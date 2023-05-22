package com.example.ticketbooking.Model.Bean;

public class MovieNews {

   private String id;
    private String name;
    private String cast;
    private String release_date;
    private String description;
    private String attachment;

    public MovieNews(String id, String name, String cast, String release_date, String description, String attachment) {
        this.id = id;
        this.name = name;
        this.cast = cast;
        this.release_date = release_date;
        this.description = description;
        this.attachment = attachment;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
