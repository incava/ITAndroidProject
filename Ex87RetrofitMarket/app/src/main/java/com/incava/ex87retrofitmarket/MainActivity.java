package com.incava.ex87retrofitmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.incava.ex87retrofitmarket.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fabEdit.setOnClickListener(v -> clickFab());


        binding.refreshLayout.setOnRefreshListener(() -> {
            loadData();
            binding.refreshLayout.setRefreshing(false); //로딩 아이콘 제거
        });
    }

        @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    void loadData(){
        Call<ArrayList<MarketItem>> call = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class).loadDataFromServer();
        call.enqueue(new Callback<ArrayList<MarketItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MarketItem>> call, Response<ArrayList<MarketItem>> response) {
                ArrayList<MarketItem> arrayList = response.body();
                Toast.makeText(MainActivity.this, arrayList.size()+"", Toast.LENGTH_SHORT).show();
                MarketAdapter adapter= new MarketAdapter(MainActivity.this, arrayList);
                binding.recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<MarketItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void clickFab(){
        startActivity(new Intent(this,EditActivity.class));
    }


}