package com.incava.ex29viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> imgIds = new ArrayList<>();
    ViewPager2 viewPager2;
    MyAdapter myAdapter;
    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가
        imgIds.add(R.drawable.bg_one01);
        imgIds.add(R.drawable.bg_one02);
        imgIds.add(R.drawable.bg_one03);
        imgIds.add(R.drawable.bg_one04);
        imgIds.add(R.drawable.bg_one05);
        imgIds.add(R.drawable.bg_one06);
        imgIds.add(R.drawable.bg_one07);
        imgIds.add(R.drawable.bg_one08);
        imgIds.add(R.drawable.bg_one09);
        imgIds.add(R.drawable.bg_one10);

        viewPager2 = findViewById(R.id.viewPager_onePeace);
        myAdapter = new MyAdapter(this, imgIds);
        viewPager2.setAdapter(myAdapter);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(position-1,true);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(position+1,false);
            }
        });








    }
}