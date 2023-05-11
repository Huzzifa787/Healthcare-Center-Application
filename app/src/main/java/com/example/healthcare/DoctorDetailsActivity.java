package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
        {
            {"Doctor Name : M Ali","Hospital Address : Lahore","Exp : 5 years","Mobile No : 03001234567","600"},
            {"Doctor Name : M Usman","Hospital Address : Fasilabad","Exp : 15 years","Mobile No : 03001234567","900"},
            {"Doctor Name : M Arshad","Hospital Address : Islamabad","Exp : 8 years","Mobile No : 03001234567","300"},
            {"Doctor Name : M Akram","Hospital Address : Quetta","Exp : 7 years","Mobile No : 03001234567","500"},
            {"Doctor Name : M Danyal Ali","Hospital Address : Lahore","Exp : 6 years","Mobile No : 03001234567","800"}
        };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : M Huzzifa Hafeez","Hospital Address : Lahore","Exp : 5 years","Mobile No : 03001234567","600"},
                    {"Doctor Name : M Ukasha Qadir","Hospital Address : Fasilabad","Exp : 15 years","Mobile No : 03001234567","900"},
                    {"Doctor Name : M Sarim Qadir","Hospital Address : Islamabad","Exp : 8 years","Mobile No : 03001234567","300"},
                    {"Doctor Name : M Hadi Qadir","Hospital Address : Quetta","Exp : 7 years","Mobile No : 03001234567","500"},
                    {"Doctor Name : Abu-Huraira Hafeez","Hospital Address : Lahore","Exp : 6 years","Mobile No : 03001234567","800"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Zubair Gujjar","Hospital Address : Lahore","Exp : 5 years","Mobile No : 03001234567","600"},
                    {"Doctor Name : Abdul Rehman","Hospital Address : Fasilabad","Exp : 15 years","Mobile No : 03001234567","900"},
                    {"Doctor Name : M Qasim","Hospital Address : Islamabad","Exp : 8 years","Mobile No : 03001234567","300"},
                    {"Doctor Name : M Zain","Hospital Address : Quetta","Exp : 7 years","Mobile No : 03001234567","500"},
                    {"Doctor Name : M Daud","Hospital Address : Lahore","Exp : 6 years","Mobile No : 03001234567","800"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : M Ali","Hospital Address : Lahore","Exp : 5 years","Mobile No : 03001234567","600"},
                    {"Doctor Name : M Arshad","Hospital Address : Fasilabad","Exp : 15 years","Mobile No : 03001234567","900"},
                    {"Doctor Name : M Omer","Hospital Address : Islamabad","Exp : 8 years","Mobile No : 03001234567","300"},
                    {"Doctor Name : M Shahid","Hospital Address : Quetta","Exp : 7 years","Mobile No : 03001234567","500"},
                    {"Doctor Name : M Usama","Hospital Address : Lahore","Exp : 6 years","Mobile No : 03001234567","800"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : M Uzair","Hospital Address : Lahore","Exp : 5 years","Mobile No : 03001234567","1600"},
                    {"Doctor Name : M Usman Arshad","Hospital Address : Fasilabad","Exp : 15 years","Mobile No : 03001234567","1900"},
                    {"Doctor Name : M Usaid","Hospital Address : Islamabad","Exp : 8 years","Mobile No : 03001234567","1300"},
                    {"Doctor Name : Zuabir Gujjar","Hospital Address : Quetta","Exp : 7 years","Mobile No : 03001234567","1500"},
                    {"Doctor Name : M Qasim Ali","Hospital Address : Lahore","Exp : 6 years","Mobile No : 03001234567","1800"}
            };


    TextView tv;
    Button btn;

    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDtitle);
        btn = findViewById(R.id.ButtonDDBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            doctor_details= doctor_details1;
        }

        else if(title.compareTo("Dietician")==0){
            doctor_details= doctor_details2;
        }
        else if(title.compareTo("Dentist")==0){
            doctor_details= doctor_details3;
        }
        else if(title.compareTo("Cardiologist")==0){
            doctor_details= doctor_details4;
        }
        else{
            doctor_details= doctor_details5;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDocotorActivity.class));
            }
        });
        list = new ArrayList<>();
        for (int i =0 ;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5","Cost Fee : "+doctor_details[i][4]+"/-");
            list.add(item);
        }
         sa =  new SimpleAdapter(this,list,R.layout.multi_lines,
                 new String[]{"line1","line2","line3","line4","line5"},
                 new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                intent1.putExtra("text1",title);
                intent1.putExtra("text2",doctor_details[i][0]);
                intent1.putExtra("text3",doctor_details[i][1]);
                intent1.putExtra("text4",doctor_details[i][3]);
                intent1.putExtra("text5",doctor_details[i][4]);
                startActivity(intent1);
            }
        });

    }
}