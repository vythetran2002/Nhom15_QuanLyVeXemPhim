package com.example.adminbooking.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.adminbooking.Controller.AddShowController;
import com.example.adminbooking.Model.Adapter.TodayBookingAdapter;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.Showtime;
import com.example.adminbooking.Model.Bean.TodayBooking;
import com.example.adminbooking.Model.Service.AddShowSearvice;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;

public class TodayBookings extends Fragment {

    Spinner spnScreen, spnShow;
    RecyclerView rvTodayBooking;
    AddShowSearvice addShowSearvice;
    AddShowController addShowController;
    String showtime;

    public TodayBookings() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        addShowController.LoadScreenToSpnTodayBooking(MainActivityIMP.login.getUserId());
    }

    private void init(View view) {
        spnScreen = view.findViewById(R.id.spnScreen);
        spnShow =view.findViewById(R.id.spnShow);
        rvTodayBooking = view.findViewById(R.id.rvTodayBooking);
        addShowSearvice= new AddShowSearvice(getContext());
        addShowController = new AddShowController(addShowSearvice, this);
    }

    public void LoadScreenByIDTheatre(ArrayList<Screen> arrayList) {
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i< arrayList.size(); ++i){
            tmp.add(arrayList.get(i).getScreen());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,tmp);
        spnScreen.setAdapter(arrayAdapter);

        spnScreen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addShowController.LoadShowtimeByIdScreenIdTodayBooking(arrayList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_bookings, container, false);
    }

    public void setAdapter(ArrayList<TodayBooking> arrayList){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        TodayBookingAdapter todayBookingAdapter = new TodayBookingAdapter(arrayList, getContext());
        rvTodayBooking.setLayoutManager(linearLayoutManager);
        rvTodayBooking.setAdapter(todayBookingAdapter);
    }

    public void LoadShowtime(ArrayList<Showtime> arrayList) {

        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i< arrayList.size(); ++i){
            tmp.add(arrayList.get(i).getName()+"("+arrayList.get(i).getStart_time()+")");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,tmp);
        spnShow.setAdapter(arrayAdapter);

        spnShow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showtime = arrayList.get(position).getId();
                addShowController.LoadTodayBookingByIDScreen(showtime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}