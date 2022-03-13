package com.green.domain.usecase

import com.green.domain.entity.StockCache
import com.green.domain.entity.StockEntity
import com.green.domain.entity.SubscribeSource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscribeSingleStockUseCase @Inject constructor(private val subscribeSource: SubscribeSource, private val stockCache: StockCache) {
    fun invoke(stockCode: String) : Flowable<StockEntity> {
        return subscribeSource.stockPublish.toFlowable(BackpressureStrategy.LATEST)
            .filter { it.stockName == stockCode }
            .map {
                stockCache.updateStock(it)
                stockCache.getStock(stockCode) ?: it
            }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}