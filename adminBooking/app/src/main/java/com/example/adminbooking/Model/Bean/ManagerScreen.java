package com.example.adminbooking.Model.Bean;

import java.util.ArrayList;

public class ManagerScreen {

    Screen screen;
    ArrayList<String> listStartTime;

    public ManagerScreen(Screen screen, ArrayList<String> listStartTime) {
        this.screen = screen;
        this.listStartTime = listStartTime;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public ArrayList<String> getListStartTime() {
        return listStartTime;
    }

    public void setListStartTime(ArrayList<String> listStartTime) {
        this.listStartTime = listStartTime;
    }
}
