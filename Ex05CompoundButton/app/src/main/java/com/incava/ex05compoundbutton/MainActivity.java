package com.incava.ex05compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3;
    Button btn;
    TextView tv;
    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb1 = findViewById(R.id.cb01);
        cb2 = findViewById(R.id.cb02);
        cb3 = findViewById(R.id.cb03);
        btn = findViewById(R.id.btn_submit);
        tv = findViewById(R.id.tv_result);
        tb = findViewById(R.id.tb_press);
        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "";
                if(cb1.isChecked()) s+=cb1.getText().toString() + "\t";
                if(cb2.isChecked()) s+=cb2.getText().toString() + "\t";
                if(cb3.isChecked()) s+=cb3.getText().toString() + "\t";
                tv.setText(s);
            }
        });




    }//onCreate

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            String s = "";
            if(cb1.isChecked()) s+=cb1.getText().toString() + "\t";
            if(cb2.isChecked()) s+=cb2.getText().toString() + "\t";
            if(cb3.isChecked()) s+=cb3.getText().toString() + "\t";
            tv.setText(s);
        }
    };



}