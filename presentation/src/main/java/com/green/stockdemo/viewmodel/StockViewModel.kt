package com.green.stockdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.green.domain.usecase.GetStocksUseCase
import com.green.domain.usecase.SubscribeStocksUseCase
import com.green.stockdemo.entity.StockItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(val getStocksUseCase: GetStocksUseCase, val subscribeStocksUseCase: SubscribeStocksUseCase) : ViewModel() {
    val stocksLiveData: MutableLiveData<List<StockItemViewModel>> by lazy { MutableLiveData() }
    val stocksMap: HashMap<String, StockItemViewModel> by lazy { HashMap() }
    val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(getStocksUseCase.invoke()
            .flatMap { Flowable.fromCallable { it.map {
                val itemVM = StockItemViewModel(it)
                stocksMap[it.stockName] = itemVM
                itemVM
            } } }
            .subscribe {
                stocksLiveData.value = it
                subscribeStocks()
            })
    }

    private fun subscribeStocks() {
        compositeDisposable.add(subscribeStocksUseCase.invoke(stocksMap.keys)
            .flatMap {
                Flowable.fromIterable(it)
            }
            .subscribe {
                stocksMap[it.stockName]?.updatePrice(it.price)
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}