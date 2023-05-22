package com.example.ticketbooking.Model.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbooking.Model.Bean.Profile;
import com.example.ticketbooking.R;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHolde>{

    Context context;
    ArrayList<Profile> arrayListProfile;

    public  BookingHistoryAdapter(Context context, ArrayList<Profile> arrayList){
        this.context = context;
        this.arrayListProfile = arrayList;
    }

    @NonNull
    @Override
    public BookingHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history, parent, false);
        BookingHolde bookingHolde = new BookingHolde(view);
        return bookingHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolde holder, int position) {

        Profile profile = arrayListProfile.get(position);

        holder.txtSeats.setText(profile.getSeats());
        holder.txtTheatre.setText(profile.getTheatre());
        holder.txtMovie.setText(profile.getMovie());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(holder.itemView.getContext());
                dialog.setContentView(R.layout.dialog_detail_booking);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                TextView txtBookingid = dialog.findViewById(R.id.txtBookingID);
                TextView txtMovie = dialog.findViewById(R.id.txtMovie);
                TextView txttheatre = dialog.findViewById(R.id.txtTheatre);
                TextView txtScreen = dialog.findViewById(R.id.txtScreen);
                TextView txtShow = dialog.findViewById(R.id.txtShow);
                TextView txtSeats = dialog.findViewById(R.id.txtSeats);
                TextView txtAmount = dialog.findViewById(R.id.txtAmount);
                ImageView imgClose = dialog.findViewById(R.id.imgClose);
                txtBookingid.setText(profile.getBookingid());
                txtMovie.setText(profile.getMovie());
                txttheatre.setText(profile.getTheatre());
                txtScreen.setText(profile.getScreen());
                txtShow.setText(profile.getShow());
                txtSeats.setText(profile.getSeats());
                txtAmount.setText("Rs." + profile.getAmount());

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListProfile.size();
    }

    class BookingHolde extends RecyclerView.ViewHolder{

        TextView txtMovie;
        TextView txtTheatre;
        TextView txtSeats;

        Button btnView;

        public BookingHolde(@NonNull View itemView) {
            super(itemView);
            txtMovie = itemView.findViewById(R.id.txtMovie);
            txtTheatre = itemView.findViewById(R.id.txtTheatre);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            btnView = itemView.findViewById(R.id.btnView);

        }
    }

}
