package com.green.domain.entity

import com.green.domain.entity.StockEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockCache @Inject constructor() {
    val hashMap = WeakHashMap<String, StockEntity>()
    val lineChartMap = WeakHashMap<String, LineChartEntity>()

    fun getStock(stockCode: String): StockEntity? {
        return hashMap[stockCode]
    }

    fun setStock(stockEntity: StockEntity) {
        hashMap[stockEntity.stockName] = stockEntity
    }

    fun updateStock(stockEntity: StockEntity) {
        val stock = hashMap[stockEntity.stockName]
        stock?.apply {
            val d = stockEntity.price - price
            updateDirection = if (d > 0) 1 else if (d < 0) -1 else 0
            price = stockEntity.price
        }
        hashMap[stockEntity.stockName] = stock ?: stockEntity
        val lineChart = lineChartMap[stockEntity.stockName]
        lineChart?.apply {
            lastDone = (stock?.price ?: stockEntity.price).toFloat()
        }
    }

    fun getLineChart(stockCode: String): LineChartEntity? {
        return lineChartMap[stockCode]
    }

    fun setLineChart(stockCode: String, lineChartEntity: LineChartEntity) {
        lineChartMap[stockCode] = lineChartEntity
    }
}