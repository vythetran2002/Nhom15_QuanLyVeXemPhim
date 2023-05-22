package com.example.adminbooking.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminbooking.Controller.AddShowController;
import com.example.adminbooking.Controller.MovieController;
import com.example.adminbooking.MainActivity;
import com.example.adminbooking.Model.Bean.Movie;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.Model.Bean.Showtime;
import com.example.adminbooking.Model.Service.AddShowSearvice;
import com.example.adminbooking.Model.Service.MovieService;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

import java.util.ArrayList;

public class AddShow extends Fragment {

    DatePicker datePicker;
    Spinner spnSelectmovie, spnSelectScreen, spnShowTime;
    Button btnAddShow;

    MovieService movieService;
    MovieController movieController;
    AddShowController addShowController;
    AddShowSearvice addShowSearvice;

    String showtime, movie;

    public AddShow() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        movieController.LoadMovieToSpinner();
        addShowController.LoadScreenToSpn(MainActivityIMP.login.getUserId());

        btnAddShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShowController.addShow(new Show(null, showtime, MainActivityIMP.login.getUserId(), movie, datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth(),null,null));
                Toast.makeText(getContext(),"Susscess", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(View view) {
        spnSelectmovie = view.findViewById(R.id.spnSelectmovie);
        spnSelectScreen = view.findViewById(R.id.spnSelectScreen);
        spnShowTime = view.findViewById(R.id.spnShowTime);
        datePicker = view.findViewById(R.id.datePickerAddMovie);
        btnAddShow = view.findViewById(R.id.btnAddShow);

        addShowSearvice = new AddShowSearvice(getContext());
        addShowController = new AddShowController(this, addShowSearvice);

        movieService = new MovieService(getContext());
        movieController = new MovieController(movieService, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_show, container, false);
    }



    public void LoadMovie(ArrayList<Movie> arrayListMovie) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i< arrayListMovie.size(); ++i){
            arrayList.add(arrayListMovie.get(i).getMovie_name());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,arrayList);
        spnSelectmovie.setAdapter(arrayAdapter);

        spnSelectmovie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movie = arrayListMovie.get(position).getMovie_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void LoadScreenByIDTheatre(ArrayList<Screen> arrayList) {
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i< arrayList.size(); ++i){
            tmp.add(arrayList.get(i).getScreen());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,tmp);
        spnSelectScreen.setAdapter(arrayAdapter);

        spnSelectScreen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addShowController.LoadShowtimeByIdScreenId(arrayList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void LoadShowtime(ArrayList<Showtime> arrayList) {

        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i< arrayList.size(); ++i){
            tmp.add(arrayList.get(i).getName()+"("+arrayList.get(i).getStart_time()+")");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,tmp);
        spnShowTime.setAdapter(arrayAdapter);

        spnShowTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showtime = arrayList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}