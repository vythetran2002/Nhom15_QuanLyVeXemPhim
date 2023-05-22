package com.example.ticketbooking.View.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ticketbooking.Controller.HomeController;
import com.example.ticketbooking.Controller.MovieController;
import com.example.ticketbooking.Model.Adapter.FilmsInTheatersAdapter;
import com.example.ticketbooking.Model.Adapter.UpcomingAdapter;
import com.example.ticketbooking.Model.Adapter.photoAdapter;
import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.MovieNews;
import com.example.ticketbooking.Model.Service.MovieService;
import com.example.ticketbooking.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    photoAdapter ptAdapter;
    CircleIndicator circleIndicator;
    MovieController movieController;
    HomeController homeController;
    MovieService movieService;
    RecyclerView rvUpcoming, rvtheate;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        movieController.LoadSlider();
        homeController.LoadUpcomingMovies();
        movieController.LoadMoviesHome();
    }

    private void init(View view) {
        viewPager = view.findViewById(R.id.vpSlide);
        circleIndicator = view.findViewById(R.id.ciSlide);
        movieService = new MovieService(getContext());
        movieController = new MovieController(movieService, this);
        homeController = new HomeController(movieService, this);
        rvUpcoming = view.findViewById(R.id.rvUpcoming);
        rvtheate = view.findViewById(R.id.rvFilms);
    }

    public void LoadUpcoming(ArrayList<MovieNews> arrayList){

        UpcomingAdapter upcomingAdapter = new UpcomingAdapter(arrayList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvUpcoming.setLayoutManager(linearLayoutManager);
        rvUpcoming.setAdapter(upcomingAdapter);
    }

    public void LoadSlider(ArrayList<Movie> arrayList){
        ptAdapter = new photoAdapter(getContext(),arrayList, this);
        viewPager.setAdapter(ptAdapter);
        circleIndicator.setViewPager(viewPager);
        ptAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void getToast(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    public void LoadHomeNews(ArrayList<Movie> arrayListMovie) {

        FilmsInTheatersAdapter filmsInTheatersAdapter = new FilmsInTheatersAdapter(arrayListMovie, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvtheate.setLayoutManager(linearLayoutManager);
        rvtheate.setAdapter(filmsInTheatersAdapter);
    }
}