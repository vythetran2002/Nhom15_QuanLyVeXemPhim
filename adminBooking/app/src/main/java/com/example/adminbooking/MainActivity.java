package com.example.adminbooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityIMP mainActivityIMP = new MainActivityIMP(this, null, getSupportFragmentManager());
        setContentView(mainActivityIMP.getRootView());

        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        Login login = (Login) b.getSerializable("loginInfor");

        if (login.getUserType().equals("0")){
            mainActivityIMP.initViews();
        }
        else if (login.getUserType().equals("1")){
            mainActivityIMP.initViewTheatre( login);
        }

    }
}