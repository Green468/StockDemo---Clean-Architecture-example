package com.green.stockdemo.entity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.green.domain.entity.StockEntity
import com.green.domain.usecase.BuyUseCase
import com.green.domain.usecase.GetLineChartUseCase
import com.green.domain.usecase.SubscribeSingleStockUseCase
import com.green.stockdemo.customview.LineChartView
import io.reactivex.disposables.CompositeDisposable

class StockItemViewModel(stockEntity: StockEntity, subscribeSingleStockUseCase: SubscribeSingleStockUseCase, getLineChartUseCase: GetLineChartUseCase, val buyUseCase: BuyUseCase) {
    val stockName = stockEntity.stockName
    val priceStr: MutableLiveData<String> = MutableLiveData(stockEntity.price.toString())
    val priceBlinkColor: MutableLiveData<Int> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    val lineChart: MutableLiveData<LineChartView.LineChartModel> = MutableLiveData()

    init {
        compositeDisposable.add(
            subscribeSingleStockUseCase.invoke(stockEntity.stockName)
                .subscribe {
                    priceStr.value = it.price.toString()
                    priceBlinkColor.value = it.updateDirection
                }
        )
        compositeDisposable.add(
            getLineChartUseCase.invoke(stockEntity.stockName)
                .map {
                    LineChartView.LineChartModel(it.lineChartPoints, it.lastDone)
                }
                .subscribe {
                    lineChart.value = it
                }
        )
    }

    fun clear() {
        compositeDisposable.dispose()
    }

    fun buy() {
        compositeDisposable.add(buyUseCase.invoke(stockName).subscribe())
    }
}