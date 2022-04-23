package com.example.java_harkkatyo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class LoginScreen extends AppCompatActivity {

    EditText Username, Password;
    Button SignIn;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        loadLocale();

        Username = findViewById(R.id.login_username);
        Password = findViewById(R.id.login_password);
        SignIn = findViewById(R.id.login_loginbutton);
        myDB = new DBHelper(this);
    }

    public void OnclickLogin(View v ){

        String username = "";

        username = Username.getText().toString();
        String password = Password.getText().toString();

        boolean success =  myDB.checkUsernameAndPassword(username,password);
        if (success){
            Intent intent = new Intent(LoginScreen.this, MainActivity.class );
            intent.putExtra("Username", username);
            startActivity(intent);
        } else{
            Toast.makeText(LoginScreen.this, "User was not found.", Toast.LENGTH_SHORT).show();
        }

    }

    public void OnClickRegister(View v){
        Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
        startActivity(intent);
    }

    public void OnClickChangeLanguage(View v){
        ChangeLanguage();
    }


    private void ChangeLanguage(){
        final String[] listItems = {"English", "Finnish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginScreen.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("en");
                    recreate();
                } else if (i == 1){
                    setLocale("fi");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration( config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }


    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = prefs.getString("My_Lang","");
        setLocale(lang);
    }






























}