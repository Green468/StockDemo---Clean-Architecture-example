package com.green.stockdemo.entity

import androidx.lifecycle.MutableLiveData
import com.green.domain.entity.PortfolioEntity

class PortfolioItemViewModel(val portfolioEntity: PortfolioEntity) {
    val stockName = portfolioEntity.stockName
    val priceStr = MutableLiveData<String>(portfolioEntity.price.toString())
    val buyPriceStr = portfolioEntity.buyPrice.toString()
    val volumeStr = portfolioEntity.volume.toString()
    val portfolioValue = MutableLiveData<String>(portfolioEntity.portfolioValue.toString())
    val priceBlinkColor = MutableLiveData<Int>(portfolioEntity.priceBlinkColor)
    val portfolioColor = MutableLiveData<Int>(portfolioEntity.portfolioColor)

    fun sell() {

    }
}