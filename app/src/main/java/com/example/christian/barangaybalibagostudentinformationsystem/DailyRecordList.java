package com.example.christian.barangaybalibagostudentinformationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class DailyRecordList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Student> list;
    DailyRecordListAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this, "studentDB.sqlite", null, 1);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new DailyRecordListAdapter(this, R.layout.daily_record_list, list);
        gridView.setAdapter(adapter);
        //get all data from sqlite
        Cursor cursor = databaseHelper.getData("SELECT * FROM STUDENT");
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
            Intent intent = new Intent(DailyRecordList.this, NavigationActivity.class);
            startActivity(intent);
        }

        mBackPressed = System.currentTimeMillis();
    }

}
