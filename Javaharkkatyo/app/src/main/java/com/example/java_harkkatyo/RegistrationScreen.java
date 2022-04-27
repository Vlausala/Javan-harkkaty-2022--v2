/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
package com.example.java_harkkatyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class RegistrationScreen extends AppCompatActivity {

    private EditText Username, Password, Repassword;
    private Button Register;
    private DBHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationscreen);

        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Repassword = (EditText) findViewById(R.id.RePassword);
        Register = (Button) findViewById(R.id.Register);
        myDB = new DBHelper(this);

    }


    /**
     * Button functionality to register the user
     * @param v View which triggers the function
     */
    public void OnClickRegister(View v ){

        String user = Username.getText().toString();
        String password = Password.getText().toString();
        String repassword = Repassword.getText().toString();

        //Show error-message if any of the fields is empty
        if ( user.equals("") || password.equals("") || repassword.equals("")){
            Toast.makeText(RegistrationScreen.this,"Fill all fields",Toast.LENGTH_LONG).show();

        //Check if password is strong and is typed two times
        } else if (RegexHelper.IsPasswordStrong(password) && password.equals(repassword) ){
            //User is inserted and errors are checked
            boolean success =  myDB.insertUser(user, password);
            if (success){
                Toast.makeText(RegistrationScreen.this, "Success!", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(RegistrationScreen.this, "Creating user failed.", Toast.LENGTH_LONG).show();
            }
        } else{
            System.out.println( RegexHelper.IsPasswordStrong(password));
            Toast.makeText(RegistrationScreen.this, "Please fix the password", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to move to loginscreen
     * @param v
     */
    public void OnclickLogin(View v){
        Intent intent = new Intent(RegistrationScreen.this, LoginScreen.class);
        startActivity(intent);
    }

}