package com.incava.ex42intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);

        btn.setOnClickListener(v -> {
            a(SecondActivity.class);
        });
        btn2.setOnClickListener(view -> {
            a(ThirdActivity.class);
        });
    }
    void a (Class<?> cls){
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
        finish();
    }

}