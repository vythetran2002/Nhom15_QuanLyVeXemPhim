package com.example.adminbooking.Model.Service;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.Model.Bean.RunningMovie;
import com.example.adminbooking.Model.Bean.Theatre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TheatreService {
    Context context;

    public TheatreService(Context context){
        this.context = context;
    }

    public interface GetTheatre{
        void getError(String mess);
        void getResponse(Theatre theatre);
    }

    public void LoadTheatre(String id, GetTheatre getTheatre) {
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadTheatreByID", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jsonArray.length(); ++i){
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Theatre theatre = new Theatre(obj.getString("id"),
                                obj.getString("name"),
                                obj.getString("Address"),
                                obj.getString("place"),
                                obj.getString("state"),
                                obj.getString("pin"));

                        getTheatre.getResponse(theatre);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getTheatre.getError(error.getMessage());

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idTheatre", id);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }

    public interface getIDLogin{
        void getError(String mess);
        void getIDlogin(String id);
    }

    public  void addLogin(Login login, getIDLogin getIDLogin){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertLogin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getIDLogin.getIDlogin(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getIDLogin.getError(error.getMessage());

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idUser", login.getUserId().trim());
                map.put("username", login.getUsername().trim());
                map.put("password", login.getPassword().trim());
                map.put("type", login.getUserType().trim());

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void addTheatre(Theatre theatre, getIDLogin getIDLogin){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertTheatre", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getIDLogin.getIDlogin(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getIDLogin.getError(error.getMessage());
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name",theatre.getName());
                map.put("address", theatre.getAddress());
                map.put("place", theatre.getPlace());
                map.put("state", theatre.getState());
                map.put("pin", theatre.getPin());

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


}
