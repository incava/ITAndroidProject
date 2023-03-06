package com.incava.ex41bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Fragment[] fragments = new Fragment[3];

    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.bnv);
        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment,fragments[0]).commit();

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bnv_aaa) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[0]).commit();
                else if(item.getItemId() == R.id.bnv_bbb) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[1]).commit();
                else if(item.getItemId() == R.id.bnv_ccc) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,fragments[2]).commit();
                return true;
            }
        });



    }
}