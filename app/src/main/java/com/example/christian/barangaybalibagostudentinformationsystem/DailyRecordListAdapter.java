package com.example.christian.barangaybalibagostudentinformationsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class DailyRecordListAdapter extends BaseAdapter {

    private static Context context;
    private int layout;
    private ArrayList<Student> studentList;


    public DailyRecordListAdapter(Context context, int layout, ArrayList<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class viewHolder
    {

        TextView txt_ID,txt_fullname,txt_dateOfBirth,txt_placeOfBirth,txt_citizenship,txt_comelecNo,txt_dateIssued,txt_username,txt_password;
        AppCompatButton btn_delete;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        viewHolder holder = new viewHolder();
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txt_ID = (TextView) row.findViewById(R.id.txt_ID);
            holder.txt_fullname = (TextView) row.findViewById(R.id.txt_fullname);
            holder.txt_citizenship = (TextView) row.findViewById(R.id.txt_citizenship);
            holder.txt_dateOfBirth = (TextView) row.findViewById(R.id.txt_dateOfBirth);
            holder.txt_placeOfBirth = (TextView) row.findViewById(R.id.txt_placeOfBirth);
            holder.txt_comelecNo = (TextView) row.findViewById(R.id.txt_comelecNo);
            holder.txt_dateIssued = (TextView) row.findViewById(R.id.txt_dateIssued);
            holder.txt_username = (TextView) row.findViewById(R.id.txt_username);
            holder.txt_password = (TextView) row.findViewById(R.id.txt_password);
            holder.btn_delete = (AppCompatButton) row.findViewById(R.id.btn_delete);
            row.setTag(holder);



        }
        else
        {
            holder = (viewHolder) row.getTag();
        }

        final Student student = studentList.get(position);
        final DatabaseHelper databaseHelper = new DatabaseHelper(context,"studentDB.sqlite",null,1);
        holder.txt_ID.setText(Integer.toString(student.getId()));
        holder.txt_fullname.setText(student.getFullname());
        holder.txt_fullname.setGravity(Gravity.CENTER);
        holder.txt_citizenship.setText(student.getCitizenship());
        holder.txt_dateOfBirth.setText(student.getDateOfBirth());
        holder.txt_placeOfBirth.setText(student.getPlaceOfBirth());
        holder.txt_comelecNo.setText(student.getComelecNo());
        holder.txt_dateIssued.setText(student.getDateIssued());
        holder.txt_username.setText(student.getUsername());
        holder.txt_password.setText(student.getPassword());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.deleteData(Integer.toString(student.getId()));
                Toast.makeText(context ,"Sucessfully deleted student." + Integer.toString(student.getId()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DailyRecordListAdapter.context,DailyRecordList.class);
                context.startActivity(intent);
            }
        });
        return row;
    }


}


