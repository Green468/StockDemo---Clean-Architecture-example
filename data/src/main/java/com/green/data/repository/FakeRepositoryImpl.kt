package com.green.data.repository

import android.util.Log
import com.green.domain.entity.StockEntity
import com.green.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeRepositoryImpl @Inject constructor() : Repository {
    var stocks: List<StockEntity>

    init {
        val stocks = mutableListOf<StockEntity>()
        for (i in 1..100) {
            val entity = StockEntity("Stock " + i, 1.0)
            stocks.add(entity)
        }
        this.stocks = stocks
    }

    var t = 1.0

    override fun GetStocks(): Flowable<Collection<StockEntity>> {
        return Flowable.fromCallable { stocks }
    }

    override fun SubscribeStocks(stocks: Collection<String>): Flowable<Collection<StockEntity>> {
        return Flowable.interval(1, TimeUnit.SECONDS).flatMap {
            t += 1
            if (t > 10)
                t = 1.0
            Flowable.fromCallable {
                listOf (
                    StockEntity("Stock 1", t),
                    StockEntity("Stock 2", t),
                    StockEntity("Stock 3", t)
                )
            }
        }
    }
}