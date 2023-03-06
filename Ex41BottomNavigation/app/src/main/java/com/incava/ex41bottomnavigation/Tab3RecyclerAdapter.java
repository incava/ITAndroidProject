package com.incava.ex41bottomnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incava.ex41bottomnavigation.R;
import com.incava.ex41bottomnavigation.Tab3RecyclerItem;

import java.util.ArrayList;

public class Tab3RecyclerAdapter extends RecyclerView.Adapter<Tab3RecyclerAdapter.VH> {

    Context context;
    ArrayList<Tab3RecyclerItem> items;

    public Tab3RecyclerAdapter(Context context, ArrayList<Tab3RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tv= itemView.findViewById(R.id.tv);
            iv= itemView.findViewById(R.id.iv);
        }
    }
}
