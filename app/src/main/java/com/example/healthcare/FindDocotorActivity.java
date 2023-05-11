package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDocotorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_docotor);


        CardView family_physician = findViewById(R.id.cardFDfamilyphysician);
        CardView dietician = findViewById(R.id.cardFDDietician);
        CardView dentist = findViewById(R.id.cardFDDentist);
        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        CardView cardiologist = findViewById(R.id.cardFDCardiologist);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView back = findViewById(R.id.cardFDBack);

        family_physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDocotorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);

            }
        });

        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDocotorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDocotorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });

        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDocotorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);

            }
        });

        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDocotorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Cardiologist");
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDocotorActivity.this,HomeActivity.class));
            }
        });

    }
}