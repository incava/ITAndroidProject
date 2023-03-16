package com.incava.ex87retrofitmarket;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.incava.ex87retrofitmarket.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;
    //업로드 할 이미지의 파일경로 변수
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("글 작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnSelect.setOnClickListener(v -> clickSelect());
        binding.btnComplete.setOnClickListener(v -> clickComplete());
    }


    void clickSelect() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode()==RESULT_CANCELED) return;
                Uri uri = result.getData().getData();
                Glide.with(this).load(uri).into(binding.iv);
                //레트로핏을 이용하여 서버에 이미지를 보낼 때는 Uri주소가 아니라 파일의 주소가 필요하다.
                //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
                imgPath = getFilePathFromUri(uri);
                //new AlertDialog.Builder(this).setMessage(imgPath).show();
            });

    String getFilePathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return result;
    }


    void clickComplete(){
        // 사용자가 입력한 데이터를 서버에 전송하기
        // 전송할 데이터 [name, title, message,price,imgPath(파일)]

        String name = binding.etName.getText().toString();
        String title = binding.etTitle.getText().toString();
        String message = binding.etMsg.getText().toString();
        String price = binding.etPrice.getText().toString();
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();

        HashMap<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("title",title);
        map.put("message",message);
        map.put("price",price);

        MultipartBody.Part filepart = null;
        if(imgPath!=null){
            File file = new File(imgPath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"),file);
            filepart = MultipartBody.Part.createFormData("img",file.getName(),body);
        }

        Call<String> call = retrofit.create(RetrofitService.class).postDataToServer(map,filepart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Toast.makeText(EditActivity.this, s, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
               Log.e("error",t.getMessage());
                finish();
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}