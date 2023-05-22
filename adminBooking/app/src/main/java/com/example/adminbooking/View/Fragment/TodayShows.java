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

import com.example.adminbooking.Controller.AddShowController;
import com.example.adminbooking.Controller.MovieController;
import com.example.adminbooking.Model.Adapter.ShowAdapter;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.Model.Service.AddShowSearvice;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;


public class TodayShows extends Fragment {

    RecyclerView rv;
    AddShowSearvice addShowSearvice;
    AddShowController addShowController;
    ShowAdapter showAdapter;

    public TodayShows() {
        // Required empty public constructor


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        addShowController.LoadShow(MainActivityIMP.login.getUserId());
    }

    private void init(View view) {
        rv = view.findViewById(R.id.rvTodayShow);
        addShowSearvice = new AddShowSearvice(getContext());
        addShowController = new AddShowController(addShowSearvice,this);
    }

    public void setAdapter(ArrayList<Show> arrayList){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        showAdapter = new ShowAdapter(arrayList,getContext());
        rv.setAdapter(showAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_shows, container, false);
    }
}