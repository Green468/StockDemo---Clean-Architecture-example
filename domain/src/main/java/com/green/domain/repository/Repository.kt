package com.green.domain.repository

import com.green.domain.entity.LineChartEntity
import com.green.domain.entity.StockEntity
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun getStocks() : Flowable<Collection<StockEntity>>
    fun subscribeStocks(stocks: Collection<String>) : Flowable<Collection<StockEntity>>
    fun getLineChart(stockCode: String) : Flowable<LineChartEntity>
}