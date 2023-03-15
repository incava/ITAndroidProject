package com.incava.ex86imageupload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {
    //이미지 파일은 택배상자[MultipartBody.Part]에 넣어서 전송함.
    //@Part 어노테이션을 사용. 단 @Multipart라는 어노테이션이 필요함.

    @Multipart
    @POST("Retrofit/fileUpload.php")
    Call<String> uploadImage(@Part MultipartBody.Part file);
}
