package com.incava.ex79databinding;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    //일반 자료형은 값이 변경되어도 화면갱신이 이루어지지 않음.

//    public String name;
//    public int age;
//    public boolean fav;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();
    public ObservableBoolean fav = new ObservableBoolean();


    public User(String name, int age, boolean fav) {
        this.name.set(name);
        this.age.set(age);
        this.fav.set(fav);
    }

    public void changeText(View view){
        this.name.set("Robin");
    }

    public void increaseAge(View view){
        this.age.set(this.age.get() +1);
    }


}
