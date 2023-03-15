package com.incava.ex85retrofit;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//인터페이스는 클래스와 비슷하지만 멤버로 추상메서드
public interface RetrofitService {

    //1.단순하게 GET방식으로 json파일을 읽어오기.

    @GET("Retrofit/board.json")
    Call<Item> getBoardJson();

    @GET("{aaa}/{bbb}")
    Call<Item> getBoardJsonByPath(@Path("aaa")String path, @Path("bbb")String fileName);

    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest(@Query("name")String name,@Query("msg")String message);

    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest2(@QueryMap Map<String,String> datas);

    @POST("Retrofit/postTest.php")
    Call<Item> postMethodTest(@Body Item item);

    @FormUrlEncoded
    @POST("Retrofit/postTest2.php")
    Call<Item> postMethodTest2(@Field("name")String name, @Field("msg")String age);

    @GET("Retrofit/boardArray.json")
    Call<ArrayList<Item>> getJsonBoardArray();

    @GET("Retrofit/board.json")
    Call<String> getJsonString();






}
