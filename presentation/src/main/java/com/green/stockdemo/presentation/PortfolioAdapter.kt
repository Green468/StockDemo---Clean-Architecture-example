package com.green.stockdemo.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.green.stockdemo.databinding.ViewPortfolioBinding
import com.green.stockdemo.entity.PortfolioItemViewModel

class PortfolioAdapter(val lifecycleOwner: LifecycleOwner, val portfolioList: List<PortfolioItemViewModel>, val layoutInflater: LayoutInflater): RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {
    class ViewHolder(val lifecycleOwner: LifecycleOwner, val binding: ViewPortfolioBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPortfolioBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(lifecycleOwner, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = portfolioList[position]
    }

    override fun getItemCount(): Int {
        return portfolioList.size
    }
}