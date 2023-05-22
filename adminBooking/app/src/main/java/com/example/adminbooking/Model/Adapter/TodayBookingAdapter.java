package com.example.adminbooking.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbooking.Model.Bean.TodayBooking;
import com.example.adminbooking.R;

import java.util.ArrayList;

public class TodayBookingAdapter extends RecyclerView.Adapter<TodayBookingAdapter.TodayBookingHolder> {

    ArrayList<TodayBooking> arrayList;
    Context context;

    public TodayBookingAdapter(ArrayList<TodayBooking> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodayBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new TodayBookingAdapter.TodayBookingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today_booking, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull TodayBookingHolder holder, int position) {

        TodayBooking todayBooking = arrayList.get(position);

        holder.txtNo_seats.setText(todayBooking.getSeats());
        holder.txtPhone.setText(todayBooking.getPhone());
        holder.txtName.setText(todayBooking.getName());
        holder.txtTicketID.setText(todayBooking.getId());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TodayBookingHolder extends RecyclerView.ViewHolder{

        TextView txtTicketID, txtName, txtPhone, txtNo_seats;

        public TodayBookingHolder(@NonNull View itemView) {
            super(itemView);

            txtNo_seats = itemView.findViewById(R.id.txtNoTodayBooking);
            txtTicketID = itemView.findViewById(R.id.txtIDBooking);
            txtName = itemView.findViewById(R.id.txtNameTodayBooking);
            txtPhone = itemView.findViewById(R.id.txtphone);
        }
    }

}
