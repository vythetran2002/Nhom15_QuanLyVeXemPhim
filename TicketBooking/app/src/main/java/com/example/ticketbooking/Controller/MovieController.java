package com.example.ticketbooking.Controller;

import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.aboutMovie;
import com.example.ticketbooking.Model.Bean.managerMovie;
import com.example.ticketbooking.Model.Service.MovieService;
import com.example.ticketbooking.View.fragment.DetailMovieFragment;
import com.example.ticketbooking.View.fragment.HomeFragment;
import com.example.ticketbooking.View.fragment.MoviesFragment;

import java.util.ArrayList;

public class MovieController {

    MoviesFragment moviesFragment;
    MovieService movieService;
    DetailMovieFragment detailMovieFragment;
    HomeFragment homeFragment;

    public MovieController(MovieService movieService, MoviesFragment moviesFragment){
        this.movieService = movieService;
        this.moviesFragment = moviesFragment;
    }

    public MovieController(MovieService movieService,DetailMovieFragment detailMovieFragment){
        this.movieService = movieService;
        this.detailMovieFragment = detailMovieFragment;
    }

    public MovieController(MovieService movieService,HomeFragment homeFragment){
        this.movieService = movieService;
        this.homeFragment = homeFragment;
    }

    public void LoadMovies(){

        movieService.LoadMovies(new MovieService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                moviesFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<Movie> ArrayListMovie) {
                moviesFragment.setAdapter(ArrayListMovie);
            }
        });

    }

    public void InsertBooking(aboutMovie aboutMovie, String idUser){

        movieService.insertBooking(aboutMovie,idUser);
        detailMovieFragment.showToast("successful");
    }

    public void LoadMoviesHome(){

        movieService.LoadMovies(new MovieService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                moviesFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<Movie> ArrayListMovie) {
                homeFragment.LoadHomeNews(ArrayListMovie);
            }
        });

    }

    public  void LoadSlider(){
        movieService.LoadMovies(new MovieService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                moviesFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<Movie> ArrayListMovie) {
                homeFragment.LoadSlider(ArrayListMovie);
            }
        });
    }

    public void LoadDetailMovie(String idMovie){
        movieService.LoadDetailMovie(idMovie, new MovieService.VolleyResponseListenerDetail() {
            @Override
            public void getError(String mess) {
                moviesFragment.getToast(mess);
            }

            @Override
            public void onResponse(ArrayList<aboutMovie> arrayListMovie) {

                detailMovieFragment.setDataAdapter(convertDataMovieDatail(arrayListMovie));

            }
        });
    }

    private ArrayList<managerMovie> convertDataMovieDatail(ArrayList<aboutMovie> arrayListMovie){

        ArrayList<managerMovie> arrayList = new ArrayList<>();

        if (arrayListMovie.size() > 0){

            int i = 0;
            int j = 1;

            ArrayList<aboutMovie> arrayList1 = new ArrayList<>();
            arrayList1.add(arrayListMovie.get(0));
            managerMovie mm = new managerMovie(arrayListMovie.get(0).getTheatre(),arrayList1);

            arrayList.add(mm);
            int count = 0;

            while (i < j){
                if (j >= arrayListMovie.size()){
                    break;
                }
                if (arrayList.get(count).getName().equals(arrayListMovie.get(j).getTheatre())){
                    arrayList.get(count).getArrayListMovie().add(arrayListMovie.get(j));
                }
                else{
                    ArrayList<aboutMovie> tmp = new ArrayList<>();
                    tmp.add(arrayListMovie.get(j));
                    managerMovie managertmp = new managerMovie(arrayListMovie.get(j).getTheatre(),tmp);

                    arrayList.add(managertmp);
                    ++count;
                    i = j;
                }
                ++j;

            }

        }


        return  arrayList;
    }
}
