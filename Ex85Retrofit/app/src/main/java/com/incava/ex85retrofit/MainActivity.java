package com.incava.ex85retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.incava.ex85retrofit.databinding.ActivityMainBinding;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Http 통신 라이브러리 : OkHttp, Retrofit, Volley

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v->clickBtn());

    }

    void clickBtn(){
        //get방식으로 json파일 읽기.
        //retrofit의 5단계.
        //1. retrofit 객체 생성.
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //2. 원하는 GET, POST 동작을 하는 인터페이스(작업 요구서)를 설계



    }

}