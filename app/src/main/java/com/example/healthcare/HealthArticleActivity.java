package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticleActivity extends AppCompatActivity {

    private String[][] health_articles =
            {
                    {"Walking Daily","","","","Click More Details"},
                    {"Home care of COVID-19","","","","Click More Details"},
                    {"Stop Smoking","","","","Click More Details"},
                    {"Henstrual Cramps","","","","Click More Details"},
                    {"Healthy Gut","","","","Click More Details"}
            };

    private int[] images ={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String> item;
    SimpleAdapter sa;
    ArrayList list;
    ListView lst;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        lst = findViewById(R.id.listViewHA);
        btnBack = findViewById(R.id.ButtonHABack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<health_articles.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",health_articles[i][0]);
            item.put("line2",health_articles[i][1]);
            item.put("line3",health_articles[i][2]);
            item.put("line4",health_articles[i][3]);
            item.put("line5",health_articles[i][4]);
            list.add(item);
        }
        sa =  new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HealthArticleActivity.this,HealthArticleDetailsActivity.class);
                intent.putExtra("text1",health_articles[i][0]);
                intent.putExtra("text2",images[i]);
                startActivity(intent);
            }
        });

    }
}