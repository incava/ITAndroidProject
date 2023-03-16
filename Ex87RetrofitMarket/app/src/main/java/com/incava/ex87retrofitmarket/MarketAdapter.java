package com.incava.ex87retrofitmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.incava.ex87retrofitmarket.MarketItem;
import com.incava.ex87retrofitmarket.R;
import com.incava.ex87retrofitmarket.databinding.RecyclerItemBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.VH>{

    Context context;
    ArrayList<MarketItem> items;

    public MarketAdapter(Context context, ArrayList<MarketItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MarketItem item= items.get(position);
        holder.binding.tvTitle.setText(item.title);
        holder.binding.tvMsg.setText(item.msg);
        holder.binding.tvPrice.setText(item.price +"원");
        String address="";
        if(item.image!=null) address= "http://incava.dothome.co.kr/RetrofitMarket/" + item.image;
        Glide.with(context).load(address).error(R.drawable.paris).into(holder.binding.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        RecyclerItemBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerItemBinding.bind(itemView);
            itemView.setOnLongClickListener(view -> {
                MarketItem item = items.get(getLayoutPosition());
                //Retrofit을 이용해 DB에서 해당 아이템을 삭제요청.
                Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
                Call<String> call = retrofit.create(RetrofitService.class).deleteItem(item.no);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                        items.remove(getLayoutPosition());
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                return true;
            });
        }
    }
}
