package com.incava.tp04compand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;
    EditText et_name, et_phone1, et_phone2, et_phone3;

    RadioGroup rg_gender, rg_city;
    CheckBox cb_email, cb_phone, cb_visit, cb_sms;

    Button btn;
    ScrollView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_submit);
        tv_result = findViewById(R.id.tv_result);
        et_name = findViewById(R.id.et_name);
        et_phone1 = findViewById(R.id.et_phone1);
        et_phone2 = findViewById(R.id.et_phone2);
        et_phone3 = findViewById(R.id.et_phone3);
        rg_gender = findViewById(R.id.rg_genders);
        rg_city = findViewById(R.id.rg_city);
        cb_email = findViewById(R.id.cb_email);
        cb_phone = findViewById(R.id.cb_phone);
        cb_visit = findViewById(R.id.cb_visit);
        cb_sms = findViewById(R.id.cb_sms);
        sv = findViewById(R.id.sv_list);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String s = "";
                Button city = findViewById(rg_city.getCheckedRadioButtonId());
                Button gender = findViewById(rg_gender.getCheckedRadioButtonId());
                s+= " " + et_name.getText().toString();
                s+= " " + gender.getText().toString();
                s+= " " + city.getText().toString();
                s+= " " + et_phone1.getText().toString();
                s+= et_phone2.getText().toString();
                s+= et_phone3.getText().toString();
                if (cb_email.isChecked()) s+=" " + cb_email.getText().toString();
                if (cb_email.isChecked()) s+=" " + cb_visit.getText().toString();
                if (cb_email.isChecked()) s+=" " + cb_sms.getText().toString();
                String tv = tv_result.getText().toString();
                tv_result.setText(tv + s + "\n");
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
        et_phone1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //pass
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                String text = charSequence.toString();
                if (text.length()==3){
                    et_phone2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_phone2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //pass
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                String text = charSequence.toString();
                if (text.length()==4){
                    et_phone3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_phone3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //pass
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                String text = charSequence.toString();
                if (text.length()==4){
                    cb_email.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
