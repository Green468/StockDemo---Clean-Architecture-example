package com.green.data.repository

import android.util.Log
import com.green.domain.entity.LineChartEntity
import com.green.domain.entity.StockEntity
import com.green.domain.repository.Repository
import com.green.domain.usecase.GetLineChartUseCase
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class FakeRepositoryImpl @Inject constructor() : Repository {
    val random = Random(15)
    var t = 1.0
    var lineChartMap = HashMap<String, LineChartEntity>()

    init {
        (1..100).forEach {
            val lineChart = LineChartEntity((0..5).map { t -> random.nextFloat() })
            lineChartMap["Stock $it"] = lineChart
        }
    }

    override fun getStocks(): Flowable<Collection<StockEntity>> {
        return Flowable.fromCallable {
            val stocks = mutableListOf<StockEntity>()
            (1..100).forEach {
                val entity = StockEntity("Stock $it", 1.0)
                stocks.add(entity)
            }
            stocks
        }
    }

    override fun subscribeStocks(stocks: Collection<String>): Flowable<Collection<StockEntity>> {
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

    override fun getLineChart(stockCode: String): Flowable<LineChartEntity> {
        return Flowable.fromCallable {
            lineChartMap[stockCode] ?: LineChartEntity(listOf())
        }
    }
}