package com.example.ticketbooking.Model.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.MovieNews;
import com.example.ticketbooking.Model.Service.MySingleton;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.fragment.DetailMovieFragment;

import java.util.ArrayList;

public class FilmsInTheatersAdapter extends RecyclerView.Adapter<FilmsInTheatersAdapter.FilmsInTheatersViewHolder> {

    ArrayList<Movie> arrayList;
    Context context;

    public  FilmsInTheatersAdapter( ArrayList<Movie> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmsInTheatersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_theaters, parent, false);
        return new FilmsInTheatersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsInTheatersViewHolder holder, int position) {
        Movie movie = arrayList.get(position);

        holder.txtNameMovie.setText(holder.txtNameMovie.getText() + movie.getMovie_name());
        holder.txtBrief.setText(movie.getDesc());
        holder.txtCastUpcoming.setText(holder.txtCastUpcoming.getText() + movie.getCast());
        holder.txtReleaseData.setText(holder.txtReleaseData.getText() + movie.getRelease_date());

        holder.imgMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailMovieFragment();
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.frameLayout)).commit();

                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout, fragment ,null);
                fragmentTransaction.commit();
            }
        });

        byte[] decodedString = Base64.decode(movie.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context).load(decodedByte).into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class FilmsInTheatersViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMovie;
        TextView txtNameMovie,txtCastUpcoming, txtReleaseData, txtBrief;

        public FilmsInTheatersViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgTheate);
            txtNameMovie = itemView.findViewById(R.id.txtNameTheate);
            txtCastUpcoming = itemView.findViewById(R.id.txtCastTheate);
            txtReleaseData = itemView.findViewById(R.id.txtReleaseDataTheate);
            txtBrief = itemView.findViewById(R.id.txtBriefTheate);
        }
    }
}
