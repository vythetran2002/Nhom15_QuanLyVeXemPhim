package com.example.ticketbooking.Controller;

import android.util.Log;

import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Service.LoginService;
import com.example.ticketbooking.View.fragment.LoginFragment;

public class LoginController {

    LoginFragment loginFragment;
    LoginService loginService;

    public  LoginController(LoginService loginService, LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        this.loginService = loginService;
    }

    public void getInforLogin(String name, String password){
        loginService.getListUsers(name, password, new LoginService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("messss", message);
                loginFragment.wrongLogin(message);
            }

            @Override
            public void onResponse(Login login) {
                if (login != null){
                    loginFragment.loadFragmentProfile(login);
                }
                else{
                    loginFragment.wrongLogin("Username or password wrong");
                }
            }
        });
    }
}
