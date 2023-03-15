package com.incava.ex85retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.incava.ex85retrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Http 통신 라이브러리 : OkHttp, Retrofit, Volley

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v->clickBtn());
        binding.btn2.setOnClickListener(v->clickBtn2());
        binding.btn3.setOnClickListener(v->clickBtn3());
        binding.btn4.setOnClickListener(v->clickBtn4());
        binding.btn5.setOnClickListener(v->clickBtn5());
        binding.btn6.setOnClickListener(v->clickBtn6());
        binding.btn7.setOnClickListener(v->clickBtn7());
        binding.btn8.setOnClickListener(v->clickBtn8());
    }

    void clickBtn8(){
        //서버의 응답결과가 json이 아닐때 사용.
        //서버의 응답결과를 그냥 String 으로 받아보기.[NO Parse]
        // 결과를 String으로 받으려면 ScalarsConverter가 필요함.<- 라이브러리.
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr/")
                .addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();
        Call<String> call = retrofit.create(RetrofitService.class).getJsonString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                binding.tv.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }



    void clickBtn7(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        Call<ArrayList<Item>> call = retrofit.create(RetrofitService.class).getJsonBoardArray();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                ArrayList<Item> items = response.body();
                binding.tv.setText("아이템 갯수 : " + items.size());
            }
            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }


    void clickBtn6(){
        String name= "ROSA";
        String message = "Have a good day";
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        Call<Item> call = retrofit.create(RetrofitService.class).postMethodTest2(name,message);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name+" , " + item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }

    void clickBtn5(){
        Item item = new Item("kim","Good afternoon");
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        Call<Item> call = retrofit.create(RetrofitService.class).postMethodTest(item);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                binding.tv.setText(response.body().name + response.body().msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }


    void clickBtn4(){
        //GET방식으로 전달할 데이터들을 MAP Collection으로 한방에 보내기.
        // 보낼 데이터들을 Map객체로 만들기.
        HashMap<String,String> datas = new HashMap<>();
        datas.put("name","robin");
        datas.put("msg","Nice to meet you");

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        Call<Item> call = retrofit.create(RetrofitService.class).getMethodTest2(datas);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name+" , " + item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }



    void clickBtn3(){
        String name = "홍길동";
        String message = "안녕하세요";
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        Call<Item> call = retrofit.create(RetrofitService.class).getMethodTest(name,message);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name+":"+item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("error : " + t);
            }
        });
    }


    void clickBtn2() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr/")
                .addConverterFactory(GsonConverterFactory.create());
        Call<Item> call = (builder.build().create(RetrofitService.class)).getBoardJsonByPath("Retrofit","board.json");
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText(item.name +item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }



    void clickBtn(){
        //get방식으로 json파일 읽기.
        //retrofit의 5단계.
        //1. retrofit 객체 생성.
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //2. 원하는 GET, POST 동작을 하는 인터페이스(작업 요구서)를 설계
        RetrofitService retrofitService =  retrofit.create(RetrofitService.class);
        Call<Item> call = retrofitService.getBoardJson();

        //4. 위에서 만든 서비스객체의 추상메서드를 호출해 실제 서버 작업을 수행하는 call이라는 객체 리턴받기.
        //call은 Retrofit이 가지고 있는
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                binding.tv.setText(call.toString());

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });





    }

}