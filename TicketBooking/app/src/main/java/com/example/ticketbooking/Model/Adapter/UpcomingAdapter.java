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

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MovieNewViewHolder> {

    ArrayList<MovieNews> alMovie;
    Context context;

    public UpcomingAdapter(ArrayList<MovieNews> alMovie, Context context){
        this.alMovie = alMovie;
        this.context = context;
    }


    @NonNull
    @Override
    public MovieNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming, parent, false);
        return new MovieNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieNewViewHolder holder, int position) {
        MovieNews movieNews = alMovie.get(position);

        holder.txtNameMovie.setText(holder.txtNameMovie.getText() + movieNews.getName());
        holder.txtBrief.setText(movieNews.getDescription());
        holder.txtCastUpcoming.setText(holder.txtCastUpcoming.getText() + movieNews.getCast());
        holder.txtReleaseData.setText(holder.txtReleaseData.getText() + movieNews.getRelease_date());

        byte[] decodedString = Base64.decode(movieNews.getAttachment(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context).load(decodedByte).into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return alMovie.size();
    }

    class MovieNewViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMovie;
        TextView txtNameMovie,txtCastUpcoming, txtReleaseData, txtBrief;

        public MovieNewViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgUpcoming);
            txtNameMovie = itemView.findViewById(R.id.txtNameUpcoming);
            txtCastUpcoming = itemView.findViewById(R.id.txtCastUpcoming);
            txtReleaseData = itemView.findViewById(R.id.txtReleaseData);
            txtBrief = itemView.findViewById(R.id.txtBrief);
        }
    }
}
