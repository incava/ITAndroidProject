package com.incava.ex45activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            //SecondActivity.class 명칭없이 실행해보기.
            Intent intent = new Intent();
            intent.setAction("aaaa");
            startActivity(intent);
        });
    }
}