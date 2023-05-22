package com.example.adminbooking.View.MainActiviryIMP;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.R;
import com.example.adminbooking.View.Fragment.AddMovie;
import com.example.adminbooking.View.Fragment.AddShow;
import com.example.adminbooking.View.Fragment.AddThreateFragment;
import com.example.adminbooking.View.Fragment.HomeFragment;
import com.example.adminbooking.View.Fragment.HomeTheatre;
import com.example.adminbooking.View.Fragment.Theatre_Details;
import com.example.adminbooking.View.Fragment.TodayBookings;
import com.example.adminbooking.View.Fragment.TodayShows;
import com.example.adminbooking.View.Fragment.ViewShow;
import com.example.adminbooking.View.Fragment.upMovieNewsFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivityIMP {

    View rootView;
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    Context context;
    FragmentManager supportFragmentManager;
    public  static Login login;
    public static int checkLogin;

    public MainActivityIMP(Context context, ViewGroup continer, FragmentManager supportFragmentManager){
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_main,continer);
        this.context = context;
        this.supportFragmentManager = supportFragmentManager;
    }

    public View getRootView() {
        return rootView;
    }

    public void initViewTheatre(Login login){
        sNavigationDrawer = rootView.findViewById(R.id.navigationDrawer);
        this.login = login;
        List<MenuItem> menuItems;
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.drawable.news_bg));
        menuItems.add(new MenuItem("Add Movie",R.drawable.feed_bg));
        menuItems.add(new MenuItem("View Movie",R.drawable.message_bg));
        menuItems.add(new MenuItem("Add Show",R.drawable.music_bg));
        menuItems.add(new MenuItem("Today Show",R.drawable.message_bg));
        menuItems.add(new MenuItem("Today Bookings",R.drawable.feed_bg));
        menuItems.add(new MenuItem("View Show",R.drawable.news_bg));
        menuItems.add(new MenuItem("Threatre Details",R.drawable.music_bg));
        menuItems.add(new MenuItem("Log out",R.drawable.feed_bg));
        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  HomeTheatre.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = supportFragmentManager;
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);
                switch (position){
                    case 0:{
                        fragmentClass = HomeTheatre.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = AddMovie.class;
                        break;
                    }
                    case 2:{

                        fragmentClass = HomeFragment.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = AddShow.class;
                        break;
                    }
                    case 4:{
                        fragmentClass = TodayShows.class;
                        break;
                    }
                    case 5:{
                        fragmentClass = TodayBookings.class;
                        break;
                    }
                    case 6:{
                        fragmentClass = ViewShow.class;
                        break;
                    }
                    case 7:{
                        fragmentClass = Theatre_Details.class;

                        break;
                    }
                    case 8:{
                        ((Activity)context).finish();
                        break;
                    }

                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = supportFragmentManager;
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });
    }

    public void initViews() {

        sNavigationDrawer = rootView.findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems;

        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("HOME",R.drawable.news_bg));
        menuItems.add(new MenuItem("Add Theatre",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Upcoming Movies News",R.drawable.message_bg));
        menuItems.add(new MenuItem("Log out",R.drawable.music_bg));

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  HomeFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = supportFragmentManager;
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);
                switch (position){
                    case 0:{
                        fragmentClass = HomeFragment.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = AddThreateFragment.class;
                        break;
                    }
                    case 2:{

                        fragmentClass = upMovieNewsFragment.class;
                        break;
                    }
                    case 3:{

                        ((Activity)context).finish();
                        break;
                    }

                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = supportFragmentManager;
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }
}
