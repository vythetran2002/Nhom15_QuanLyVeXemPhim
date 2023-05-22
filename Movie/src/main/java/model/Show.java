package model;


public class Show {

    String id;
    String showTime;
    String theatre;
    String movie;
    String date;
    String status;
    String run_status;

    public Show() {
    	
    }

    public Show(String id, String showTime, String theatre, String movie, String date, String status, String run_status) {
        this.id = id;
        this.showTime = showTime;
        this.theatre = theatre;
        this.movie = movie;
        this.date = date;
        this.status = status;
        this.run_status = run_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRun_status() {
        return run_status;
    }

    public void setRun_status(String run_status) {
        this.run_status = run_status;
    }
}
