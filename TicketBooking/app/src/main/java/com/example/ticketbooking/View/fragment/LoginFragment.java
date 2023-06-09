package com.example.ticketbooking.View.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketbooking.Controller.LoginController;
import com.example.ticketbooking.MainActivity;
import com.example.ticketbooking.Model.Bean.Login;
import com.example.ticketbooking.Model.Service.LoginService;
import com.example.ticketbooking.R;
import com.example.ticketbooking.View.MainActivity.MainActivityImp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    LoginService loginService;
    LoginController loginController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnLogin;
    EditText edtUsername, edtPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.getInforLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

    }

    public void wrongLogin(String mess){
        Toast.makeText(getContext(),mess, Toast.LENGTH_SHORT).show();
    }

    public  void loadFragmentProfile(Login login){
        MainActivityImp.checkLogin = 1;
        Fragment fragment = new MyProfileFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.frameLayout)).commit();

        MainActivityImp.login = login;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment ,null);
        fragmentTransaction.commit();
    }


    public void init(View view) {

        btnLogin = view.findViewById(R.id.btnLogin);
        edtPassword = view.findViewById(R.id.edtPasswod);
        edtUsername = view.findViewById(R.id.edtUsername);

        loginService = new LoginService(getContext());
        loginController = new LoginController(loginService, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}