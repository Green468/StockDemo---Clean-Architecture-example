package com.green.stockdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.green.domain.usecase.GetStocksUseCase
import com.green.stockdemo.entity.StockItemViewModel
import com.green.stockdemo.entity.StockItemViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(val getStocksUseCase: GetStocksUseCase, val stockItemViewModelFactory: StockItemViewModelFactory) : ViewModel() {
    val stocksLiveData: MutableLiveData<List<StockItemViewModel>> by lazy { MutableLiveData() }
    val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(getStocksUseCase.invoke()
            .flatMap { Flowable.fromCallable { it.map {
                val itemVM = stockItemViewModelFactory.create(it)
                itemVM
            } } }
            .subscribe {
                stocksLiveData.value = it
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        stocksLiveData.value?.forEach {
            it.clear()
        }
    }
}