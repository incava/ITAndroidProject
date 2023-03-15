package com.incava.ex78viewbinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incava.ex78viewbinding.databinding.RecyclerItemBinding;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VH> {


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VH extends RecyclerView.ViewHolder{
        public VH(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
        }
    }

}
