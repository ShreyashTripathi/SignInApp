package com.example.signinapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.signinapp.Adapter.UserAdapter;
import com.example.signinapp.Database.MyDatabase;
import com.example.signinapp.model.User;

import java.util.ArrayList;

public class ViewUserData extends AppCompatActivity {

    MyDatabase db;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_data);
        db = new MyDatabase(this);
        recyclerView = findViewById(R.id.user_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        ArrayList<User> userArrayList = db.showUserData();
        if(userArrayList.isEmpty())
        {
            Toast.makeText(this, "ArrayList is empty!", Toast.LENGTH_SHORT).show();
        }
        UserAdapter userAdapter = new UserAdapter(this, userArrayList);
        recyclerView.setAdapter(userAdapter);
    }
}
