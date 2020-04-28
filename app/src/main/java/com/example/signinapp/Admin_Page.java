package com.example.signinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Page extends AppCompatActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__page);
        logout = findViewById(R.id.a_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearUserName(Admin_Page.this);
                goToMainActivity();
            }
        });
    }
    public void gotoViewData(View view) {
        Intent i2 = new Intent(Admin_Page.this,ViewUserData.class);
        startActivity(i2);
    }
    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = SaveSharedPreference.getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
    public void goToMainActivity()
    {
        finishAfterTransition();
        Intent i = new Intent(Admin_Page.this,Admin_Login.class);
        startActivity(i);
    }
}
