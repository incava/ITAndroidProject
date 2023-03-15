package com.incava.ex78viewbinding;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.incava.ex78viewbinding.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ViewBinding 은 라이브러리가 아니고 안드로이드 아키텍처 구성요소임
    // 그래서 그냥 기능만 ON 하면 됨. - build.gradle

    //activity_main.xml 과 연결되어 뷰들의 참조변수를 멤버로 가지고 있는 Binding클래스
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding 객체가 만든 뷰를 액티비티가 보여줘야 하기에..주석처리!!
        //setContentView(R.layout.activity_main);

        // Binding 객체생성 - activity_main.xml을 객체로 생성하여 액티비티에 뷰로 설정
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1) TextView 글씨 변경 - 이미 binding 객체가 TextView를 참조하고 있음
        binding.tv.setText("Nice to meet you");

        //2) button click event
        binding.btn.setOnClickListener(view->{
            binding.tv.setText("Have a good day");
        });

        //2.1) button long~click event -- 람다식으로
        binding.btn.setOnLongClickListener(view -> {
            Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
            return true;
        });

        binding.btn2.setOnClickListener(view -> {
            binding.tvResult.setText(binding.et.getText().toString());
            binding.et.setText("");
        });

    }

    ArrayList<ItemVO> items = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        items.add(new ItemVO("NEW YORK",R.drawable.newyork));
        items.add(new ItemVO("PARIS",R.drawable.paris));
        items.add(new ItemVO("SYDNEY",R.drawable.sydney));
        items.add(new ItemVO("NEW YORK",R.drawable.newyork));
        items.add(new ItemVO("PARIS",R.drawable.paris));
        items.add(new ItemVO("SYDNEY",R.drawable.sydney));
    }
}