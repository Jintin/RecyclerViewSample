package com.jintin.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.jintin.recyclerviewsample.databinding.AdapterMainBinding

class MySortedListCallback(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) :
    SortedListAdapterCallback<MyData>(adapter) {

    override fun compare(o1: MyData?, o2: MyData?): Int {
        return (o1?.id ?: 0) - (o2?.id ?: 0)
    }

    override fun areContentsTheSame(
        oldItem: MyData?,
        newItem: MyData?
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(item1: MyData?, item2: MyData?): Boolean {
        return item1?.id == item2?.id
    }
}

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private val list = SortedList(
        MyData::class.java,
        MySortedListCallback(this)
    )

    fun addData(item: MyData) {
        list.add(item)
    }

    fun removeData(item: MyData) {
        list.remove(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(AdapterMainBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size()
    }
}

class MyViewHolder(private val binding: AdapterMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: MyData) {
        binding.title.text = data.value
    }
}