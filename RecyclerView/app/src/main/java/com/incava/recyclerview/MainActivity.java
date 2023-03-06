package com.incava.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview_region);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //세번째 파라미터 i : 클릭한 아이템의 위치 인덱스 번호 .. 0.1.2.3.4.
                String[] datas = getResources().getStringArray(R.array.datas);
                Toast.makeText(MainActivity.this,datas[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}