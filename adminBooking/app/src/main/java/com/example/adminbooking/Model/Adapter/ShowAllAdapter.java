package com.example.adminbooking.Model.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbooking.Controller.AddShowController;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.R;

import java.util.ArrayList;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ShowHolder>{

    ArrayList<Show> arrayList;
    Context context;
    AddShowController addShowController;
    public ShowAllAdapter(ArrayList<Show> arrayList, Context context, AddShowController addShowController){
        this.arrayList = arrayList;
        this.context = context;
        this.addShowController = addShowController;
    }


    @NonNull
    @Override
    public ShowAllAdapter.ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowAllAdapter.ShowHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHolder holder, int position) {
        Show show = arrayList.get(position);
        holder.txtNo.setText((position+1)+"");
        holder.txtScreen.setText(show.getShowTime());
        holder.txtShowTime.setText(show.getTheatre()+"("+show.getMovie()+")");
        holder.txtMovie.setText(show.getDate());

        if (arrayList.get(position).getStatus().equals("0")){

            holder.btnStoprun.setBackgroundColor(Color.YELLOW);
        }
        else {
            holder.btnStoprun.setBackgroundColor(Color.BLUE);
            holder.btnStoprun.setText("Stop Now");

        }


        if (arrayList.get(position).getRun_status().equals("0")){

            holder.btnStopNow.setBackgroundColor(Color.MAGENTA);
            holder.btnStopNow.setText("Start Run");

        }
        else {
            holder.btnStopNow.setBackgroundColor(Color.GREEN);
            holder.btnStopNow.setText("Stop Run");

        }

        holder.btnStoprun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShowController.stopShow("0", arrayList.get(position).getId());
                arrayList.remove(arrayList.get(position));
                notifyDataSetChanged();
            }
        });

        holder.btnStopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayList.get(position).getRun_status().equals("0")){
                    addShowController.updateShowStatus("1", arrayList.get(position).getId());
                    arrayList.get(position).setRun_status("1");

                }
                else {
                    addShowController.updateShowStatus("0", arrayList.get(position).getId());
                    arrayList.get(position).setRun_status("0");

                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ShowHolder extends RecyclerView.ViewHolder{
        TextView txtNo, txtScreen, txtShowTime, txtMovie;
        Button btnStoprun, btnStopNow;

        public ShowHolder(@NonNull View itemView) {
            super(itemView);

            txtNo = itemView.findViewById(R.id.txtNoShowAll);
            txtScreen = itemView.findViewById(R.id.txtShowShowAll);
            txtShowTime = itemView.findViewById(R.id.txtScreenShowAll);
            txtMovie = itemView.findViewById(R.id.txtMovieShowAll);
            btnStopNow = itemView.findViewById(R.id.btnStopNow);
            btnStoprun = itemView.findViewById(R.id.btnStoprun);
        }
    }


}
