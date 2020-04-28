package com.example.signinapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signinapp.Database.MyDatabase;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login,register;
    AlertDialog.Builder builder;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SaveSharedPreference.getUserName(Login.this).length() != 0 && SaveSharedPreference.getUserName(Login.this).equals("_user_key_"))
        {
            //If already logged in, move to User_Page
            Intent i2 = new Intent(Login.this,User_Page.class);
            startActivity(i2);
        }

        login = findViewById(R.id._login);
        username = findViewById(R.id._username);
        password = findViewById(R.id._password);
        register = findViewById(R.id._register);
        sp = SaveSharedPreference.getSharedPreferences(this);
        builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to register as a new user?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finishAfterTransition();
                        Intent intent = new Intent(Login.this,Register.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                         username.setText("");
                         password.setText("");
                    }
                });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    MyDatabase db = new MyDatabase(getApplicationContext());
                    String __email = username.getText().toString();
                    String __password = password.getText().toString();
                    if(db.searchUser(__email,__password))
                    {
                        SaveSharedPreference.setUserName(Login.this,"_user_key_");
                        Toast.makeText(Login.this, "User found!", Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                        finishAfterTransition();
                        Intent i1 = new Intent(Login.this,User_Page.class);
                        startActivity(i1);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "User Not Found! Check the credentials!", Toast.LENGTH_SHORT).show();
                        password.setText("");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.setTitle("New User?");
                        alertDialog.show();
                    }
                }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("New User?");
                alertDialog.show();
            }
        });

    }


}
