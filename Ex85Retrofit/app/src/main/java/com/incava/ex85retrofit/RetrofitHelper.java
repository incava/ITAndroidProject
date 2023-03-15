package com.incava.ex85retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    public static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr/")
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

}
