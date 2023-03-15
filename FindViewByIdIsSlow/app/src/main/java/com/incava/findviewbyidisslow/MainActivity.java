package com.incava.findviewbyidisslow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.incava.findviewbyidisslow.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 간단하게 Button 하나, AndroidButton, ButtonTextView 하나를 설정.
    ArrayList<Long> findView = new ArrayList<>();
    ArrayList<Long> alreadyFindView = new ArrayList<>();
    TextView textView;
    Button btn, btnX;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        btn = findViewById(R.id.btn_default);
        btnX = findViewById(R.id.btn_androidx);
        for (int j = 0; j <10; j++) {
            // findViewById로 textView값을  가져오는 경우
            long l = System.nanoTime();
                for (int i = 0; i <100; i++) {
                    textView = findViewById(R.id.tv);
                    textView.getText();
                }
            findView.add(System.nanoTime()-l);

            // ViewBinding으로 textView값을 가져오는 경우
            l = System.nanoTime();
            for (int i = 0; i <100; i++) {
                binding.tv.getText();
            }
            alreadyFindView.add(System.nanoTime()-l);

            Log.i("result","findView : " + findView.get(findView.size() - 1) +
                    "  ViewBinding : " + alreadyFindView.get(alreadyFindView.size() -1));
        }
    }
}