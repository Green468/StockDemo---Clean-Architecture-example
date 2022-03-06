package com.green.stockdemo.entity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.green.domain.entity.StockEntity
import com.green.stockdemo.customview.LineChartView

class StockItemViewModel(stockEntity: StockEntity) {
    val stockName = stockEntity.stockName
    var price = stockEntity.price
    val priceStr: MutableLiveData<String> = MutableLiveData(stockEntity.price.toString())
    val priceBlinkColor: MutableLiveData<Int> = MutableLiveData()
    val lineChart: MutableLiveData<LineChartView.LineChartModel> = MutableLiveData(
        LineChartView.LineChartModel(stockEntity.lineChartModel.lineChartPoints.map { t -> t }, stockEntity.price.toFloat())
    )

    fun updatePrice(newPrice: Double) {
        if (newPrice > price)
            priceBlinkColor.value = 1
        else if (newPrice < price)
            priceBlinkColor.value = -1
        price = newPrice
        priceStr.value = price.toString()
        lineChart.value?.apply {
            lastDone = newPrice.toFloat()
        }
        lineChart.value = lineChart.value
    }

    fun buy() {
        Log.d("Green", "Green buy stock: $stockName")
    }
}