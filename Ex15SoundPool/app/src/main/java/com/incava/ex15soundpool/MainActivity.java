package com.incava.ex15soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;

    SoundPool sp;

    int sdStart, sdAgain, sdGoodJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        sp = builder.build();
        sdStart = sp.load(this,R.raw.s_sijak,0);
        sdAgain = sp.load(this,R.raw.s_again,0);
        sdGoodJob = sp.load(this,R.raw.s_goodjob,0);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdStart,0.9f,0.9f,3,0,1.0f);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdAgain,0.9f,0.9f,3,0,1.0f);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdGoodJob,0.9f,0.9f,3,0,1.0f);

            }
        });


    }
}