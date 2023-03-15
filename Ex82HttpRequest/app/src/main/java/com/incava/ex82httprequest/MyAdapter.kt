package com.incava.ex82httprequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.incava.ex82httprequest.databinding.RecyclerItemBinding

class MyAdapter(list : MutableList<ItemVO> ) : RecyclerView.Adapter<MyAdapter.VH>() {
    private var items : MutableList<ItemVO>
    init{
        items = list
    }

    inner class VH(private val binding: RecyclerItemBinding) : ViewHolder(binding.root) {
        fun bind(itemVO: ItemVO){
            binding.apply {
                tvNo.text = itemVO.no.toString()
                tvMsg.text = itemVO.msg
                tvDate.text = itemVO.date
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }


}