package com.example.adminbooking.Model.Service;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adminbooking.Model.Bean.ManagerScreen;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.TodayBooking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenService {

    Context context;

    public ScreenService(Context context){
        this.context = context;
    }

    public interface GetArrayScreen{
        void getError(String mess);
        void getResponse(ArrayList<Screen> arrayList);
    }

    public void addScreen(Screen screen){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertScreen", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("t_ids",screen.getTheatreID());
                map.put("Name",screen.getScreen());
                map.put("Seats",screen.getSeats());
                map.put("Charge",screen.getCharge());
                return map;
            }
        };

        requestQueue.add(stringRequest);


    }

    public void AddShowtime(String idScreen,String name, String time){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertTime", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("idcreen", idScreen);
                map.put("Name", name);
                map.put("Time", time);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void LoadScreenByIDTheatre(String idScreen, GetArrayScreen getArrayScreen){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadScreenByIDScreen", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Screen> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Screen screen = new Screen(jsonObject.getString("id"),null,
                                jsonObject.getString("screen"),
                                jsonObject.getString("seats"),
                                jsonObject.getString("charge"),
                                jsonObject.getString("Showtime"));

                        arrayList.add(screen);
                    }

                    getArrayScreen.getResponse(arrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getArrayScreen.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("idScreen",idScreen);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
