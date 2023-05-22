package com.example.ticketbooking.Controller;

import com.example.ticketbooking.Model.Bean.Profile;
import com.example.ticketbooking.Model.Service.MyProfileService;
import com.example.ticketbooking.View.fragment.MyProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MyProfileController {

    MyProfileFragment myProfileFragment;
    MyProfileService myProfileService;

    public MyProfileController( MyProfileFragment myProfileFragment,  MyProfileService myProfileService){
        this.myProfileFragment = myProfileFragment;
        this.myProfileService = myProfileService;
    }

    public void LoadProfile(String idUser){

        myProfileService.getProfileByID(idUser, new MyProfileService.VolleyResponseListener() {
            @Override
            public void onError(String messege) {
                myProfileFragment.getMessager(messege);
            }

            @Override
            public void onResponse(List<Profile> profile) {
                myProfileFragment.setDataAdapter((ArrayList<Profile>) profile);
            }
        });

    }

}
