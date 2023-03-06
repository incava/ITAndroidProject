package com.incava.ex32fragmentpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    MyAdapter myAdapter;
    TabLayout tabLayout;
    String[] tabTitle = new String[]{"TAB1", "TAB2", "TAB3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAdapter = new MyAdapter(this);
        viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(myAdapter);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int currentState = 0;
            int currentPos = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPos == position){
                    if (currentPos == 0) viewPager2.setCurrentItem(2);
                    else if(currentPos == 2) viewPager2.setCurrentItem(0);
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                currentState = state;
                Toast.makeText(MainActivity.this,currentState+"",Toast.LENGTH_SHORT).show();
                super.onPageScrollStateChanged(state);
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle[position]);
            }
        });
        tabLayoutMediator.attach();
    }
}