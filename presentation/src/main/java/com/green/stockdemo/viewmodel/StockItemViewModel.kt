package com.green.stockdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.green.domain.entity.StockEntity

class StockItemViewModel(stockEntity: StockEntity) {
    val stockName = stockEntity.stockName
    var price = stockEntity.price
    val priceStr: MutableLiveData<String> = MutableLiveData(stockEntity.price.toString())
    val priceBlinkColor: MutableLiveData<Int> = MutableLiveData()

    fun updatePrice(newPrice: Double) {
        if (newPrice > price)
            priceBlinkColor.value = 1
        else if (newPrice < price)
            priceBlinkColor.value = -1
        price = newPrice
        priceStr.value = price.toString()
    }

    fun buy() {
        Log.d("Green", "Green buy stock: $stockName")
    }
}