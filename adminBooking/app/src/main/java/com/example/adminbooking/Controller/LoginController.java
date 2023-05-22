package com.example.adminbooking.Controller;

import android.util.Log;

import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.Model.Service.LoginService;
import com.example.adminbooking.View.LoginActivity.LoginActivity;

public class LoginController {

    LoginActivity loginFragment;
    LoginService loginService;

    public  LoginController(LoginService loginService, LoginActivity loginFragment) {
        this.loginFragment = loginFragment;
        this.loginService = loginService;
    }

    public void getInforLogin(String name, String password){
        loginService.getListUsers(name, password, new LoginService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                loginFragment.getToast(message);
            }

            @Override
            public void onResponse(Login login) {

                loginFragment.sendBundle(login);
            }
        });
    }
}
