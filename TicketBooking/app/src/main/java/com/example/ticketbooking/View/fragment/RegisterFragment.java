package com.example.ticketbooking.View.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.ticketbooking.Controller.RegisterController;
import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Bean.Registration;
import com.example.ticketbooking.Model.Service.RegisterService;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.MainActivity.MainActivityImp;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class RegisterFragment extends Fragment {

    TextInputEditText txtName;
    TextInputEditText txtAge;
    TextInputEditText txtMobile;
    TextInputEditText txtEmail;
    TextInputEditText txtPass;
    TextInputEditText txtConfirm;
    MaterialButton btnResgeter;
    AutoCompleteTextView autoCompleteTextView;
    RegisterController registerController;
    RegisterService registerService;
    AlertDialog.Builder builder;

    public RegisterFragment() {
        // Required empty public constructor
    }
    public  void CheckLoginRegister(){
        if (MainActivityImp.checkLogin == 1){
            builder.setMessage("Must log out before using this function")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivityImp.login = null;
                            MainActivityImp.checkLogin = 0;
                            dialog.cancel();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("AlertDialogExample");
            alert.show();
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        checkValue();
        CheckLoginRegister();
        btnResgeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLoginRegister();
                if (MainActivityImp.login == null){
                    Registration registration = new Registration(txtName.getText().toString(),
                            txtEmail.getText().toString(),
                            txtMobile.getText().toString(),
                            txtAge.getText().toString(),
                            autoCompleteTextView.getText().toString());
                    Login login = new Login();
                    login.setUsername(txtEmail.getText().toString());
                    login.setPassword(txtPass.getText().toString());

                    registerController.addRegister(login,registration);
                }
            }
        });
    }



    public void checkValue(){

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() <= 0){
                    txtName.setError("The Name is required and can't be empty");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    txtAge.setError("The Age is required and can't be empty");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    txtMobile.setError("The Mobile Number is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    txtEmail.setError("The Email is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() < 7){
                    txtPass.setError("The Password must be more than 7 characters long");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() < 7){
                    txtConfirm.setError("The Password must be more than 7 characters long");
                    return;
                }

                if (!s.toString().equals(txtPass.getText().toString())){
                    txtConfirm.setError("Do not match the imported password");
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void init(View view){
        builder = new AlertDialog.Builder(getContext());

        registerService = new RegisterService(getContext());
        registerController = new RegisterController(registerService, this);
        btnResgeter = view.findViewById(R.id.mbtnRegister);
        txtName = view.findViewById(R.id.titName);
        txtAge = view.findViewById(R.id.titAge);
        txtMobile = view.findViewById(R.id.titPhone);
        txtEmail = view.findViewById(R.id.titEmail);
        txtPass = view.findViewById(R.id.titPassword);
        txtConfirm = view.findViewById(R.id.titPasswordConfirm);
        autoCompleteTextView = view.findViewById(R.id.autocompe);

        String[] Gender = new String[]{"Male","Female" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line,
                Gender
                );
        autoCompleteTextView.setAdapter(adapter);

    }

    public  void toastMess(String mess){
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    public void successful(){

        builder.setMessage("Sign Up Success!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Fragment fragment = new LoginFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.frameLayout)).commit();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.frameLayout, fragment ,null);
                        fragmentTransaction.commit();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetController();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("AlertDialogExample");
        alert.show();
    }

    public void resetController(){
        txtAge.setText("");
        txtPass.setText("");
        txtConfirm.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
        txtName.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}