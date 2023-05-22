package com.example.adminbooking.View.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminbooking.Controller.ScreenController;
import com.example.adminbooking.Controller.ThreatreController;
import com.example.adminbooking.Model.Adapter.ScreenAdapter;
import com.example.adminbooking.Model.Bean.ManagerScreen;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.Theatre;
import com.example.adminbooking.Model.Service.ScreenService;
import com.example.adminbooking.Model.Service.TheatreService;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;

public class Theatre_Details extends Fragment {

    RecyclerView rvScreen;
    ScreenService screenService;
    ScreenController screenController;
    ScreenAdapter screenAdapter;

    TheatreService theatreService;
    ThreatreController threatreController;
    Button btnAddScreen;
    TextView txtTheaterName, txtTheaterAddress, txtPlace, txtState, txtPin;

    public Theatre_Details() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        screenController.LoadScreen(MainActivityIMP.login.getUserId());
        threatreController.LoadTheatre(MainActivityIMP.login.getUserId());

        btnAddScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_screen);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                EditText edtname = dialog.findViewById(R.id.edtScreenName);
                EditText edtSeats = dialog.findViewById(R.id.edtSeats);
                EditText edtCharge = dialog.findViewById(R.id.edtCharge);
                Button btnAdd = dialog.findViewById(R.id.btnAddScreenDialog);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        screenController.AddScreen(new Screen(null, MainActivityIMP.login.getUserId(),edtname.getText().toString(),
                                edtSeats.getText().toString(), edtCharge.getText().toString()));
                        Toast.makeText(getContext(), "successfull", Toast.LENGTH_LONG).show();
                        edtname.setText("");
                        edtSeats.setText("");
                        edtCharge.setText("");
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

    private void init(View view) {
        rvScreen = view.findViewById(R.id.rvScreenDetails);
        screenService = new ScreenService(getContext());
        screenController = new ScreenController(screenService, this);
        theatreService = new TheatreService(getContext());
        threatreController = new ThreatreController(this, theatreService);
        btnAddScreen = view.findViewById(R.id.btnAddScreenn);
        txtTheaterName = view.findViewById(R.id.txtTheaterName);
        txtTheaterAddress = view.findViewById(R.id.txtTheaterAddress);
        txtPlace = view.findViewById(R.id.txtPlace);
        txtState = view.findViewById(R.id.txtState);
        txtPin = view.findViewById(R.id.txtPin);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvScreen.setLayoutManager(linearLayoutManager);
    }

    public void LoadTheatre(Theatre theatre){
        txtState.setText(theatre.getState());
        txtPin.setText(theatre.getPin());
        txtPlace.setText(theatre.getPlace());
        txtTheaterAddress.setText(theatre.getAddress());
        txtTheaterName.setText(theatre.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theatre__details, container, false);
    }

    public void setAdapter(ArrayList<ManagerScreen> convertDataScreen) {
        screenAdapter = new ScreenAdapter(convertDataScreen, getContext(), screenController);
        rvScreen.setAdapter(screenAdapter);
    }
}