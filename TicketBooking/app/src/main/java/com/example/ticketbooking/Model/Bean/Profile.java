package com.example.ticketbooking.Model.Bean;

public class Profile {

    String bookingid;
    String Movie;
    String Theatre;
    String Screen;
    String show;
    String Seats;
    String Amount;
    String status;
    public Profile(String bookingid, String movie, String theatre, String screen, String show, String seats, String amount, String status) {
        this.bookingid = bookingid;
        Movie = movie;
        Theatre = theatre;
        Screen = screen;
        this.show = show;
        Seats = seats;
        Amount = amount;
        this.status = status;
    }

    public String getTheatre() {
        return Theatre;
    }

    public void setTheatre(String theatre) {
        Theatre = theatre;
    }

    public String getScreen() {
        return Screen;
    }

    public void setScreen(String screen) {
        Screen = screen;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getMovie() {
        return Movie;
    }

    public void setMovie(String movie) {
        Movie = movie;
    }
}

