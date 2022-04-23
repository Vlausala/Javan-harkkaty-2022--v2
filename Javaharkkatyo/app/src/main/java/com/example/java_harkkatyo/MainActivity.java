package com.example.java_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    String LoggedInUser;  //getIntent().getStringExtra("Username");
    private ListView listView;
    ArrayList<Lake> lakes;
    AdapterLake adapter;
    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = findViewById(R.id.ls);
        button = findViewById(R.id.refresh);
        LoggedInUser = getIntent().getStringExtra("Username");




    }


    public void testi(View v){
        JsonHelper helper = JsonHelper.getInstance();
        String json = helper.getJSON();
        try {
            lakes = helper.ReadJSON(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new AdapterLake(MainActivity.this, 0, lakes);
        listView.setAdapter(adapter);
    }




    public void CommentScreen(View v){
        Intent intent = new Intent(MainActivity.this, CommentActivity.class );
        intent.putExtra("Username", LoggedInUser);
        startActivity(intent);
    }




}