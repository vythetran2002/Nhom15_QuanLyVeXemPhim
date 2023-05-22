package com.example.adminbooking.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbooking.Model.Bean.RunningMovie;
import com.example.adminbooking.R;

import java.util.ArrayList;

public class RunningMovieAdapter extends RecyclerView.Adapter<RunningMovieAdapter.RunnongMovieHolder> {

    ArrayList<RunningMovie> arrayListRunningMovie;
    Context context;

    public RunningMovieAdapter(ArrayList<RunningMovie> arrayListRunningMovie,  Context context){
        this.arrayListRunningMovie = arrayListRunningMovie;
        this.context = context;
    }

    @NonNull
    @Override
    public RunnongMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RunningMovieAdapter.RunnongMovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_running, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RunnongMovieHolder holder, int position) {

        RunningMovie runningMovie = arrayListRunningMovie.get(position);
        holder.txtNo.setText((position+1)+"");
        holder.txtMovieRunning.setText(runningMovie.getNameMovie());
        holder.txtShowtimeRunning.setText(runningMovie.getName()+"("+runningMovie.getStart_time()+")");
        holder.txtScreenRunning.setText(runningMovie.getScreen());
    }

    @Override
    public int getItemCount() {
        return arrayListRunningMovie.size();
    }


    class RunnongMovieHolder extends RecyclerView.ViewHolder{

        TextView txtNo, txtShowtimeRunning, txtScreenRunning, txtMovieRunning;

        public RunnongMovieHolder(@NonNull View itemView) {
            super(itemView);

            txtNo = itemView.findViewById(R.id.txtNo);
            txtShowtimeRunning = itemView.findViewById(R.id.txtShowtimeRunning);
            txtScreenRunning = itemView.findViewById(R.id.txtScreenRunning);
            txtMovieRunning = itemView.findViewById(R.id.txtMovieRunning);
        }
    }

}
