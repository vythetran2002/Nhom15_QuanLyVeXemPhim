package com.example.ticketbooking.Model.Service;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ticketbooking.Model.Bean.Profile;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyProfileService {

    Context context;
    public  MyProfileService(Context context) {this.context = context;}

    public interface VolleyResponseListener{
         void onError(String messege);

         void onResponse(List<Profile> profile);
    }

    public  void getProfileByID(String idUser, VolleyResponseListener volleyResponseListener){

        RequestQueue queue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url+"LoadMyProfileByID", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;
                List<Profile> lisProfile = new ArrayList<>();
                try {
                    jsonArray = new JSONArray(response);
                    int length = jsonArray.length();
                    for (int i = 0; i < length; ++i)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       Profile profile = new Profile(jsonObject.getString("bookingid"),
                                jsonObject.getString("Movie"),
                                jsonObject.getString("Theatre"),
                                jsonObject.getString("Screen"),
                                jsonObject.getString("show"),
                                jsonObject.getString("Seats"),
                                jsonObject.getString("Amount"),
                                jsonObject.getString("status"));

                        lisProfile.add(profile);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(lisProfile);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.getMessage());
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("iduser",idUser);

                return map;
            }
        };

        queue.add(stringRequest);
    }

}
