package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvpackagename,tvtotalcost;
    EditText edDetail;
    Button btnAddToCart,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvpackagename = findViewById(R.id.textViewBMDtitle2);
        tvtotalcost = findViewById(R.id.textViewBMDTotalCost);
        edDetail = findViewById(R.id.editTextmultilineBMD);
        edDetail.setKeyListener(null);
        btnAddToCart = findViewById(R.id.ButtonBMDGotoCart);
        btnBack = findViewById(R.id.ButtonBMDBack);

        Intent intent= getIntent();
        String pname  = intent.getStringExtra("text1");
        String edetails  = intent.getStringExtra("text2");
        String cost = intent.getStringExtra("text3");
        tvpackagename.setText(pname);
        edDetail.setText(edetails);
        tvtotalcost.setText("Total Cost : "+cost+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvpackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==true){
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.AddToCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });

    }
}