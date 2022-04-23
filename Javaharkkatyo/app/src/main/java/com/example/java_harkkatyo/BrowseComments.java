package com.example.java_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BrowseComments extends AppCompatActivity {

    String LoggedInUser;
    private ListView listView;
    ArrayList<UserComment> comments;
    AdapterComment adapter;
    DBHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_comments);
        myDB = new DBHelper(this);
        listView = findViewById(R.id.commentls);
        LoggedInUser = getIntent().getStringExtra("Username");
        loadComments(LoggedInUser);
    }

    private void loadComments(String user){
        ArrayList<UserComment> comments = myDB.QueryComments(user);
        adapter = new AdapterComment(BrowseComments.this, 0, comments);
        listView.setAdapter(adapter);
    }

}