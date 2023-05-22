package com.example.ticketbooking.Model.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbooking.MainActivity;
import com.example.ticketbooking.Model.Bean.aboutMovie;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.MainActivity.MainActivityImp;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;

public class DetailTimeStartAdapter extends RecyclerView.Adapter<DetailTimeStartAdapter.DetailTimeStartHolder> {


    ArrayList<aboutMovie> ArraylistAboutMovies;
    Context context;
    sendDataDetailBooking sendData;
    final int postcode = 7171;

    private static PayPalConfiguration configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("ARlUHaIMQG5x1buWvuKQjhPt8lmrvgTkrpM0I5ROlFQRUWuXwFLgvQGRuH7aUbJtAAedM9ease4uvTRO");

    public DetailTimeStartAdapter( ArrayList<aboutMovie> ArraylistAboutMovies,  Context context, sendDataDetailBooking sendData){
        this.ArraylistAboutMovies = ArraylistAboutMovies;
        this.context = context;
        this.sendData = sendData;

        Intent intent = new Intent(context, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        context.startService(intent);

    }

    @NonNull
    @Override
    public DetailTimeStartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_show, parent, false);
        return new DetailTimeStartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailTimeStartHolder holder, int position) {

        aboutMovie aM = ArraylistAboutMovies.get(position);
        holder.txtTime.setText(aM.getStarttime());

        holder.txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivityImp.login == null){
                    Toast.makeText(context, "Login before using this function!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        showDialog(aM);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void showDialog(aboutMovie aM) throws ParseException {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_payment);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        TextView txtTheatre = dialog.findViewById(R.id.txtTheatrePaymetn);
        TextView txtScreen = dialog.findViewById(R.id.txtScreenPayment);
        Spinner spinnerDate = dialog.findViewById(R.id.spnTime);
        TextView txtShowtime = dialog.findViewById(R.id.txtShowtimePayment);
        NumberPicker npSeats = dialog.findViewById(R.id.npNumberSeats);
        TextView txtAmount = dialog.findViewById(R.id.txtAmountPayment);
        Button  btnAmount = dialog.findViewById(R.id.btnPaymentMoMo);

        npSeats.setMaxValue(30);
        npSeats.setMinValue(1);
        String oldstring = "2011-01-18 "+aM.getStarttime() ;
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
        String newstring = new SimpleDateFormat("HH:mm:ss").format(date);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 1; i < 5; ++i){
            c.add(Calendar.DATE, 1);
            Date currentDatePlusOne = c.getTime();
            arrayList.add(dateFormat.format(currentDatePlusOne));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,arrayList);
        spinnerDate.setAdapter(arrayAdapter);

        txtTheatre.setText(aM.getTheatre() + aM.getPlace());
        txtScreen.setText(aM.getScreenname());
        txtShowtime.setText(newstring + " " + aM.getNametime());
        txtAmount.setText("Rs. " + aM.getCharge());

        npSeats.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtAmount.setText("Rs. " + (Integer.parseInt(aM.getCharge()) * newVal));
            }
        });

        btnAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String formatted = dateFormat.format(date).toString();

                aM.setTime_booking(formatted.toString());
                aM.setCharge((Integer.parseInt(aM.getCharge()) * Integer.parseInt(npSeats.getValue()+""))+"");
                aM.setSeats(npSeats.getValue()+"");
                aM.setDate(spinnerDate.getSelectedItem().toString());

                sendData.sendData(aM);
                requestPayment((Integer.parseInt(aM.getCharge()) * Integer.parseInt(npSeats.getValue()+""))+"");
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void requestPayment(String amount) {

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD","Pay",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        ((Activity)context).startActivityForResult(intent, postcode);
       // ((Activity)context).startActivityForResult();

    }


    @Override
    public int getItemCount() {
        return ArraylistAboutMovies.size();
    }

    class DetailTimeStartHolder extends RecyclerView.ViewHolder {
        TextView txtTime;
        public DetailTimeStartHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtStartTime);
        }
    }
}
