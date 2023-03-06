package com.incava.tp08todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    Context context;
    ArrayList<RecyclerItem> list;

    public RecyclerAdapter(Context context, ArrayList<RecyclerItem> items) {
        this.context = context;
        this.list = items;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView name, nick, theme, body;

        public VH(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            nick = view.findViewById(R.id.nick);
            theme = view.findViewById(R.id.theme);
            body = view.findViewById(R.id.body);
        }
        void onBind (RecyclerItem item){
            name.setText(item.name);
            nick.setText(item.nick);
            theme.setText(item.theme);
            body.setText(item.body);
        }
    }


}
