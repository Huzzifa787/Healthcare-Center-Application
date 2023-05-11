package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBookMDUsername);
        edaddress = findViewById(R.id.editTextBookMDAddress);
        edcontact = findViewById(R.id.editTextBookMDContact);
        edpincode = findViewById(R.id.editTextBookMDpin);
        btnBooking = findViewById(R.id.buttonBookMD);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String data = intent.getStringExtra("date").toString();


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
                    Toast.makeText(BuyMedicineBookActivity.this, "Plz Fill all Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database db = new Database(getApplicationContext(),"healthcare",null,1);
                    db.AddOrder(username,fullname,address,contact,pincode,"",data,amount,"medicine");
                    db.removeCart(username,"medicine");
                    Toast.makeText(BuyMedicineBookActivity.this, "Your Booking is Done Successfullly", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
                }

            }
        });
    }
}