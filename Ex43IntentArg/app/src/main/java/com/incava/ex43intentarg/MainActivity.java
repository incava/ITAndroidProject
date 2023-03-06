package com.incava.ex43intentarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view->{
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("age",age);
            startActivity(intent);

        });

    }


}