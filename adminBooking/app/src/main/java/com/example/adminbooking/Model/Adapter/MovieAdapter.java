package com.example.adminbooking.Model.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminbooking.Controller.MovieController;
import com.example.adminbooking.Model.Bean.Movie;
import com.example.adminbooking.Model.Service.MySingleton;
import com.example.adminbooking.R;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<Movie> alMovie;
    Context context;
    MovieController movieController;
    eventClickListener eventClickListener;
    public interface eventClickListener{
        void onClickDelete(String id);
    }

    public MovieAdapter(ArrayList<Movie> alMovie, Context context, MovieController movieController,    eventClickListener eventClickListener){
        this.alMovie = alMovie;
        this.context = context;
        this.movieController = movieController;
        this.eventClickListener = eventClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = alMovie.get(position);
        holder.txtCast.setText(movie.getCast());
        holder.txtNameMovie.setText(movie.getMovie_name());

        byte[] decodedString = Base64.decode(movie.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context).load(decodedByte).into(holder.imgMovie);

        holder.btnDeleteItemMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alMovie.remove(movie);
                eventClickListener.onClickDelete(movie.getMovie_id());
                notifyDataSetChanged();
                //movieController.DeleteMovieByID(movie.getMovie_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return alMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMovie;
        TextView txtNameMovie;
        TextView txtCast;
        Button btnDeleteItemMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMoie);
            txtNameMovie = itemView.findViewById(R.id.txtNameMovie);
            txtCast = itemView.findViewById(R.id.txtCast);
            btnDeleteItemMovie = itemView.findViewById(R.id
            .btnDeleteItemMovie);
        }
    }
}
