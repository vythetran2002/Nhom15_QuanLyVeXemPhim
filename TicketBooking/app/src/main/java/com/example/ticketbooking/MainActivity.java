package com.example.ticketbooking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ticketbooking.View.MainActivity.MainActivityImp;

public class MainActivity extends AppCompatActivity {

    MainActivityImp mainActivityImp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityImp = new MainActivityImp(this, null, getSupportFragmentManager());
        setContentView(mainActivityImp.getRootView());
        mainActivityImp.initViews();
    }
}