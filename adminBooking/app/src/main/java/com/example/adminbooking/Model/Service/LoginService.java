package com.example.adminbooking.Model.Service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.adminbooking.Model.Bean.Login;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginService {

    Context context;

    public LoginService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Login login);
    }


    public void  getListUser(String username, String password, VolleyResponseListener listener) {


        RequestQueue queue = MySingleton.getInstance(context).
                getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, MySingleton.url + "/Login", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        Login login = new Login(jsonObject.getString("id"),
                                jsonObject.getString("user_id"),
                                jsonObject.getString("username"),
                                jsonObject.getString("password"),
                                jsonObject.getString("user_type"));

                        listener.onResponse(login);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("username", username.trim());
                map.put("password", password.trim());

                return map;
            }
        };
        queue.add(jsonArrayRequest);
    }

    public void  getListUsers(String username, String password, VolleyResponseListener listener) {


        RequestQueue queue = MySingleton.getInstance(context).
                getRequestQueue();
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoginAdmin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.length() > 0) {
                    Login login = null;
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                         login = new Login(jsonObject.getString("id"),
                                jsonObject.getString("userId"),
                                jsonObject.getString("username"),
                                jsonObject.getString("password"),
                                jsonObject.getString("userType"));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listener.onResponse(login);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                listener.onError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("username", username.trim());
                map.put("password", password.trim());

                return map;
            }
        };


        queue.add(jsonArrayRequest);
    }

}
