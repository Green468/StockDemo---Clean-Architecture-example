package com.green.domain.usecase

import com.green.domain.entity.StockCache
import com.green.domain.entity.StockEntity
import com.green.domain.entity.SubscribeSource
import com.green.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStocksUseCase @Inject constructor(private val repository: Repository, private val subscribeSource: SubscribeSource, private val stockCache: StockCache) {
    fun invoke() : Flowable<Collection<StockEntity>> {
        return repository.getStocks()
            .doOnNext {
                it.forEach { stock ->
                    stockCache.setStock(stock)
                }
                val stocks = it.map { s -> s.stockName }
                subscribeSource.subscribeStocks(stocks)
            }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}