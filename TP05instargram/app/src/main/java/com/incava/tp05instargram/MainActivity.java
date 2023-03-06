package com.incava.tp05instargram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    HorizontalScrollView horizontalScrollView;
    Random rnd;

    ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    ImageView ad;

    int[] array = new int[]{R.drawable.newyork,R.drawable.paris,R.drawable.sydney};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalScrollView = findViewById(R.id.horizon_scv);
        rnd = new Random();
        iv1 = findViewById(R.id.iv_picture);
        iv2 = findViewById(R.id.ib_heart);
        iv3 = findViewById(R.id.ib_message);
        iv4 = findViewById(R.id.ib_send);
        iv5 = findViewById(R.id.ib_save);
        iv6 = findViewById(R.id.ib_setting);

        iv2.setOnClickListener(listener);
        iv3.setOnClickListener(listener);
        iv4.setOnClickListener(listener);
        iv5.setOnClickListener(listener);
        iv6.setOnClickListener(listener);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.picture);
                AlertDialog dialog = builder.create();
                dialog.show();
                ad = dialog.findViewById(R.id.iv_alert_view);
                ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i++;
                        int a = rnd.nextInt(3);
//                        switch (i%3) {
//                            case 0:
//                                ad.setImageResource(R.drawable.newyork);
//                                break;
//                            case 1:
//                                ad.setImageResource(R.drawable.sydney);
//                                break;
//                            case 2:
//                                ad.setImageResource(R.drawable.paris);
//                                break;
//                        }
                        Toast.makeText(MainActivity.this, a+"", Toast.LENGTH_SHORT).show();
                        ad.setImageResource(array[a]);
                    }
                });
            }
        });


        horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Toast.makeText(MainActivity.this,"overflow",Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView a = findViewById(view.getId());
            Toast.makeText(MainActivity.this,a.getTag().toString(),Toast.LENGTH_SHORT).show();

        }
    };






}