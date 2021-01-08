package com.jintin.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jintin.recyclerviewsample.databinding.AdapterMainBinding

class MyAdapter : ListAdapter<MyData, MyViewHolder>(MyDiffUtil) {

    fun setData(list: List<MyData>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(AdapterMainBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

object MyDiffUtil : DiffUtil.ItemCallback<MyData>() {
    override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean {
        return oldItem == newItem
    }
}

class MyViewHolder(private val binding: AdapterMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: MyData) {
        binding.title.text = data.value
    }
}