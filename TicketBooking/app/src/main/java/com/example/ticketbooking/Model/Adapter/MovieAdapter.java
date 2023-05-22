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
import com.example.ticketbooking.Model.Service.MySingleton;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.fragment.DetailMovieFragment;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<Movie> alMovie;
    Context context;

    public MovieAdapter(ArrayList<Movie> alMovie, Context context){
        this.alMovie = alMovie;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return alMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMovie;
        TextView txtNameMovie;
        TextView txtCast;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMoie);
            txtNameMovie = itemView.findViewById(R.id.txtNameMovie);
            txtCast = itemView.findViewById(R.id.txtCast);
        }
    }
}
