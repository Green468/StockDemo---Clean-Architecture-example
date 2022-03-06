package com.green.data.repository

import android.util.Log
import com.green.domain.entity.LineChartEntity
import com.green.domain.entity.StockEntity
import com.green.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class FakeRepositoryImpl @Inject constructor() : Repository {
    var stocks: List<StockEntity>
    val random = Random(15)

    init {
        Log.d("Green", "Green FakeRepositoryImpl created again")
        val stocks = mutableListOf<StockEntity>()
        for (i in 1..100) {
            val entity = StockEntity("Stock " + i, 1.0, LineChartEntity(
                (0..5).map { t -> random.nextFloat() }
            ))
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
                    StockEntity("Stock 1", t, this.stocks[0].lineChartModel),
                    StockEntity("Stock 2", t, this.stocks[0].lineChartModel),
                    StockEntity("Stock 3", t, this.stocks[0].lineChartModel)
                )
            }
        }
    }
}