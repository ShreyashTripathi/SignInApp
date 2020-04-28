package com.example.signinapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signinapp.Database.Admin_Database;
import com.example.signinapp.Database.MyDatabase;

public class Admin_Login extends AppCompatActivity {

    EditText username,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        if(SaveSharedPreference.getUserName(Admin_Login.this).length() != 0 && SaveSharedPreference.getUserName(Admin_Login.this).equals("_admin_key_"))
        {
            //If already logged in, move to User_Page
            Intent i2 = new Intent(Admin_Login.this,Admin_Page.class);
            startActivity(i2);
        }

        login = findViewById(R.id._login_m);
        username = findViewById(R.id._username_m);
        password = findViewById(R.id._password_m);

        register = findViewById(R.id._register_m);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Admin_Database db = new Admin_Database(getApplicationContext());
                if (db.searchAdmin(username.getText().toString(), password.getText().toString())) {
                    SaveSharedPreference.setUserName(Admin_Login.this,"_admin_key_");
                    Toast.makeText(Admin_Login.this, "Admin found!", Toast.LENGTH_SHORT).show();
                    finishAfterTransition();
                    Intent i1 = new Intent(Admin_Login.this,Admin_Page.class);
                    startActivity(i1);

                } else {
                    Toast.makeText(Admin_Login.this, "Admin Not Found! Check the credentials!", Toast.LENGTH_SHORT).show();
                    password.setText("");
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
                Intent intent = new Intent(Admin_Login.this,Admin_Register.class);
                startActivity(intent);
            }
        });


    }


}