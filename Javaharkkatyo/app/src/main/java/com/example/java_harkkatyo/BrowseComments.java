/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */

package com.example.java_harkkatyo;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * Class for BrowseComments- screen
 */
public class BrowseComments extends AppCompatActivity {

    private String LoggedInUser;
    private ListView listView;
    private AdapterComment adapter;
    private DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_comments);
        myDB = new DBHelper(this);
        listView = findViewById(R.id.commentls);

        //LoggedInUser is passed from previous screen
        LoggedInUser = getIntent().getStringExtra("Username");
        loadComments(LoggedInUser);
    }


    /**
     * Method used for querying user-specific contents from database and
     * displaying them in a listview
     * @param user Logged in user used to retrieve correct data
     */
    private void loadComments(String user){
        ArrayList<UserComment> comments = myDB.QueryComments(user);
        adapter = new AdapterComment(BrowseComments.this, 0, comments);
        listView.setAdapter(adapter);
    }

}