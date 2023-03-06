package com.incava.ex38floationactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ExtendedFloatingActionButton exfab;
    CoordinatorLayout coordinatorLayout;

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.snackBar_container);
        exfab = findViewById(R.id.fab);
        exfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (exfab.isExtended()){
                    Snackbar.make(coordinatorLayout,"clicked ADD", Snackbar.LENGTH_INDEFINITE)
                            .setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this,"add item",Toast.LENGTH_SHORT).show();
                            exfab.shrink();
                        }
                    }).show();
                }else{
                    exfab.extend();
                }

            }
        });


    }
}