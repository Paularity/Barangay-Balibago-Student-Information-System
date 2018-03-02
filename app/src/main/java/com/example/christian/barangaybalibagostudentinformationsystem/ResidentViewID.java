package com.example.christian.barangaybalibagostudentinformationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class ResidentViewID extends AppCompatActivity {

    GridView gridView;
    ArrayList<Student> list;
    ResidentViewIDAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.resident_id_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this, "studentDB.sqlite", null, 1);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ResidentViewIDAdapter(this, R.layout.resident_list, list);
        gridView.setAdapter(adapter);
        //get all data from sqlite
        final String uname;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                uname = null;
            } else {
                uname = extras.getString("username");

            }
        } else {
            uname= (String) savedInstanceState.getSerializable("username");
        }
        Cursor cursor = databaseHelper.getData("SELECT * FROM STUDENT WHERE username = " + "'" + uname + "'");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fullname = cursor.getString(1);
            String dateOfBirth = cursor.getString(2);
            String placeOfBirth = cursor.getString(3);
            String citizenship = cursor.getString(4);
            String comelecNo = cursor.getString(5);
            String dateIssued = cursor.getString(6);
            byte[] image = cursor.getBlob(7);
            String username = cursor.getString(8);
            String password = cursor.getString(9);

            list.add(new Student(id, fullname, dateOfBirth, placeOfBirth, citizenship, comelecNo, dateIssued, image,username,password));

            adapter.notifyDataSetChanged();

        }

    }

}
