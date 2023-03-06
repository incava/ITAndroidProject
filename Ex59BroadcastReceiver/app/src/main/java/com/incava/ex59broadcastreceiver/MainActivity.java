package com.incava.ex59broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());

    }

    void clickBtn(){
        // 명시적 인텐트
//        Intent intent = new Intent(this, MyReceiver.class);
//        sendBroadcast(intent);

        // 암시적 인텐트, Oreo 버전부터 암시적 인텐트 방송은 System만 할 수 있도록 제한.
        //  그럼에도 곡 암시적 인텐트를 보내고 싶다면 리시버를 매니페스트가 아닌 자바에서 동적 등록으로 테스트 가능.
        Intent intent = new Intent();
        intent.setAction("aaa");

    }

    MyReceiver myReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        // 테스트로 가능할듯?
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("aaa");
        registerReceiver(myReceiver,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }


}