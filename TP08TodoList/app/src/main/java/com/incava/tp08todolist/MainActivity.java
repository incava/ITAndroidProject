package com.incava.tp08todolist;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<RecyclerItem> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;




    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(this,list);
        recyclerView.setAdapter(adapter);
        floatingActionButton = findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(view ->{
            Intent intent = new Intent(this,WriteActivity.class);
            launcher.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> launcher =  registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    Intent intent = result.getData();
                    String name = intent.getStringExtra("name");
                    String nick = intent.getStringExtra("nick");
                    String theme = intent.getStringExtra("theme");
                    String body = intent.getStringExtra("body");
                    RecyclerItem item = new RecyclerItem(name,nick,theme,body);
                    list.add(0,item);
                    adapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                } else if (result.getResultCode() == RESULT_CANCELED) {
                    Toast.makeText(this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
    );
}