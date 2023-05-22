package com.example.ticketbooking.Controller;

import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.MovieNews;
import com.example.ticketbooking.Model.Service.MovieService;
import com.example.ticketbooking.View.fragment.DetailMovieFragment;
import com.example.ticketbooking.View.fragment.HomeFragment;

import java.util.ArrayList;

public class HomeController {

    MovieService movieService;
    HomeFragment homeFragment;

    public HomeController(MovieService movieService,HomeFragment homeFragment){
        this.movieService = movieService;
        this.homeFragment = homeFragment;
    }

    public void LoadUpcomingMovies(){

        movieService.LoadUpconmingMovie(new MovieService.VolleyResponseListenerUpcoming() {

            @Override
            public void getError(String mess) {
                homeFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<MovieNews> ArrayListMovie) {
                homeFragment.LoadUpcoming(ArrayListMovie);
            }
        });
    }
}
