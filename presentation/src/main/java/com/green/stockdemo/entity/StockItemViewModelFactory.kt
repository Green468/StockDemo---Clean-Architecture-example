package com.green.stockdemo.entity

import com.green.domain.entity.StockEntity
import com.green.domain.usecase.BuyUseCase
import com.green.domain.usecase.GetLineChartUseCase
import com.green.domain.usecase.SubscribeSingleStockUseCase
import javax.inject.Inject

class StockItemViewModelFactory @Inject constructor(private val subscribeSingleStockUseCase: SubscribeSingleStockUseCase, private val getLineChartUseCase: GetLineChartUseCase, private val buyUseCase: BuyUseCase) {
    fun create(stockEntity: StockEntity) : StockItemViewModel {
        return StockItemViewModel(stockEntity, subscribeSingleStockUseCase, getLineChartUseCase, buyUseCase)
    }
}