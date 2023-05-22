package com.example.ticketbooking.Model.Service;

import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ticketbooking.Model.Bean.Movie;
import com.example.ticketbooking.Model.Bean.MovieNews;
import com.example.ticketbooking.Model.Bean.aboutMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public interface VolleyResponseListenerDetail{
        void getError(String mess);
        void onResponse(ArrayList<aboutMovie> arrayListMovie);
    }

    public interface VolleyResponseListener{
        void getError(String mess);
        void onResponse(ArrayList<Movie> ArrayListMovie);
    }

    public interface VolleyResponseListenerUpcoming{
        void getError(String mess);
        void onResponse(ArrayList<MovieNews> ArrayListMovie);
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



    public  void insertBooking(aboutMovie aboutMovie, String idUser){
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url + "insertBooking", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(""+error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("t_ids", aboutMovie.getId());
                map.put("user_ids",idUser);
                map.put("show_ids",aboutMovie.getS_id());
                map.put("screen_ids",aboutMovie.getIdScreen());
                map.put("seatss",aboutMovie.getSeats());
                map.put("amounts",aboutMovie.getCharge());
                map.put("date_ticks", aboutMovie.getDate());
                map.put("dates",aboutMovie.getTime_booking());

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void LoadDetailMovie(String idMovie, VolleyResponseListenerDetail volleyResponseListenerDetail){

        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MySingleton.url+"LoadMovieDetail", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<aboutMovie> arrayListMovie = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int  i = 0; i <jsonArray.length(); ++i){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        aboutMovie aboutMovie = new aboutMovie(jsonObject.getString("id"),
                                jsonObject.getString("theatre"),
                                jsonObject.getString("place"),
                                jsonObject.getString("starttime"),
                                jsonObject.getString("idScreen"),
                                jsonObject.getString("screenname"),
                                jsonObject.getString("idShowtime"),
                                jsonObject.getString("nametime"),
                                jsonObject.getString("charge"),
                                jsonObject.getString("seats"),
                                jsonObject.getString("s_id")
                                );
                        arrayListMovie.add(aboutMovie);
                    }

                    volleyResponseListenerDetail.onResponse(arrayListMovie);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListenerDetail.getError(error.getMessage());
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("idmovie",idMovie);
                return map;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void LoadUpconmingMovie(VolleyResponseListenerUpcoming volleyResponseListenerUpcoming){
        RequestQueue queue = MySingleton.getInstance(context).
                getRequestQueue();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, MySingleton.url+"LoadUpcomingMovie", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<MovieNews> ArrayListMovie = new ArrayList<>();
                for (int i = 0; i < response.length(); ++i){

                    try {

                        JSONObject obj = response.getJSONObject(i);
                        MovieNews movie = new MovieNews(obj.getString("id"),
                                obj.getString("name"),
                                obj.getString("cast"),
                                obj.getString("release_date"),
                                obj.getString("description"),
                                obj.getString("attachment")
                        );

                        ArrayListMovie.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                volleyResponseListenerUpcoming.onResponse(ArrayListMovie);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListenerUpcoming.getError(error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

}
