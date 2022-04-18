package com.example.java_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //String LoggedInUser = getIntent().getStringExtra("Username");
    //String LoggedInPassword = getIntent().getStringExtra("Password");

    private ListView listView;
    ArrayList<Lake> lakes;
    AdapterLake adapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = findViewById(R.id.ls);
        button = findViewById(R.id.refresh);



    }


    public void testi(View v){
        JsonHelper helper = JsonHelper.getInstance();
        String json = helper.getJSON();
        try {
            lakes = helper.ReadJSON(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(lakes.size());
        for (int i=0; i < lakes.size(); i++){
            System.out.println(lakes.get(i).getLakename());
        }

        adapter = new AdapterLake(MainActivity.this, 0, lakes);
        listView.setAdapter(adapter);
    }











}