package com.example.adminbooking.Model.Bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    String movie_id;
    @SerializedName("idtheatre")
    String threatre_id;
    String movie_name;
    String cast;
    String desc;
    String release_date;
    String image;
    @SerializedName("link")
    String Video_url;
    String status;

    public Movie(String movie_id, String threatre_id, String movie_name, String cast, String desc, String release_date, String image, String video_url, String status) {
        this.movie_id = movie_id;
        this.threatre_id = threatre_id;
        this.movie_name = movie_name;
        this.cast = cast;
        this.desc = desc;
        this.release_date = release_date;
        this.image = image;
        Video_url = video_url;
        this.status = status;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getThreatre_id() {
        return threatre_id;
    }

    public void setThreatre_id(String threatre_id) {
        this.threatre_id = threatre_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo_url() {
        return Video_url;
    }

    public void setVideo_url(String video_url) {
        Video_url = video_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
