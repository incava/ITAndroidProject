package com.incava.ex32fragmentpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {

    Fragment[] fragment = new Fragment[3];

    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragment[0] = new Tab1Fragment();
        fragment[1] = new Tab2Fragment();
        fragment[2] = new Tab3Fragment();

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragment[position];
    }

    @Override
    public int getItemCount() {
        return fragment.length;
    }
}
