package com.example.ticketbooking.View.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ticketbooking.Controller.MovieController;
import com.example.ticketbooking.Model.Adapter.MovieAdapter;
import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Service.MovieService;
import com.example.ticketbooking.R;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieService movieService;
    MovieController movieController;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        movieController.LoadMovies();
    }

    public void getToast(String mess){
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    public  void setAdapter(ArrayList<Movie> arrayList){
        movieAdapter = new MovieAdapter(arrayList, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rcMobie);
        movieService = new MovieService(getContext());
        movieController = new MovieController(movieService, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }
}