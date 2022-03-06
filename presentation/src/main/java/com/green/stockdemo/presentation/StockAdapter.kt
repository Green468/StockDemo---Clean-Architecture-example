package com.green.stockdemo.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.green.stockdemo.databinding.ViewStockBinding
import com.green.stockdemo.entity.StockItemViewModel

class StockAdapter(private val lifecycleOwner: LifecycleOwner, private val data: List<StockItemViewModel>, private val inflater: LayoutInflater) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    class StockViewHolder(lifecycleOwner: LifecycleOwner, val binding: ViewStockBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ViewStockBinding.inflate(inflater, parent, false)
        return StockViewHolder(lifecycleOwner, binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.binding.viewModel = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}