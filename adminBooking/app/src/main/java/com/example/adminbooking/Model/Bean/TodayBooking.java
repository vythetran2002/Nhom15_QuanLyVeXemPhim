package com.example.adminbooking.Model.Bean;

public class TodayBooking {

    String id;
    String name;
    String seats;
    String phone;

    public TodayBooking(String id, String name, String seats, String phone) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
