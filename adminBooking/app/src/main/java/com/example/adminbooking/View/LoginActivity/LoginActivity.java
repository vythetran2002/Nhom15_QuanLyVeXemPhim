package com.example.adminbooking.View.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminbooking.Controller.LoginController;
import com.example.adminbooking.MainActivity;
import com.example.adminbooking.Model.Bean.Login;
import com.example.adminbooking.Model.Service.LoginService;
import com.example.adminbooking.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    LoginController loginController;
    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.getInforLogin(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
            }
        });
    }

    public void sendBundle(Login login){
        if (login == null){
            Toast.makeText(this, "Username or wrong password", Toast.LENGTH_LONG).show();
            return;
        }
        if (!login.getUserType().equals("2")){
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("loginInfor", login);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Username or wrong password", Toast.LENGTH_LONG).show();
        }
    }

    public void getToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    private void init() {
        edtUsername = findViewById(R.id.editTextTextPersonName);
        edtPassword = findViewById(R.id.editTextTextPersonName2);
        btnLogin = findViewById(R.id.button);

        loginService = new LoginService(this);
        loginController = new LoginController(loginService, this);
    }
}