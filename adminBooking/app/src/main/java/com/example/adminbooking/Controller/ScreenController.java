package com.example.adminbooking.Controller;

import com.example.adminbooking.Model.Bean.ManagerScreen;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Service.ScreenService;
import com.example.adminbooking.View.Fragment.Theatre_Details;

import java.util.ArrayList;

public class ScreenController {

    ScreenService screenService;
    Theatre_Details theatre_details;

    public ScreenController( ScreenService screenService,  Theatre_Details theatre_details){
        this.screenService = screenService;
        this.theatre_details = theatre_details;
    }

    public void AddScreen(Screen screen){
        screenService.addScreen(screen);
    }

    public void LoadScreen(String idTheatre){

        screenService.LoadScreenByIDTheatre(idTheatre, new ScreenService.GetArrayScreen() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(ArrayList<Screen> arrayList) {
                theatre_details.setAdapter(convertDataScreen(arrayList));
            }
        });

    }

    public void addTime(String idScreen, String name, String time){
        screenService.AddShowtime(idScreen, name, time);
    }

    private ArrayList<ManagerScreen> convertDataScreen(ArrayList<Screen> arrayListMovie){

        ArrayList<ManagerScreen> arrayList = new ArrayList<>();
        if (arrayListMovie.size() > 0) {

            int i = 0;
            int j = 1;

            ArrayList<String> arrayList1 = new ArrayList<>();
            if (!arrayListMovie.get(0).getShowtime().equals("0")){
                arrayList1.add(arrayListMovie.get(0).getShowtime());
            }

            ManagerScreen mm = new ManagerScreen(arrayListMovie.get(0), arrayList1);

            arrayList.add(mm);
            int count = 0;

            while (i < j) {
                if (j >= arrayListMovie.size()) {
                    break;
                }
                if (arrayList.get(count).getScreen().getScreen().equals(arrayListMovie.get(j).getScreen())) {
                    arrayList.get(count).getListStartTime().add(arrayListMovie.get(j).getShowtime());
                } else {
                    ArrayList<String> tmp = new ArrayList<>();
                    if (!arrayListMovie.get(j).getShowtime().equals("0")){
                        tmp.add(arrayListMovie.get(j).getShowtime());
                    }
                    ManagerScreen managertmp = new ManagerScreen(arrayListMovie.get(j), tmp);

                    arrayList.add(managertmp);
                    ++count;
                    i = j;
                }
                ++j;
            }
        }
        return  arrayList;
    }
}
