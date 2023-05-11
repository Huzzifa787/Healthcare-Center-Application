package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView eusername;
    Button btnres;
    EditText epassword,econfirmpassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        eusername = findViewById(R.id.TextViewResetUsername);
        btnres = findViewById(R.id.buttonResetPass);
        epassword = findViewById(R.id.editTextResetPassword);
        econfirmpassword = findViewById(R.id.editTextResetConfirmPassword);
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        Intent intent =getIntent();
        eusername.setText(intent.getStringExtra("username"));

        btnres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= eusername.getText().toString();
                String password = epassword.getText().toString();
                String repassword = econfirmpassword.getText().toString();
                Boolean check_pass_update = db.updatePassword(username,password);

                if (password.equals(repassword)){
                    if(check_pass_update == true){
                        startActivity(new Intent(ResetActivity.this, LoginActivity.class));
                        Toast.makeText(ResetActivity.this, "Password Updated Successfully...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ResetActivity.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ResetActivity.this, "Password and Confirm Password Does't Match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}