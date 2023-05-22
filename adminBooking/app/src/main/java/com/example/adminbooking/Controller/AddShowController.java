package com.example.adminbooking.Controller;

import com.android.volley.RequestQueue;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.Model.Bean.Showtime;
import com.example.adminbooking.Model.Bean.TodayBooking;
import com.example.adminbooking.Model.Service.AddShowSearvice;
import com.example.adminbooking.Model.Service.MySingleton;
import com.example.adminbooking.View.Fragment.AddShow;
import com.example.adminbooking.View.Fragment.TodayBookings;
import com.example.adminbooking.View.Fragment.TodayShows;
import com.example.adminbooking.View.Fragment.ViewShow;

import java.util.ArrayList;

public class AddShowController {

    AddShow addShow;
    AddShowSearvice addShowService;
    TodayShows todayShows;
    ViewShow viewShow;
    TodayBookings todayBookings;

    public AddShowController(AddShow addShow, AddShowSearvice addShowService) {
        this.addShow = addShow;
        this.addShowService = addShowService;
    }

    public AddShowController(AddShowSearvice addShowSearvice, TodayShows todayShows) {
        this.addShowService = addShowSearvice;
        this.todayShows = todayShows;

    }

    public AddShowController(AddShowSearvice addShowSearvice, ViewShow viewShow) {
        this.addShowService = addShowSearvice;
        this.viewShow = viewShow;
    }

    public AddShowController(AddShowSearvice addShowSearvice, TodayBookings todayBookings) {
        this.addShowService = addShowSearvice;
        this.todayBookings = todayBookings;
    }

    public void addShow(Show show){
        addShowService.InsertShow(show);
    }
    
    public void updateShowStatus(String status, String idShow){
        addShowService.UpdateShowStatus(status, idShow);
    }

    public void stopShow(String status, String idShow){
        addShowService.StopShow(status, idShow);
    }

    public void LoadAllShow(String id){
        addShowService.LoadAllShow(id, new AddShowSearvice.GetDataShow() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Show> arrayList) {
                viewShow.setAdapter(arrayList);
            }
        });
    }

    public void LoadTodayBookingByIDScreen(String idScreen){
        addShowService.LoadTodayBookingByScreen(idScreen, new AddShowSearvice.GetDataBooking() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<TodayBooking> arrayList) {
                todayBookings.setAdapter(arrayList);
            }
        });

    }

    public void LoadShow(String status){

        addShowService.LoadShow(status, new AddShowSearvice.GetDataShow() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Show> arrayList) {
                todayShows.setAdapter(arrayList);
            }
        });
    }

    public void LoadShowtimeByIdScreenId(String idScreen){

        addShowService.LoadShowtimeByScreen(idScreen, new AddShowSearvice.GetDataShowtime() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Showtime> arrayList) {
                addShow.LoadShowtime(arrayList);
            }
        });

    }

    public void LoadShowtimeByIdScreenIdTodayBooking(String idScreen){

        addShowService.LoadShowtimeByScreen(idScreen, new AddShowSearvice.GetDataShowtime() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Showtime> arrayList) {
                todayBookings.LoadShowtime(arrayList);
            }
        });

    }

    public void LoadScreenToSpnTodayBooking(String idTheatre){

        addShowService.LoadScreenByIDTheatre(idTheatre, new AddShowSearvice.GetDataScreen() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Screen> arrayList) {
                todayBookings.LoadScreenByIDTheatre(arrayList);
            }
        });

    }

    public void LoadScreenToSpn(String idTheatre){

        addShowService.LoadScreenByIDTheatre(idTheatre, new AddShowSearvice.GetDataScreen() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Screen> arrayList) {
                addShow.LoadScreenByIDTheatre(arrayList);
            }
        });

    }
}
