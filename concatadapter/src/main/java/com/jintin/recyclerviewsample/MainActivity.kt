package com.jintin.recyclerviewsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.recyclerviewsample.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ConcatAdapter(getAdapter(100), getAdapter(200))
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            delay(500)
            adapter.addAdapter(1, getAdapter(300))
        }

    }

    private fun getAdapter(index: Int) = MyAdapter().also {
        it.setData(genData(index))
    }

    private fun genData(index: Int) = (index..index + 5).map {
        MyData(it.toString())
    }
}