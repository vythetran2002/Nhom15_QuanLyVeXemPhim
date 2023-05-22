package com.example.adminbooking.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.adminbooking.Controller.ThreatreController;
import com.example.adminbooking.Model.Bean.Theatre;
import com.example.adminbooking.Model.Service.TheatreService;
import com.example.adminbooking.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddThreateFragment extends Fragment {

    TextInputEditText edtName, edtAdress, edtPlace, edtState, edtPin, edtUsername, edtPassword;
    ThreatreController theatreController;
    TheatreService theatreService;
    Button btnAdd;

    public AddThreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

    }

    public void showToast(String mess){
        Toast.makeText(getContext(), mess, Toast.LENGTH_LONG).show();
    }

    private void init(View view) {

        edtName = view.findViewById(R.id.edtTheatreName);
        edtAdress = view.findViewById(R.id.edtAddress);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtPin = view.findViewById(R.id.edtPin);
        edtState = view.findViewById(R.id.edtState);
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPlace = view.findViewById(R.id.edtPlace);
        btnAdd = view.findViewById(R.id.btnAddThreatre);

        theatreService = new TheatreService(getContext());
        theatreController = new ThreatreController(this,theatreService);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatreController.addTheatre(new Theatre("",edtName.getText().toString(),
                        edtAdress.getText().toString(),
                        edtPlace.getText().toString(),
                        edtState.getText().toString(),
                        edtPin.getText().toString()), edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtPassword.setError("The Password is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtName.setError("The Theatre Name is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtAdress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtAdress.setError("The Theatre Address is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtPlace.setError("The Place is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtState.setError("The State is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().length() > 6){
                    edtPin.setError("The Pin is invalid");
                }
                if (s.toString().length() <= 0){
                    edtPin.setError("The Pin Code is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0){
                    edtUsername.setError("The Username is required and can't be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_threate, container, false);
    }
}