package com.example.ticketbooking.Model.Service;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Bean.Profile;
import com.example.ticketbooking.Model.Bean.Registration;
import com.example.ticketbooking.Model.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterService {

    Context context;


    public RegisterService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void getError(String mess);

        void onResponse(String idRegister);

    }

    public  void addLogin(Login login,VolleyResponseListener volleyResponseListener ){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertLogin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyResponseListener.onResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.getError(error.getMessage());
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idUser", login.getUserId());
                map.put("username", login.getUsername().trim());
                map.put("password", login.getPassword().trim());
                map.put("type", login.getUserType().trim());

                return map;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void addRegister(Registration registration, VolleyResponseListener volleyResponseListener){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertRegistration", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyResponseListener.onResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.getError(error.getMessage());
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", registration.getName());
                map.put("email", registration.getEmail());
                map.put("phone", registration.getPhone());
                map.put("age", registration.getAge());
                map.put("gender", registration.getGender());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
