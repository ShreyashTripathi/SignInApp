package com.example.signinapp.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.signinapp.Register;
import com.example.signinapp.model.User;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    Context context;
    public MyDatabase(@Nullable Context context) {
        super(context, "user_db", null, 3);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table UserData(name,email,username,password)";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertUserData(User u1)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",u1.getName());
        cv.put("email",u1.getEmail());
        cv.put("username",u1.getUsername());
        cv.put("password",u1.getPassword());
        db.insert("UserData",null, cv);
        Toast.makeText(context, "Data Inserted!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<User> showUserData()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from UserData";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<User> userArrayList = new ArrayList<>();
        int flag_2 = 0;
        while(cursor.moveToNext())
        {
            flag_2 = 1;
            User u1 = new User();
            u1.setName(cursor.getString(0));
            u1.setEmail(cursor.getString(1));
            u1.setUsername(cursor.getString(2));
            u1.setPassword(cursor.getString(3));
            userArrayList.add(u1);
        }
        if(flag_2 == 0)
        {
            Toast.makeText(context, "No data found!", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        closeDatabase(db);
        return  userArrayList;
    }

    public boolean searchUser(String username,String password)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from UserData where email = ? AND password = ?";
        String args[] = {username,password};
        Cursor cursor = db.rawQuery(sql,args);
        int flag = 0;
        while(cursor.moveToNext())
        {
            flag = 1;
        }
        if(flag == 1)
        {
            cursor.close();
            closeDatabase(db);
            return true;
        }
        cursor.close();
        closeDatabase(db);
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void closeDatabase(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
