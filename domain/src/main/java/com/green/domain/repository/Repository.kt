package com.green.domain.repository

import com.green.domain.entity.StockEntity
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun GetStocks() : Flowable<Collection<StockEntity>>
    fun SubscribeStocks(stocks: Collection<String>) : Flowable<Collection<StockEntity>>
}