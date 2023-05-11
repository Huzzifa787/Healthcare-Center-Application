package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eusername,epassword;
    Button btn;
    TextView newUser,forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eusername = findViewById(R.id.editTextLoginUsername);
        epassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        newUser = findViewById(R.id.textViewNewUser);
        forgotPassword = findViewById(R.id.textViewForgotPassword);
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = eusername.getText().toString();
                String password = epassword.getText().toString();

                if (username.length() == 0 || password.length()==0){
                    Toast.makeText(LoginActivity.this, "Plz fill all Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (db.Login(username,password)==true){
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invaled Username and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotActivity.class));
            }
        });
    }
}