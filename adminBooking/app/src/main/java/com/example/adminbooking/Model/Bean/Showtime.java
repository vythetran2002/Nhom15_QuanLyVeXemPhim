package com.example.adminbooking.Model.Bean;

public class Showtime {
    String id;
    String screen_id;
    String name;
    String start_time;

    public Showtime(String id, String screen_id, String name, String start_time) {
        this.id = id;
        this.screen_id = screen_id;
        this.name = name;
        this.start_time = start_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(String screen_id) {
        this.screen_id = screen_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
