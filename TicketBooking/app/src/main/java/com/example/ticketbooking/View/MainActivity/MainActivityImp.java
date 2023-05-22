package com.example.ticketbooking.View.MainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.fragment.HomeFragment;
import com.example.ticketbooking.View.fragment.LoginFragment;
import com.example.ticketbooking.View.fragment.MoviesFragment;
import com.example.ticketbooking.View.fragment.MyProfileFragment;
import com.example.ticketbooking.View.fragment.RegisterFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivityImp implements  MVCview{

    View rootView;
    SNavigationDrawer sNavigationDrawer;
    int color1=0;
    Class fragmentClass;
    public static Fragment fragment;
    Context context;
    FragmentManager supportFragmentManager;
    List<MenuItem> menuItems;

    public static int checkLogin;
    public  static Login login;
    public MainActivityImp(Context context, ViewGroup continer, FragmentManager supportFragmentManager){
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_main,continer);
        this.context = context;
        this.supportFragmentManager = supportFragmentManager;
    }

    @Override
    public View getRootView() {
        return rootView;
    }



    @Override
    public void initViews() {

        sNavigationDrawer = rootView.findViewById(R.id.navigationDrawer);
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("HOME",R.drawable.news_bg));
        menuItems.add(new MenuItem("MOVIES",R.drawable.feed_bg));
        menuItems.add(new MenuItem("MY PROFILE",R.drawable.message_bg));
        menuItems.add(new MenuItem("REGISTER",R.drawable.music_bg));
        checkLogin = 0;
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
                        fragmentClass = MoviesFragment.class;
                        break;
                    }
                    case 2:{
                        if (checkLogin == 1)
                        {
                            fragmentClass = MyProfileFragment.class;
                        }
                        else
                        {
                            fragmentClass = LoginFragment.class;
                        }
                        break;
                    }
                    case 3:{
                        fragmentClass = RegisterFragment.class;
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
