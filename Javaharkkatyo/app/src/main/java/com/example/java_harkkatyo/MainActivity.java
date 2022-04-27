/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
package com.example.java_harkkatyo;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    //LoggedInUser is saved here
    private String LoggedInUser;
    private ListView listView;
    private ArrayList<Lake> lakes;
    private AdapterLake adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StrictMode is turned off for the API to work
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = findViewById(R.id.ls);
        button = findViewById(R.id.refresh);
        LoggedInUser = getIntent().getStringExtra("Username");


    }

    /**
     * Method used for refreshing data from the API
     * @param v
     */
    public void RefreshData(View v){
        //Creates JsonHelper instance
        JsonHelper helper = JsonHelper.getInstance();
        try {
            //Saves lakes from the api to arraylist
            lakes = helper.ReadJSON(helper.getJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Adapter for the listview
        adapter = new AdapterLake(MainActivity.this, 0, lakes);

        //Listview is populated with the help of the adapter
        listView.setAdapter(adapter);
    }


    /**
     * Method for navigating to the comments screen
     * @param v
     */
    public void CommentScreen(View v){
        Intent intent = new Intent(MainActivity.this, CommentActivity.class );
        //Logged in user is passed to the next screen
        intent.putExtra("Username", LoggedInUser);
        startActivity(intent);
    }
}