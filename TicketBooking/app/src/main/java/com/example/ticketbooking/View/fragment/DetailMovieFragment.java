package com.example.ticketbooking.View.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.ticketbooking.Controller.LoginController;
import com.example.ticketbooking.Controller.MovieController;
import com.example.ticketbooking.MainActivity;
import com.example.ticketbooking.Model.Adapter.DetailMovieAdapter;
import com.example.ticketbooking.Model.Adapter.MovieAdapter;
import com.example.ticketbooking.Model.Adapter.sendDataDetailBooking;
import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.aboutMovie;
import com.example.ticketbooking.Model.Bean.managerMovie;
import com.example.ticketbooking.Model.Service.LoginService;
import com.example.ticketbooking.Model.Service.MovieService;
import com.example.ticketbooking.Model.Service.MySingleton;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.MainActivity.MainActivityImp;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;
import vn.momo.momo_partner.MoMoParameterNameMap;
public class DetailMovieFragment extends Fragment {

    Movie movie;
    RecyclerView recyclerView;
    MovieService movieService;
    MovieController movieController;
    ImageView imgMovie;
    TextView txtCast, txtReleaseDate, txtDecs;
    aboutMovie _aboutMovie;
    final int postcode = 7171;
    Button btnWatchTrailer;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setDataControll();
        movieController.LoadDetailMovie(movie.getMovie_id());
    }

    private void setDataControll() {
        txtCast.setText(txtCast.getText().toString() + movie.getCast());
        txtReleaseDate.setText(txtReleaseDate.getText().toString()+movie.getRelease_date());
        txtDecs.setText(txtDecs.getText().toString()+movie.getDesc());
        byte[] decodedString = Base64.decode(movie.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(getContext()).load(decodedByte).into(imgMovie);

        btnWatchTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_video);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                YouTubePlayerView youTubePlayerView = dialog.findViewById(R.id.youtube_player_view);
                getLifecycle().addObserver(youTubePlayerView);
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId[] = movie.getVideo_url().split("=");
                        youTubePlayer.loadVideo(videoId[1], 0);
                    }
                });

                dialog.show();
            }
        });

    }

    public void setDataAdapter( ArrayList<managerMovie> arrayList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DetailMovieAdapter detailMovieAdapter = new DetailMovieAdapter(getContext(), arrayList, new sendDataDetailBooking() {
            @Override
            public void sendData(aboutMovie aboutMovie) {
                _aboutMovie = aboutMovie;
            }
        });
        recyclerView.setAdapter(detailMovieAdapter);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public void showToast(String mess){
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    public void init(View view) {
        btnWatchTrailer = view.findViewById(R.id.btnWatchTrailer);
        recyclerView = view.findViewById(R.id.rcDetailMovie);
        txtCast = view.findViewById(R.id.txtCastDetail);
        txtDecs = view.findViewById(R.id.txtDesc);
        txtReleaseDate = view.findViewById(R.id.txtRelease);
        imgMovie = view.findViewById(R.id.imgDetailMovie);
        movieService = new MovieService(getContext());
        movieController = new MovieController(movieService, this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == postcode)
        {
            if (resultCode == Activity.RESULT_OK) {


                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if (paymentConfirmation != null) {
                    try {
                        String detail = paymentConfirmation.toJSONObject().toString(4);
                        movieController.InsertBooking(_aboutMovie, MainActivityImp.login.getUserId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(getContext(),"Invalid", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movie = (Movie) getArguments().getSerializable("movie");
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }
}