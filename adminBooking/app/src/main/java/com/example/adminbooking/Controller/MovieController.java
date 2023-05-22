package com.example.adminbooking.Controller;

import android.graphics.Bitmap;

import com.example.adminbooking.Model.Bean.Movie;
import com.example.adminbooking.Model.Bean.RunningMovie;
import com.example.adminbooking.Model.Service.MovieService;
import com.example.adminbooking.View.Fragment.AddMovie;
import com.example.adminbooking.View.Fragment.AddShow;
import com.example.adminbooking.View.Fragment.AddThreateFragment;
import com.example.adminbooking.View.Fragment.HomeFragment;
import com.example.adminbooking.View.Fragment.HomeTheatre;
import com.example.adminbooking.View.Fragment.upMovieNewsFragment;


import java.util.ArrayList;

public class MovieController {

    HomeFragment homeFragment;
    MovieService movieService;
    upMovieNewsFragment upMovieNewsFragment;
    HomeTheatre homeTheatre;
    AddMovie addMovie;
    AddShow addShow;

    public MovieController(MovieService movieService,  HomeFragment homeFragment){
        this.movieService = movieService;
        this.homeFragment = homeFragment;
    }

    public MovieController(MovieService movieService, AddShow addShow){
        this.movieService = movieService;
        this.addShow = addShow;
    }

    public MovieController(MovieService movieService,   upMovieNewsFragment upMovieNewsFragment){
        this.movieService = movieService;
        this.upMovieNewsFragment = upMovieNewsFragment;
    }

    public MovieController(MovieService movieService,   HomeTheatre homeTheatre){
        this.movieService = movieService;
        this.homeTheatre = homeTheatre;
    }

    public MovieController(MovieService movieServicel, AddMovie addMovie) {
        this.addMovie = addMovie;
        this.movieService = movieServicel;
    }

    public void LoadMovies(){
        movieService.LoadMovies(new MovieService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                homeFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<Movie> ArrayListMovie) {
                homeFragment.setAdapter(ArrayListMovie);
            }
        });

    }

    public void LoadMovieToSpinner(){
        movieService.LoadMovies(new MovieService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                homeFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<Movie> ArrayListMovie) {
                addShow.LoadMovie(ArrayListMovie);
            }
        });

    }

    public  void LoadMovieRunning(String idTheatre){
        movieService.LoadMoviesRunning(idTheatre, new MovieService.getArrayRunning() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void onResponse(ArrayList<RunningMovie> ArrayListMovieRunning) {
                homeTheatre.setAdapterRunningMovie(ArrayListMovieRunning);
            }
        });
    }

    public void InsertMovieNews(Movie movie){

        movieService.addMovieNews(movie, new MovieService.getLinkListener() {
            @Override
            public void getError(String mess) {
                upMovieNewsFragment.showToast(mess);
            }

            @Override
            public void onResponse(String nameImage) {
                upMovieNewsFragment.showToast("susseccful");
            }
        });

    }

    public  void InsertImage(Bitmap bitmap, Movie movie){
        movieService.InsertImage(bitmap, new MovieService.getLinkListener() {
            @Override
            public void getError(String mess) {
                upMovieNewsFragment.showToast(mess);
            }

            @Override
            public void onResponse(String nameImage) {
                movie.setImage(nameImage);
                upMovieNewsFragment.setTextIMG(nameImage);
                InsertMovieNews(movie);
            }
        });
    }

    public void addMovieTheatre(Movie movie){

        movieService.addMovieTheatre(movie, new MovieService.getLinkListener() {
            @Override
            public void getError(String mess) {
                addMovie.showToast(mess);
            }

            @Override
            public void onResponse(String nameImage) {
                addMovie.showToast("susseccful");
            }
        });

    }

    public  void InsertImageMovieAdd(Bitmap bitmap, Movie movie){
        movieService.InsertImageAddMoie(bitmap, new MovieService.getLinkListener() {
            @Override
            public void getError(String mess) {
                addMovie.showToast(mess);
            }

            @Override
            public void onResponse(String nameImage) {
                movie.setImage(nameImage);
                addMovie.setTextIMG(nameImage);
                addMovieTheatre(movie);
            }
        });
    }

    public  void DeleteMovieByID(String id){
        movieService.DeleteMovieByID( id);
    }
}
