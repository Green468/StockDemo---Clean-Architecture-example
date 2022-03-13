package com.green.stockdemo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.green.stockdemo.R
import com.green.stockdemo.databinding.FragmentPortfolioBinding
import com.green.stockdemo.viewmodel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioFragment : Fragment() {
    val viewModel: PortfolioViewModel by viewModels()
    val binding: FragmentPortfolioBinding by lazy { FragmentPortfolioBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.portfolioList.observe(viewLifecycleOwner) {
            binding.lstPortfolio.adapter = PortfolioAdapter(this, it, layoutInflater)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PortfolioFragment()
    }
}