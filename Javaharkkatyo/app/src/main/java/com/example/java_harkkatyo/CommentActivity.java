package com.example.java_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CommentActivity extends AppCompatActivity {

    String LoggedInUser;
    EditText Lakename, Comments;
    Button Save, Browse;
    DBHelper myDB = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment2);

        LoggedInUser = getIntent().getStringExtra("Username");
        Lakename = findViewById(R.id.lakename);
        Comments = findViewById(R.id.comments);
        Save = findViewById(R.id.add_comment);
        Browse = findViewById(R.id.check_history);

        Lakename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    Lakename.setHintTextColor(Color.parseColor("#ffffff"));
                else
                    Lakename.setHintTextColor(Color.parseColor("#000000"));
            }
        });

        Comments.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    Comments.setHintTextColor(Color.parseColor("#ffffff"));
                else
                    Comments.setHintTextColor(Color.parseColor("#000000"));
            }
        });

    }



    public void SaveComment(View v){
        String lakename, comment;
        lakename = Lakename.getText().toString();
        comment = Comments.getText().toString();
        boolean success = myDB.InsertComment(lakename,comment,LoggedInUser);
        if (success){
            Toast.makeText(CommentActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            Lakename.setText("");
            Comments.setText("");
        } else{
            Toast.makeText(CommentActivity.this, "Saving comment failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void BrowseComments(View v){
        Intent intent = new Intent(CommentActivity.this, BrowseComments.class);
        intent.putExtra("Username", LoggedInUser);
        startActivity(intent);
    }











}