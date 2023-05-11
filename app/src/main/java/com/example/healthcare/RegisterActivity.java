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

public class RegisterActivity extends AppCompatActivity {

    EditText eusername,eemail,epassword,econfirmpassword;
    Button btn;
    TextView acc;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eusername = findViewById(R.id.editTextBookAppUsername);
        eemail = findViewById(R.id.editTextBookAppAddress);
        epassword = findViewById(R.id.editTextBookAppContact);
        econfirmpassword = findViewById(R.id.editTextBookAppFee);
        btn = findViewById(R.id.buttonBookApp);
        acc= findViewById(R.id.textViewAccount);
        Database db = new Database(getApplicationContext(),"healthcare",null,1);



        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = eusername.getText().toString();
                String email = eemail.getText().toString();
                String password = epassword.getText().toString();
                String confirmPassword = econfirmpassword.getText().toString();



                if (username.length()==0 || email.length()==0 || password.length()==0 || confirmPassword.length()==0)
                {
                    Toast.makeText(RegisterActivity.this, "Plz Fill all Details", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8){
                    Toast.makeText(RegisterActivity.this, "Passwords Must be equal to or greater than 8", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirmPassword)== 0) {
                        if(isValid(password)){
                            db.register(username,email,password);
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password did n't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }



    public static boolean isValid(String Passwordhere){
        int f1=0,f2=0,f3=0;
        if (Passwordhere.length()<8){
            return false;
        }
        else{
            for(int p =0 ; p<Passwordhere.length();p++){
                if(Character.isLetter(Passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int q =0 ; q<Passwordhere.length();q++){
                if(Character.isDigit(Passwordhere.charAt(q))){
                    f2=1;
                }
            }
            for(int r =0 ; r<Passwordhere.length();r++){
                char c = Passwordhere.charAt(r);
                if (c>33 && c<46 || c==64){
                    f3=1;
                }
            }
            if(f2 == 1 && f1 == 1 && f3 ==1){
                return true;
            }
            return false;
        }
    }



}