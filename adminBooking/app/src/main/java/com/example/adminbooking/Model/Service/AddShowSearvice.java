package com.example.adminbooking.Model.Service;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adminbooking.Model.Bean.Screen;
import com.example.adminbooking.Model.Bean.Show;
import com.example.adminbooking.Model.Bean.Showtime;
import com.example.adminbooking.Model.Bean.TodayBooking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddShowSearvice {

    Context context;

    public AddShowSearvice(Context context){
        this.context = context;
    }

    public interface GetDataScreen{
        void getError(String mess);
        void getResponse(ArrayList<Screen> arrayList);
    }

    public interface GetDataShowtime{
        void getError(String mess);
        void getResponse(ArrayList<Showtime> arrayList);
    }

    public interface GetDataBooking{
        void getError(String mess);
        void getResponse(ArrayList<TodayBooking> arrayList);
    }

    public interface GetDataShow{
        void getError(String mess);
        void getResponse(ArrayList<Show> arrayList);
    }
    public void LoadTodayBookingByScreen(String idScreen, GetDataBooking getDataBooking){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadTodayBooking", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<TodayBooking> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TodayBooking todayBooking = new TodayBooking(jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("phone"),
                                jsonObject.getString("seats"));

                        arrayList.add(todayBooking);
                    }

                    getDataBooking.getResponse(arrayList);

                  } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataBooking.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("idScreen", idScreen);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }



    public void UpdateShowStatus(String status, String idShow){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "updateShow", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                map.put("Status", status);
                map.put("idshow", idShow);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void StopShow(String status, String idShow){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "stopShow", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                map.put("Status", status);
                map.put("idshow", idShow);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void LoadAllShow(String status, GetDataShow getDataShow){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadAllShow", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Show> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Show show = new Show(jsonObject.getString("id"),
                                jsonObject.getString("showTime"),
                                jsonObject.getString("theatre"),
                                jsonObject.getString("movie"),
                                jsonObject.getString("date"),
                                jsonObject.getString("status"),
                                jsonObject.getString("run_status"));

                        arrayList.add(show);
                    }
                    getDataShow.getResponse(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataShow.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("idTheatre", status);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void LoadShow(String status, GetDataShow getDataShow){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadShow", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Show> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Show show = new Show(jsonObject.getString("id"),
                                jsonObject.getString("theatre"),
                                jsonObject.getString("showTime"),
                                jsonObject.getString("movie"),
                                jsonObject.getString("date"),
                                jsonObject.getString("status"),
                                jsonObject.getString("run_status"));

                        arrayList.add(show);
                    }
                    getDataShow.getResponse(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataShow.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("idTheatre", status);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void InsertShow(Show show){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertShow", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Showtime> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Showtime showtime = new Showtime(jsonObject.getString("id"),
                                jsonObject.getString("screen"),
                                jsonObject.getString("name"),
                                jsonObject.getString("namestart"));

                        arrayList.add(showtime);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                map.put("st_id", show.getShowTime());
                map.put("theatre_id", show.getTheatre());
                map.put("movie_id", show.getMovie());
                map.put("date", show.getDate());

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void LoadShowtimeByScreen(String idScreen, GetDataShowtime getDataShowtime){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadShowtime", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Showtime> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Showtime showtime = new Showtime(jsonObject.getString("id"),
                                jsonObject.getString("screen_id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("start_time"));

                        arrayList.add(showtime);
                    }

                    getDataShowtime.getResponse(arrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataShowtime.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("idscreen", idScreen);

                return map;
            }
        };
        requestQueue.add(stringRequest);


    }

    public void LoadScreenByIDTheatre(String idTheatre, GetDataScreen getDataScreen){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadScreenByIDTheatre", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Screen> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); ++i) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Screen screen = new Screen(jsonObject.getString("id"),
                                jsonObject.getString("theatreID"),
                                jsonObject.getString("screen"),
                                jsonObject.getString("seats"),
                                jsonObject.getString("charge"));

                        arrayList.add(screen);
                    }

                    getDataScreen.getResponse(arrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataScreen.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("idtheatre", idTheatre);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
