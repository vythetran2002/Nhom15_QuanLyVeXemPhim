package com.example.adminbooking.Model.Service;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.Model.Bean.Movie;
import com.example.adminbooking.Model.Bean.RunningMovie;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MovieService {

    Context context;
    public MovieService(Context context){
        this.context = context;
    }


    public void LoadMoviesRunning(String idTheatre, getArrayRunning getArrayRunning){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "LoadRuningMovie", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<RunningMovie> ArrayListMovie = new ArrayList<>();
                JSONArray jsonArray = null;
                try {
                     jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jsonArray.length(); ++i){

                    try {

                        JSONObject obj = jsonArray.getJSONObject(i);
                        RunningMovie runningMovie = new RunningMovie(obj.getString("name"),
                                obj.getString("start_time"),
                                obj.getString("screen"),
                                obj.getString("nameMovie"));


                        ArrayListMovie.add(runningMovie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                getArrayRunning.onResponse(ArrayListMovie);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String > map = new HashMap<>();
                map.put("idTheatre", idTheatre);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void DeleteMovieByID(String id) {
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "DeleteMovieByID", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ad",error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("idMovie", id);
                return  map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public interface getArrayRunning{
        void getError(String mess);
        void onResponse(ArrayList<RunningMovie> ArrayListMovieRunning);
    }

    public interface VolleyResponseListener{
        void getError(String mess);
        void onResponse(ArrayList<Movie> ArrayListMovie);
    }

    public interface getLinkListener{
        void getError(String mess);
        void onResponse(String nameImage);
    }

    public String imgToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte []imagebyte = outputStream.toByteArray();

        String encodeImage = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encodeImage;
    }

    public  void addMovieTheatre(Movie movie, getLinkListener getLinkListener){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertMovieAdd", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getLinkListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getLinkListener.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map =  new HashMap<>();
                map.put("name", movie.getMovie_name());
                map.put("cast", movie.getCast());
                map.put("release", movie.getRelease_date());
                map.put("description", movie.getDesc());
                map.put("images", movie.getImage());
                map.put("link",movie.getVideo_url());
                map.put("idtheatre",movie.getThreatre_id());
                return  map;
            }
        };

        requestQueue.add(stringRequest);


    }

    public  void addMovieNews(Movie movie, getLinkListener getLinkListener){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertMovieNews", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getLinkListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getLinkListener.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map =  new HashMap<>();
                map.put("name", movie.getMovie_name());
                map.put("cast", movie.getCast());
                map.put("release", movie.getRelease_date());
                map.put("description", movie.getDesc());
                map.put("images", movie.getImage());
                return  map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void InsertImage(Bitmap bitmap, getLinkListener getLinkListener){

       RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

       StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertImage", new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
                getLinkListener.onResponse(response);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
                getLinkListener.getError(error.getMessage());
           }
       }){
           @Nullable
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {

               Map<String, String> map =  new HashMap<>();
               map.put("image", imgToString(bitmap));
               return  map;
           }
       };

       requestQueue.add(stringRequest);
    }

    public void InsertImageAddMoie(Bitmap bitmap, getLinkListener getLinkListener){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "InsertImageAddMovie", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getLinkListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getLinkListener.getError(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map =  new HashMap<>();
                map.put("image", imgToString(bitmap));
                return  map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void  LoadMovies(VolleyResponseListener volleyResponseListener){
        RequestQueue queue = MySingleton.getInstance(context).
                getRequestQueue();

      JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, MySingleton.url+"LoadMovie", null, new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
              ArrayList<Movie> ArrayListMovie = new ArrayList<>();
              for (int i = 0; i < response.length(); ++i){

                  try {

                      JSONObject obj = response.getJSONObject(i);
                      Movie movie = new Movie(obj.getString("movie_id"),
                              obj.getString("threatre_id"),
                              obj.getString("movie_name"),
                              obj.getString("cast"),
                              obj.getString("desc"),
                              obj.getString("release_date"),
                              obj.getString("image"),
                              obj.getString("Video_url"),
                              obj.getString("status")
                              );

                      ArrayListMovie.add(movie);

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }


              }
              volleyResponseListener.onResponse(ArrayListMovie);

          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
                volleyResponseListener.getError(error.getMessage());
          }
      });

      queue.add(jsonArrayRequest);
    }

}
