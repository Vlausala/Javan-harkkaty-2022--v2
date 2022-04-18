package com.example.java_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationScreen extends AppCompatActivity {

    EditText Username, Password, Repassword;
    Button Register;
    DBHelper myDB;



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



    public void OnClickRegister(View v ){ //TODO Korjaa rekisteröintiin kunnon vikailmoitukset

        String user = Username.getText().toString();
        String password = Password.getText().toString();
        String repassword = Repassword.getText().toString();

        if ( user.equals("") || password.equals("") || repassword.equals("")){
            Toast.makeText(RegistrationScreen.this,"Fill all fields",Toast.LENGTH_LONG).show();
        } else if (
                RegexHelper.IsPasswordStrong(password) &&
                        password.equals(repassword)
        ){
            boolean success =  myDB.insertData(user, password);
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

    public void OnclickLogin(View v){
        Intent intent = new Intent(RegistrationScreen.this, LoginScreen.class);
        startActivity(intent);
    }





}