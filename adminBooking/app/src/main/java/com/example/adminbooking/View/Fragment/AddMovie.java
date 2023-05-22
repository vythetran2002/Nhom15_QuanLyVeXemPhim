package com.example.adminbooking.View.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminbooking.Controller.MovieController;
import com.example.adminbooking.Model.Bean.Movie;
import com.example.adminbooking.Model.Service.MovieService;
import com.example.adminbooking.R;
import com.example.adminbooking.View.MainActiviryIMP.MainActivityIMP;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddMovie extends Fragment {

    TextInputEditText edtName, edtCast, edtDes, edtLink;
    TextView txtNameImg;
    Button btnAddNew;
    ImageView imageView;
    final int CODE_GRALLERY_REQUEST= 113;
    MovieController movieController;
    MovieService movieServicel;
    Bitmap bitmap;
    DatePicker datePicker;

    public AddMovie() {
        // Required empty public constructor
    }

    public void showToast(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        txtNameImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GRALLERY_REQUEST);
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieController.InsertImageMovieAdd(bitmap, new Movie(null, MainActivityIMP.login.getUserId(),edtName.getText().toString(),edtCast.getText().toString(),edtDes.getText().toString(),datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth(),null,edtLink.getText().toString(),null));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CODE_GRALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), CODE_GRALLERY_REQUEST);
            }else {
                Toast.makeText(getContext(), "you don't have permession to acess gallery", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== CODE_GRALLERY_REQUEST && resultCode == Activity.RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void init(View view) {
        edtName  = view.findViewById(R.id.edtMovieNameAddMovie);
        edtCast = view.findViewById(R.id.edtCaseAddMovie);
        edtDes = view.findViewById(R.id.edtDescriptionAddMovie);
        txtNameImg = view.findViewById(R.id.txtPickImgAddMovie);
        btnAddNew = view.findViewById(R.id.btnAddMovieTheatre);
        imageView = view.findViewById(R.id.imgViewAddMovie);
        datePicker = view.findViewById(R.id.datePickerAddMovie);
        edtLink = view.findViewById(R.id.edtLinkYT);
        movieServicel = new MovieService(getContext());
        movieController = new MovieController(movieServicel, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_movie, container, false);
    }

    public void setTextIMG(String nameImage) {
        txtNameImg.setText(nameImage);
    }
}