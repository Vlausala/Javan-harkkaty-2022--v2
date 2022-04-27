/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
package com.example.java_harkkatyo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the class for adding comments
 */
public class CommentActivity extends AppCompatActivity {

    private String LoggedInUser;
    private EditText Lakename, Comments;
    private Button Save, Browse;
    private DBHelper myDB = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment2);

        //Logged in user is passed from former activity
        LoggedInUser = getIntent().getStringExtra("Username");
        Lakename = findViewById(R.id.lakename);
        Comments = findViewById(R.id.comments);
        Save = findViewById(R.id.add_comment);
        Browse = findViewById(R.id.check_history);

        // changes hint white when the field has focus
        Lakename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    Lakename.setHintTextColor(Color.parseColor("#ffffff"));
                else
                    Lakename.setHintTextColor(Color.parseColor("#000000"));
            }
        });

        // changes hint white when the field has focus
        Comments.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    Comments.setHintTextColor(Color.parseColor("#ffffff"));
                else
                    Comments.setHintTextColor(Color.parseColor("#000000"));
            }
        });

    }


    /**
     * On click- method for saving comments to database.
     * @param v View which acts as the trigger
     */
    public void SaveComment(View v){
        String lakename, comment;

        //values are gotten from the EditText views
        lakename = Lakename.getText().toString();
        comment = Comments.getText().toString();

        //myDB object handles the saving and return true/false depending on the success
        boolean success = myDB.InsertComment(lakename,comment,LoggedInUser);
        if (success){
            Toast.makeText(CommentActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            Lakename.setText("");
            Comments.setText("");
        } else{
            Toast.makeText(CommentActivity.this, "Saving comment failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * On click- method for navigating to BrowseComments view
     * @param v View which acts as a trigger
     */
    public void BrowseComments(View v){
        Intent intent = new Intent(CommentActivity.this, BrowseComments.class);
        //Logged in user is passed to the next screen
        intent.putExtra("Username", LoggedInUser);
        startActivity(intent);
    }


}