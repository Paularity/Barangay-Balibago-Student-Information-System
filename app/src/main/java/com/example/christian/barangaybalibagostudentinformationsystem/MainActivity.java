package com.example.christian.barangaybalibagostudentinformationsystem;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    Button btn_login,btn_register;
    EditText et_username,et_password;

    Cursor cursor;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username =  (EditText) findViewById(R.id.et_username);
        et_password =  (EditText) findViewById(R.id.et_password);


        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);


        //Opening SQLite Pipeline
        dbhelper = new DatabaseHelper(this, "studentDB.sqlite", null, 1);
        dbhelper.queryData("CREATE TABLE IF NOT EXISTS STUDENT (id INTEGER PRIMARY KEY AUTOINCREMENT, fullname VARCHAR,dateOfBirth VARCHAR,placeOfBirth VARCHAR,citizenship VARCHAR,comelecNo VARCHAR,dateIssued VARCHAR,image BLOB,username VARCHAR, password VARCHAR)");

        db = dbhelper.getReadableDatabase();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = String.valueOf(et_username.getText());
                final String password = String.valueOf(et_password.getText());
                String sql = "SELECT * FROM STUDENT WHERE username = ? AND password=?";
                cursor = db.rawQuery( sql, new String[] {username,password});
                if(username.equals("admin") && password.equals("admin"))
                {
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Welcome Admin!",Toast.LENGTH_SHORT).show();
                }

                else  if(cursor.getCount() > 0) {
                    Intent intent = new Intent(MainActivity.this, ResidentNavigationActivity.class);
                    startActivity(intent);
                    intent.putExtra(username, password);
                    Toast.makeText(getApplicationContext(),"Login Successfully.",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Username or Password is wrong.", Toast.LENGTH_SHORT).show();

//                    et_username.setText("");
//                    et_password.setText("");
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setTitle("Alert");
//                    builder.setMessage("Username or Password is wrong.");
//                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            dialogInterface.dismiss();
//
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterStudent.class);
                startActivity(intent);
            }
        });

    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();

            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();

        }

        mBackPressed = System.currentTimeMillis();
    }

}
