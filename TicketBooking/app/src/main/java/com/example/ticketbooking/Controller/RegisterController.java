package com.example.ticketbooking.Controller;

import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Bean.Registration;
import com.example.ticketbooking.Model.Service.RegisterService;
import com.example.ticketbooking.View.fragment.RegisterFragment;

public class RegisterController {

    RegisterFragment registerFragment;
    RegisterService registerService;

    public RegisterController(RegisterService registerService, RegisterFragment registerFragment){
        this.registerFragment = registerFragment;
        this.registerService = registerService;
    }

    public void addRegister(Login login ,Registration registration){

        registerService.addRegister(registration, new RegisterService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                registerFragment.toastMess(mess);
            }

            @Override
            public void onResponse(String idRegister) {

                login.setUserId(idRegister);
                login.setUserType("2");
                addLogin(login);
            }
        });
    }

    public void addLogin(Login login){
        registerService.addLogin(login, new RegisterService.VolleyResponseListener() {
            @Override
            public void getError(String mess) {
                registerFragment.toastMess(mess);
            }

            @Override
            public void onResponse(String idRegister) {
                registerFragment.successful();
            }
        });
    }

}
