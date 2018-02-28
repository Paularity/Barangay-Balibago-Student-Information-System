package com.example.christian.barangaybalibagostudentinformationsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class DailyRecordListAdapter extends BaseAdapter {

    private Context context;
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

        TextView txt_ID,txt_fullname,txt_dateOfBirth,txt_placeOfBirth,txt_citizenship,txt_comelecNo,txt_dateIssued;
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
            row.setTag(holder);

        }
        else
        {
            holder = (viewHolder) row.getTag();
        }

        Student student = studentList.get(position);

        holder.txt_ID.setText(student.getFullname());
        holder.txt_fullname.setGravity(Gravity.CENTER);
        holder.txt_citizenship.setText(student.getCitizenship());
        holder.txt_dateOfBirth.setText(student.getDateOfBirth());
        holder.txt_placeOfBirth.setText(student.getPlaceOfBirth());
        holder.txt_comelecNo.setText(student.getComelecNo());
        holder.txt_dateIssued.setText(student.getDateIssued());
        return row;
    }
}
