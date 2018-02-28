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

public class StudentListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Student> studentList;

    public StudentListAdapter(Context context, int layout, ArrayList<Student> studentList) {
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
        ImageView imageView;
        TextView tv_fullname,tv_dateOfBirth,tv_placeOfBirth,tv_comelecNo,tv_dateIssued,tv_citizenship;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        viewHolder holder = new viewHolder();
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.tv_fullname = (TextView) row.findViewById(R.id.tv_fullname);
            holder.tv_citizenship = (TextView) row.findViewById(R.id.tv_citizenship);
            holder.tv_dateOfBirth = (TextView) row.findViewById(R.id.tv_dateOfBirth);
            holder.tv_placeOfBirth = (TextView) row.findViewById(R.id.tv_placeOfBirth);
            holder.tv_comelecNo = (TextView) row.findViewById(R.id.tv_comelecNo);
            holder.tv_dateIssued = (TextView) row.findViewById(R.id.tv_dateIssued);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);

        }
        else
        {
            holder = (viewHolder) row.getTag();
        }

        Student student = studentList.get(position);

        holder.tv_fullname.setText(student.getFullname());
        holder.tv_fullname.setGravity(Gravity.CENTER);
        holder.tv_citizenship.setText(student.getCitizenship());
        holder.tv_dateOfBirth.setText(student.getDateOfBirth());
        holder.tv_placeOfBirth.setText(student.getPlaceOfBirth());
        holder.tv_comelecNo.setText(student.getComelecNo());
        holder.tv_dateIssued.setText(student.getDateIssued());
        byte[] studentImage = student.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(studentImage,0,studentImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
