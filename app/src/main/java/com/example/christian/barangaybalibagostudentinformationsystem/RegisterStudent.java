package com.example.christian.barangaybalibagostudentinformationsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Christian on 26/02/2018.
 */

public class RegisterStudent extends AppCompatActivity {

    EditText et_fullname,et_dateOfBirth,et_placeOfBirth,et_citizenship,et_comelecNo,et_dateIssued,et_username,et_password;
    Button btn_upload,btn_register;
    ImageView image;

    final int REQUEST_CODE_GALLERY = 999;

    public static DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);


        init();
        databaseHelper = new DatabaseHelper(this,"studentDB.sqlite",null,1);
        databaseHelper.queryData("CREATE TABLE IF NOT EXISTS STUDENT (id INTEGER PRIMARY KEY AUTOINCREMENT, fullname VARCHAR,dateOfBirth VARCHAR,placeOfBirth VARCHAR,citizenship VARCHAR,comelecNo VARCHAR,dateIssued VARCHAR,image BLOB,username VARCHAR, password VARCHAR)");

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        RegisterStudent.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String fullname = et_fullname.getText().toString();
                    String dateOfBirth = et_dateOfBirth.getText().toString();
                    String placeOfBirth = et_placeOfBirth.getText().toString();
                    String citizenship = et_citizenship.getText().toString();
                    String comelecNo = et_comelecNo.getText().toString();
                    String dateIssued = et_dateIssued.getText().toString();
                    byte[] imageView = imageViewToByte(image);
                    String username = et_username.getText().toString();
                    String password = et_password.getText().toString();

                if(!fullname.equals("") && !dateOfBirth.equals("") && !dateOfBirth.equals("") && !placeOfBirth.equals("") &&
                        !citizenship.equals("") && !comelecNo.equals("") && !dateIssued.equals("") && !imageView.equals("")
                        && !username.equals("") && !password.equals("")) {
                    databaseHelper.insertData(
                            et_fullname.getText().toString().trim(),
                            et_dateOfBirth.getText().toString().trim(),
                            et_placeOfBirth.getText().toString().trim(),
                            et_citizenship.getText().toString().trim(),
                            et_comelecNo.getText().toString().trim(),
                            et_dateIssued.getText().toString().trim(),
                            imageViewToByte(image),
                            et_username.getText().toString().trim(),
                            et_password.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Student was added successfully!", Toast.LENGTH_SHORT).show();
                    et_username.setText("");
                    et_password.setText("");
                    et_fullname.setText("");
                    et_dateOfBirth.setText("");
                    et_placeOfBirth.setText("");
                    et_citizenship.setText("");
                    et_comelecNo.setText("");
                    et_dateIssued.setText("");
                    image.setImageResource(R.mipmap.ic_launcher);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill in the blank/s",Toast.LENGTH_SHORT).show();
                }
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //REQUEST IMAGE UPLOAD PERMISSION
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);

            }

            else
            {
                Toast.makeText(getApplicationContext(),"You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init()
    {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password =  (EditText) findViewById(R.id.et_password);
        et_fullname = (EditText) findViewById(R.id.et_fullname);
        et_dateOfBirth = (EditText) findViewById(R.id.et_dateOfBirth);
        et_placeOfBirth = (EditText) findViewById(R.id.et_placeOfBirth);
        et_citizenship = (EditText) findViewById(R.id.et_citizenship);
        et_comelecNo = (EditText) findViewById(R.id.et_comelecNo);
        et_dateIssued = (EditText) findViewById(R.id.dt_dateIssued);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_register = (Button) findViewById(R.id.btn_register);
        image = (ImageView) findViewById(R.id.image);
    }
}
