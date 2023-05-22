package com.example.ticketbooking.Model.Bean;

import java.util.ArrayList;

public class managerMovie {
    String name;
    ArrayList<aboutMovie> arrayListMovie;

    public managerMovie(String name, ArrayList<aboutMovie> arrayListMovie){
        this.name = name;
        this.arrayListMovie = arrayListMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<aboutMovie> getArrayListMovie() {
        return arrayListMovie;
    }

    public void setArrayListMovie(ArrayList<aboutMovie> arrayListMovie) {
        this.arrayListMovie = arrayListMovie;
    }
}
