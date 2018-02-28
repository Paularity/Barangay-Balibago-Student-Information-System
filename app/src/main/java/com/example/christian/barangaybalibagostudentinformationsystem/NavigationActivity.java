package com.example.christian.barangaybalibagostudentinformationsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Christian on 26/02/2018.
 */

public class NavigationActivity extends AppCompatActivity {


    Button btn_register,btn_viewProfile,btn_dailyRecord,btn_logout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_layout);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_viewProfile = (Button) findViewById(R.id.btn_viewProfile);
        btn_dailyRecord = (Button) findViewById(R.id.btn_dailyRecord);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, RegisterStudent.class);
                startActivity(intent);
            }
        });

        btn_viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(NavigationActivity.this, StudentList.class);
                    startActivity(intent);
            }
        });

        btn_dailyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, DailyRecordList.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }



}
