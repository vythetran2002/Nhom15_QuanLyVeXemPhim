package com.example.adminbooking.Controller;

import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.Model.Bean.Theatre;
import com.example.adminbooking.Model.Service.TheatreService;
import com.example.adminbooking.View.Fragment.AddThreateFragment;
import com.example.adminbooking.View.Fragment.Theatre_Details;

import java.util.ArrayList;
import java.util.List;

public class ThreatreController {
    AddThreateFragment addThreateFragment;
    TheatreService theatreService;
    Theatre_Details theatre_details;
    public ThreatreController(AddThreateFragment addThreateFragment, TheatreService theatreService){
        this.theatreService = theatreService;
        this.addThreateFragment = addThreateFragment;
    }
    public ThreatreController(Theatre_Details theatre_details, TheatreService theatreService){
        this.theatreService = theatreService;
        this.theatre_details = theatre_details;
    }

    public void LoadTheatre(String id){
        theatreService.LoadTheatre(id, new TheatreService.GetTheatre() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getResponse(Theatre theatre) {
                theatre_details.LoadTheatre(theatre);
            }
        });

    }

    public void addTheatre(Theatre theatre, String username, String password){
        theatreService.addTheatre(theatre, new TheatreService.getIDLogin() {
            @Override
            public void getError(String mess) {

            }

            @Override
            public void getIDlogin(String id) {

                Login login = new Login();
                login.setPassword(password.trim());
                login.setUsername(username.trim());
                login.setUserId(id.trim());
                login.setUserType("1");
                addLogin(login);
            }
        });
    }

    public void addLogin(Login login){
        theatreService.addLogin(login, new TheatreService.getIDLogin() {
            @Override
            public void getError(String mess) {
                addThreateFragment.showToast(mess);
            }

            @Override
            public void getIDlogin(String id) {
                addThreateFragment.showToast("Successful");
            }
        });
    }

}
