package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;


    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton,timebutton;
    private Button btnbook,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);


        tv = findViewById(R.id.textViewTitle);
        ed1 = findViewById(R.id.editTextBookAppUsername);
        ed2 = findViewById(R.id.editTextBookAppAddress);
        ed3 = findViewById(R.id.editTextBookAppContact);
        ed4 = findViewById(R.id.editTextBookAppFee);
        datebutton = findViewById(R.id.buttonAppDate);
        timebutton = findViewById(R.id.buttonAppTime);
        btnback = findViewById(R.id.buttonBookAppBack);
        btnbook = findViewById(R.id.buttonBookApp);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullname = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String fee = intent.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cost Fee : "+fee+"/-");

        //Date Picker
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //time Picker
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDocotorActivity.class));
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                if(db.checkAppointmentExist(username,title+" => "+fullname,address,contact,datebutton.getText().toString(),timebutton.getText().toString())==true){
                    Toast.makeText(BookAppointmentActivity.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.AddOrder(username,title+" => "+fullname,address,contact,0,timebutton.getText().toString(),datebutton.getText().toString(),Float.parseFloat(fee),"appointment");
                    Toast.makeText(BookAppointmentActivity.this, "Your Appointment is done Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                }
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                datebutton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);


    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timebutton.setText(i+":"+i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,min,true);

    }

}