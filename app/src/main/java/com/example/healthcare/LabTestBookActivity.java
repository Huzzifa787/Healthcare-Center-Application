package com.example.healthcare;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextBookLabUsername);
        edaddress = findViewById(R.id.editTextBookLabAddress);
        edcontact = findViewById(R.id.editTextBookLabContact);
        edpincode = findViewById(R.id.editTextBookLabpin);
        btnBooking = findViewById(R.id.buttonBookLab);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String data = intent.getStringExtra("date").toString();
        String time = intent.getStringExtra("time").toString();




        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String fullname = edname.getText().toString();
                String address = edaddress.getText().toString();
                String contact = edcontact.getText().toString();
                String pincodestr = edpincode.getText().toString();
                int pincode = Integer.parseInt(pincodestr);
                float amount = Float.parseFloat(price[1]);

                if(edname.length()==0 || edaddress.length()==0 || edcontact.length()==0 || edpincode.length()==0){
                    Toast.makeText(LabTestBookActivity.this, "Plz Fill all Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database db = new Database(getApplicationContext(),"healthcare",null,1);
                    db.AddOrder(username,fullname,address,contact,pincode,time,data,amount,"lab");
                    db.removeCart(username,"lab");
                    Toast.makeText(LabTestBookActivity.this, "Your Booking is Done Successfullly", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
                }

            }
        });



    }
}