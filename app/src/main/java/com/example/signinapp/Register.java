package com.example.signinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signinapp.Database.MyDatabase;
import com.example.signinapp.model.User;

public class Register extends AppCompatActivity {

    EditText name,email,username,password;
    Button register;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id._name_r);
        email = findViewById(R.id._email_r);
        username = findViewById(R.id._username_r);
        password = findViewById(R.id._password_r);
        register = findViewById(R.id._register_r);

        myDatabase = new MyDatabase(getApplicationContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u1 = new User();
                u1.setName(name.getText().toString());
                u1.setEmail(email.getText().toString());
                u1.setUsername(username.getText().toString());
                u1.setPassword(password.getText().toString());
                myDatabase.insertUserData(u1);
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


    }
}
