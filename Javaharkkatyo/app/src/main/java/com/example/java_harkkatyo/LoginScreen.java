/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
package com.example.java_harkkatyo;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/*
 * Login screen's dedicated class
 *
 */
public class LoginScreen extends AppCompatActivity {

    private EditText Username, Password;
    private Button SignIn;
    private DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        //Loads the selected language for the app
        loadLocale();

        Username = findViewById(R.id.login_username);
        Password = findViewById(R.id.login_password);
        SignIn = findViewById(R.id.login_loginbutton);
        myDB = new DBHelper(this);
    }

    /**
     * Method used for logging into the app
     * @param v View which triggers the method trough on-click property
     */
    public void OnclickLogin(View v ){

        String username = "";

        username = Username.getText().toString();
        String password = Password.getText().toString();

        //Check if login successfull
        boolean success =  myDB.checkUsernameAndPassword(username,password);
        if (success){
            //Move to mainscreen
            Intent intent = new Intent(LoginScreen.this, MainActivity.class );
            //Pass login-username to the app
            intent.putExtra("Username", username);
            startActivity(intent);
        } else{
            //Show error-message
            Toast.makeText(LoginScreen.this, "User was not found.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Method for moving to registration-screen
     * @param v View which triggers the method
     */
    public void OnClickRegister(View v){
        Intent intent = new Intent(LoginScreen.this, RegistrationScreen.class);
        startActivity(intent);
    }

    /**
     * Triggers the ChangeLanguage function
     * @param v
     */
    public void OnClickChangeLanguage(View v){
        ChangeLanguage();
    }


    /**
     * Shows the language-selection menu
     */
    private void ChangeLanguage(){
        //Shows available languages
        final String[] listItems = {"English", "Finnish"};
        //Creates popup-menu
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginScreen.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            //Language selection
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

    /**
     * Sets the language of the app
     * @param lang ISO-code for the selected language
     */
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

    /**
     * Loads the selected language for the app
     */
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = prefs.getString("My_Lang","");
        setLocale(lang);
    }

}