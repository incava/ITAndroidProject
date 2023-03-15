package com.incava.ex80activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //Activity 클래스의 Lifecycle Method
    // 액티비티가 객체로 만들어져서 화면에 보여지고,
    // 종료되어 메모리에서 사라질때 까지 상황에 따라 자동으로 실행되는 생명주기 콜백 메서드.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Tag : ", "onCreate");
        findViewById(R.id.btn).setOnClickListener(v->{
            startActivity(new Intent(this, SecondActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tag : ","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tag : ","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tag : ","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tag : ","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tag : ","onDestroy");
    }
}