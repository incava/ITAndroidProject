package com.incava.ex78viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.incava.ex78viewbinding.databinding.FragmentMyBinding;

import java.util.ArrayList;

public class MyFragment extends Fragment {
    // 이 프레그먼트가 보여줄 뷰를 리턴해주는 기능메소드 재정의

    FragmentMyBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater,container,false); //container 사이즈 조정용. -> 안줘도 되지만 퍼포먼스 저하.
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btn.setOnClickListener(v->binding.tv.setText("Good day"));
    }


}
