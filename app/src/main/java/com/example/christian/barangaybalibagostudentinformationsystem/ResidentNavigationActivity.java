package com.example.christian.barangaybalibagostudentinformationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Christian on 26/02/2018.
 */

public class ResidentNavigationActivity extends AppCompatActivity {


    Button btn_register,btn_viewProfile,btn_viewID,btn_logout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_student_layout);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_viewProfile = (Button) findViewById(R.id.btn_viewProfile);
        btn_viewID = (Button) findViewById(R.id.btn_viewID);


        final String username;
        final String password;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
                password = null;
            } else {
                username = extras.getString("username");
                password = extras.getString("password");

            }
        } else {
            username= (String) savedInstanceState.getSerializable("username");
            password = (String) savedInstanceState.getSerializable("password");
        }


        btn_viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(ResidentNavigationActivity.this, ResidentViewProfile.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
               startActivity(intent);
            }
        });

        btn_viewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResidentNavigationActivity.this, ResidentViewID.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResidentNavigationActivity.this, MainActivity.class);
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
