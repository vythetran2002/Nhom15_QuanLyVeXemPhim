package model;


public class RunningMovie {
    String name;
    String start_time;
    String screen;
    String nameMovie;

    public RunningMovie(String name, String start_time, String screen, String nameMovie) {
        this.name = name;
        this.start_time = start_time;
        this.screen = screen;
        this.nameMovie = nameMovie;
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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }
}
