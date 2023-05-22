package com.example.ticketbooking.Model.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Service.MySingleton;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.fragment.HomeFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class photoAdapter extends PagerAdapter {
    Context context;
    ArrayList<Movie> listMovie;
    HomeFragment homeFragment;
    public photoAdapter(Context context, ArrayList<Movie> listMovie, HomeFragment homeFragment) {
        this.context = context;
        this.listMovie = listMovie;
        this.homeFragment = homeFragment;
    }

    @Override
    public int getCount() {
        return listMovie.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        Movie movie = listMovie.get(position);

        ImageView imageView = view.findViewById(R.id.imgPhoto);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_video);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                YouTubePlayerView youTubePlayerView = dialog.findViewById(R.id.youtube_player_view);
                homeFragment.getLifecycle().addObserver(youTubePlayerView);
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

        byte[] decodedString = Base64.decode(movie.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context).load(decodedByte).into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

    }
}
