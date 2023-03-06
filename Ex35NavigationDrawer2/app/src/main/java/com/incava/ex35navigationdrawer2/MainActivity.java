package com.incava.ex35navigationdrawer2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    CircleImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav);
        View view = navigationView.getHeaderView(0);
        img = view.findViewById(R.id.civ);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.thumb_moana);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        //삼선 아이콘과 화살표아이콘이 자동으로 변환되도록.
        drawerLayout.addDrawerListener(drawerToggle);

        //NavigationView의 메뉴항목들을 선택했을때 반응하기

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if( item.getItemId() == R.id.menu_gallery){
                    Toast.makeText(MainActivity.this,"Gallery",Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.menu_send) {
                    Toast.makeText(MainActivity.this,"Gallery",Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.menu_aa){
                    Toast.makeText(MainActivity.this,"Gallery",Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.menu_bb){
                    Toast.makeText(MainActivity.this,"Gallery",Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }
}