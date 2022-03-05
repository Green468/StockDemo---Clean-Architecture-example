package com.green.stockdemo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.green.stockdemo.R
import com.green.stockdemo.databinding.FragmentStockBinding
import com.green.stockdemo.viewmodel.StockViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StockFragment : Fragment() {
    val viewModel: StockViewModel by viewModels()
    val binding: FragmentStockBinding by lazy { FragmentStockBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.stocksLiveData.observe(viewLifecycleOwner) {
            binding.lstStock.adapter = StockAdapter(this, it, layoutInflater)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = StockFragment()
    }
}