package com.incava.ex08framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout1, layout2, layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout01);
        layout2 = findViewById(R.id.layout02);
        layout3 = findViewById(R.id.layout03);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            if(id==R.id.btn01){
                layout1.setVisibility(View.VISIBLE);
            }
            else if(id == R.id.btn02){
                layout2.setVisibility(View.VISIBLE);
            }
            else if (id == R.id.btn03) {
                layout3.setVisibility(View.VISIBLE);
            }
        }
    };


}