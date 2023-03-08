package com.incava.expratice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    /**
     *
     * ball이라는 리소스를 읽기 위해 연습해본 액티비티
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        int s = getResources().getIdentifier("ball","drawable",getPackageName());
        Glide.with(this)
                .load(s)
                .error(R.drawable.koala)
                .into(iv);
    }
}