package com.example.ticketbooking.View.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ticketbooking.Controller.MyProfileController;
import com.example.ticketbooking.Model.Adapter.BookingHistoryAdapter;
import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Bean.Profile;
import com.example.ticketbooking.Model.Service.MyProfileService;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.MainActivity.MainActivityImp;

import java.util.ArrayList;

public class MyProfileFragment extends Fragment {

    Login login;
    MyProfileController myProfileController;
    RecyclerView recyclerView;
    MyProfileService myProfileService;

    public MyProfileFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        myProfileController.LoadProfile(login.getUserId());

    }

    public void getMessager(String mess){

        Toast.makeText(getContext(),mess, Toast.LENGTH_SHORT).show();

    }

    public void init(View view){

        recyclerView = view.findViewById(R.id.rcProfile);
        myProfileService = new MyProfileService(getContext());
        myProfileController = new MyProfileController(this, myProfileService);
    }

    public void setDataAdapter(ArrayList<Profile> arrayListProfile){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        BookingHistoryAdapter bookingHistoryAdapter = new BookingHistoryAdapter(getContext(), arrayListProfile);
        recyclerView.setAdapter(bookingHistoryAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        login = MainActivityImp.login;
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }
}