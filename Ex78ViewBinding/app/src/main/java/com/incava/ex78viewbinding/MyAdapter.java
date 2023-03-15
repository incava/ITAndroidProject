package com.incava.ex78viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incava.ex78viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<ItemVO> items;

    public MyAdapter(Context context, ArrayList<ItemVO> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new VH(itemView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.binding.tv.setText(items.get(position).title);
        holder.binding.iv.setImageResource(items.get(position).imgResId);
    }

    class VH extends RecyclerView.ViewHolder{

        RecyclerItemBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerItemBinding.bind(itemView);//이미 바인딩이 되어있으므로 bind.

        }
    }


}
