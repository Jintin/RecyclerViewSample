package com.jintin.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jintin.recyclerviewsample.databinding.AdapterMainBinding

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    //    private var list = listOf<MyData>()
    private val list = mutableListOf<MyData>()

    fun setData(list: List<MyData>) {
        val callback = MyDiffUtil(this.list, list)
        val result = DiffUtil.calculateDiff(callback)
        this.list.clear()
        this.list.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(AdapterMainBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
}

class MyDiffUtil(
    private val oldList: List<MyData>,
    private val newList: List<MyData>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

class MyViewHolder(private val binding: AdapterMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: MyData) {
        binding.title.text = data.value
    }
}