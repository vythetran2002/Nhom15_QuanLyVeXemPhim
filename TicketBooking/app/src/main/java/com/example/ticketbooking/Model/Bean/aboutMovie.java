package com.example.ticketbooking.Model.Bean;

public class aboutMovie {
    private  String id;
    private  String theatre;
    private  String place;
    private  String starttime;
    private  String idScreen;
    private  String screenname;
    private  String idShowtime;
    private  String nametime;
    private  String charge;
    private  String seats;
    private String Date;
    private  String time_booking;
    private  String s_id;
    public aboutMovie(String id, String theatre, String place, String starttime, String idScreen, String screenname, String idShowtime, String nametime, String charge, String seats, String s_id) {
        this.id = id;
        this.theatre = theatre;
        this.place = place;
        this.starttime = starttime;
        this.idScreen = idScreen;
        this.screenname = screenname;
        this.idShowtime = idShowtime;
        this.nametime = nametime;
        this.charge = charge;
        this.seats = seats;
        this.s_id = s_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getTime_booking() {
        return time_booking;
    }

    public void setTime_booking(String time_booking) {
        this.time_booking = time_booking;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getIdScreen() {
        return idScreen;
    }

    public void setIdScreen(String idScreen) {
        this.idScreen = idScreen;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(String idShowtime) {
        this.idShowtime = idShowtime;
    }

    public String getNametime() {
        return nametime;
    }

    public void setNametime(String nametime) {
        this.nametime = nametime;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
