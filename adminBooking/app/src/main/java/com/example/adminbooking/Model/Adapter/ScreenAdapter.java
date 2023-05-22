package com.example.adminbooking.Model.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbooking.Controller.ScreenController;
import com.example.adminbooking.Model.Bean.ManagerScreen;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;

public class ScreenAdapter extends RecyclerView.Adapter<ScreenAdapter.ScreenHolder> {

    ArrayList<ManagerScreen> arrayList;
    Context context;
    ScreenController screenController;

    public ScreenAdapter(ArrayList<ManagerScreen> arrayList, Context context,  ScreenController screenController) {
        this.arrayList = arrayList;
        this.context = context;
        this.screenController = screenController;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScreenAdapter.ScreenHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_screen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenHolder holder, int position) {
        ManagerScreen managerScreen = arrayList.get(position);

        holder.txtNoScreen.setText((position + 1)+"");
        holder.txtScreenName.setText(managerScreen.getScreen().getScreen());
        holder.txtCharge.setText(managerScreen.getScreen().getCharge());
        holder.txtSeats.setText(managerScreen.getScreen().getSeats());

        String st = "";
        for (String str : arrayList.get(position).getListStartTime()){
            st += str+",";
        }

        holder.txtShowtime.setText(st);
        holder.btnAddScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_time);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                Spinner spinner = dialog.findViewById(R.id.spnScreendialog);
                TimePicker timePicker = dialog.findViewById(R.id.dateTimePickerAddMovie);
                Button btnAddTime = dialog.findViewById(R.id.btnAddScreenDialog);
                timePicker.setIs24HourView(true);

                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("Matinee");
                arrayList.add("First");
                arrayList.add("Second");
                arrayList.add("Others");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,arrayList);
                spinner.setAdapter(arrayAdapter);

                btnAddTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String text = spinner.getSelectedItem().toString();
                        String time = timePicker.getHour() + ":"+timePicker.getMinute()+":00";

                        screenController.addTime(managerScreen.getScreen().getId(),text, time);
                        Toast.makeText(context,"Sussecc", Toast.LENGTH_LONG).show();
                        screenController.LoadScreen(MainActivityIMP.login.getUserId());
                    }
                });




                ImageView imgClose = dialog.findViewById(R.id.imgClose);

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
        return arrayList.size();
    }

    class ScreenHolder extends RecyclerView.ViewHolder {

        TextView txtNoScreen, txtScreenName, txtSeats, txtCharge;
        TextView txtShowtime;
        Button btnAddScreen;

        public ScreenHolder(@NonNull View itemView) {
            super(itemView);

            txtNoScreen = itemView.findViewById(R.id.txtNoScreen);
            txtScreenName = itemView.findViewById(R.id.txtScreenName);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            btnAddScreen = itemView.findViewById(R.id.btnAddScreen);
            txtCharge = itemView.findViewById(R.id.txtCharge);
            txtShowtime = itemView.findViewById(R.id.txtShowtime);
        }
    }
}
