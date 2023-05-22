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
import androidx.core.app.ActivityCompat;
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
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class upMovieNewsFragment extends Fragment {

    TextInputEditText edtName, edtCast, edtDes;
    TextView txtNameImg;
    Button btnAddNew;
    ImageView imageView;
    final int CODE_GRALLERY_REQUEST= 113;
    MovieController movieController;
    MovieService movieServicel;
    Bitmap bitmap;
    DatePicker datePicker;

    public upMovieNewsFragment() {
        // Required empty public constructor
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
                movieController.InsertImage(bitmap, new Movie(null,null,edtName.getText().toString(),edtCast.getText().toString(),edtDes.getText().toString(),datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth(),null,null,null));
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

    public void setTextIMG(String mess){
        txtNameImg.setText(mess);
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
        edtName  = view.findViewById(R.id.edtMovieName);
        edtCast = view.findViewById(R.id.edtCaseMovieNews);
        edtDes = view.findViewById(R.id.edtDescription);
        txtNameImg = view.findViewById(R.id.txtPickImg);
        btnAddNew = view.findViewById(R.id.btnAddMovies);
        imageView = view.findViewById(R.id.imgViewMovieNews);
        datePicker = view.findViewById(R.id.datePicker);
        movieServicel = new MovieService(getContext());
        movieController = new MovieController(movieServicel, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_movie_news, container, false);
    }

    public void showToast(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_LONG).show();

    }
}