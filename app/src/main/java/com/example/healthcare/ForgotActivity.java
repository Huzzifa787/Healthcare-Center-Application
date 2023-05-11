package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    EditText eusername;
    Button btnres;
    TextView backToLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        eusername = findViewById(R.id.editTextForgotUsername);
        btnres= findViewById(R.id.buttonReset);
        backToLogin = findViewById(R.id.textViewBackToLogin);
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        btnres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = eusername.getText().toString();
                boolean check = db.checkUserName(username);
                if(check == true){
                    Intent intent = new Intent(ForgotActivity.this,ResetActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgotActivity.this, "User Does't Exit...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
            }
        });

    }
}