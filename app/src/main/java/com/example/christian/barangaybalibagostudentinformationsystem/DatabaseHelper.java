package com.example.christian.barangaybalibagostudentinformationsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Christian on 26/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }

    public void insertData(String fullname, String dateOfBirth,String placeOfBirth,String citizenship, String comelecNo, String dateIssued, byte[] image,String username, String password)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO STUDENT VALUES (NULL,?,?,?,?,?,?,?,?,?)";SQLiteStatement statement = db.compileStatement(sql);

        statement.clearBindings();
        statement.bindString(1, fullname);
        statement.bindString(2, dateOfBirth);
        statement.bindString(3, placeOfBirth);
        statement.bindString(4, citizenship);
        statement.bindString(5, comelecNo);
        statement.bindString(6, dateIssued);
        statement.bindBlob(7, image);
        statement.bindString(8, username);
        statement.bindString(9, password);

        statement.executeInsert();
    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public void deleteData(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM STUDENT WHERE id = " + id;
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
