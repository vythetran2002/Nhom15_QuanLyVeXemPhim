package com.example.ticketbooking.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbooking.Model.Bean.managerMovie;
import com.example.ticketbooking.R;

import java.util.ArrayList;

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapter.DetailMovieHolder> {

    Context context;
    ArrayList<managerMovie> arrayList;
    sendDataDetailBooking sendData;
    public DetailMovieAdapter(Context context, ArrayList<managerMovie> arrayList, sendDataDetailBooking sendData){
        this.arrayList = arrayList;
        this.context = context;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public DetailMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_movie, parent, false);
        return new DetailMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMovieHolder holder, int position) {

        managerMovie m = arrayList.get(position);
        holder.txtName.setText(m.getName());
        DetailTimeStartAdapter detailTimeStartAdapter = new DetailTimeStartAdapter(m.getArrayListMovie(), context, this.sendData);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        holder.recyclerView.setLayoutManager(gridLayoutManager);
        holder.recyclerView.setAdapter(detailTimeStartAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class DetailMovieHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        RecyclerView recyclerView;

        public DetailMovieHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNameDetailMovie);
            recyclerView = itemView.findViewById(R.id.rcItemDetailMovie);

        }
    }
}
