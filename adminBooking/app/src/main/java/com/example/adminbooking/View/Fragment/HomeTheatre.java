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

import com.example.adminbooking.Controller.MovieController;
import com.example.adminbooking.Model.Adapter.RunningMovieAdapter;
import com.example.adminbooking.Model.Bean.RunningMovie;
import com.example.adminbooking.Model.Service.MovieService;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;

public class HomeTheatre extends Fragment {

    RunningMovieAdapter runningMovieAdapter;
    RecyclerView recyclerView;

    MovieService movieService;
    MovieController movieControllerl;

    public HomeTheatre() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        movieControllerl.LoadMovieRunning(MainActivityIMP.login.getUserId());
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rvRunningMovie);
        movieService = new MovieService(getContext());
        movieControllerl = new MovieController(movieService,this );
    }

    public  void setAdapterRunningMovie(ArrayList<RunningMovie> arrayList){

        runningMovieAdapter = new RunningMovieAdapter(arrayList, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(runningMovieAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_theatre, container, false);
    }
}