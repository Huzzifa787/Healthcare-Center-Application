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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages={
            {"Uprise-D3","","","","60"},
            {"Healthvit Chromium Picolinate 200mcg Capsule","","","","305"},
            {"Vitamin B Complex Capsule","","","","448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},
            {"Dolo 650 Tablets","","","","30"},
            {"Crocine 650 Advance Tablets","","","","50"},
            {"Strepsils for Sore Throat","","","","40"},
            {"Tata 1mg Calcium + Vitamin D3","","","","30"},
            {"Ferona-XT Tablets ","","","","130"}
    };

    private String[] package_details= {

            "Building and Keeping the Bones and Teeths Strong\n"+
                    "Reducing Fatigue/Stress and masuclar pain\n"+
                    "Boosting Imunity and Increasing resistance against infection\n",
            "Chromium is an essential trace minerals that plays an important role in helping insuline regulations",
            "Provides relief from vitamin B defiencies\n"+
                    "Helps in formation of red blood cells\n"+
                    "Maintain healthy nervous system",
            "It promotes health as well as skin bnefits\n"+
                    "It helps reduce skin blemish and pigmentation\n"+
                    "it acts as safegaurd the skin from UAV and UBV sun rays",
            "Dolo 650 Tablets helps releives pain and fever by blocking and release of certain chemicals",
            "Helps relieve fever and bring down a high temperature\n"+
                    "Suitable for a people with heart condition or high blood pressure",
            "Releives the symptoms of bacterial throat infection and soothes the recovery process\n"+
                    "Provides a warm comforting feelings during sore throat.",
            "Reduce the risk of calcium defieicieny, Rickets and OSteoperios\n"+
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficinecy due to chronic blood loss or low intake of iron"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGotoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.ButtonBMBack);
        btnGotoCart = findViewById(R.id.ButtonBMGotoCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            list.add(item);
        }
        sa =  new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",package_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });

    }
}