package com.example.adminbooking.Model.Bean;

public class Screen {

    private  String id;
    private  String theatreID;
    private  String screen;
    private String seats;
    private String charge;
    private  String Showtime;

    public Screen(String id, String theatreID, String screen, String seats, String charge) {
        this.id = id;
        this.theatreID = theatreID;
        this.screen = screen;
        this.seats = seats;
        this.charge = charge;
    }

    public Screen(String id, String theatreID, String screen, String seats, String charge, String showtime) {
        this.id = id;
        this.theatreID = theatreID;
        this.screen = screen;
        this.seats = seats;
        this.charge = charge;
        this.Showtime = showtime;
    }

    public String getShowtime() {
        return Showtime;
    }

    public void setShowtime(String showtime) {
        Showtime = showtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(String theatreID) {
        this.theatreID = theatreID;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }
}
