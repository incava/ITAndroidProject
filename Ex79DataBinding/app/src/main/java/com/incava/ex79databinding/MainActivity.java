package com.incava.ex79databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;

import com.incava.ex79databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //DataBinding - 뷰를 참조하지 않고 뷰가 보여주는 값을 가진 변수를 제어하는 방식.
    // 안드로이드 아키텍쳐 구성요소 이기에 사용설정만 하면 됨.

    //뷰 바인딩과 다르게 레이아웃 xml 파일의 최상위 요소가 반드시 <layout> 이여야만
    //바인딩 클래스들이 만들어짐.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setUser(new User("sam",20,false));
    }
}