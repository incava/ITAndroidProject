package com.incava.ex44startactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    String s;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
                resultLauncher.launch(intent);
            }
        );
    }
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        s = intent.getStringExtra("name");
                        a = intent.getIntExtra("age",0);
                        tv.setText(s+a);
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(MainActivity.this, "취소했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );



}