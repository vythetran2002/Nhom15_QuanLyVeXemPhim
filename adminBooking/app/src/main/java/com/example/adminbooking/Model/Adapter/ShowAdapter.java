package com.example.adminbooking.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbooking.Model.Bean.RunningMovie;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.R;

import java.util.ArrayList;

public class ShowAdapter extends  RecyclerView.Adapter<ShowAdapter.ShowHolder>{

    ArrayList<Show> arrayList;
    Context context;

    public ShowAdapter(ArrayList<Show> arrayList,  Context context){
        this.arrayList = arrayList;
        this.context = context;

    }


    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowAdapter.ShowHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder holder, int position) {
        Show show = arrayList.get(position);
        holder.txtNo.setText((position+1)+"");
        holder.txtScreen.setText(show.getShowTime());
        holder.txtShowTime.setText(show.getTheatre()+"("+show.getMovie()+")");
        holder.txtMovie.setText(show.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ShowHolder extends RecyclerView.ViewHolder{

        TextView txtNo, txtScreen, txtShowTime, txtMovie;

        public ShowHolder(@NonNull View itemView) {
            super(itemView);

            txtNo = itemView.findViewById(R.id.txtNoTodayShow);
            txtScreen = itemView.findViewById(R.id.txtShowtimeToday);
            txtShowTime = itemView.findViewById(R.id.txtScreenToday);
            txtMovie = itemView.findViewById(R.id.txtMovieToday);
        }
    }

}
