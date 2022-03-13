package com.green.domain.usecase

import com.green.domain.entity.LineChartEntity
import com.green.domain.entity.StockCache
import com.green.domain.entity.SubscribeSource
import com.green.domain.repository.Repository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLineChartUseCase @Inject constructor(private val repository: Repository, private val subscribeSource: SubscribeSource, private val stockCache: StockCache) {
    fun invoke(stockCode: String): Flowable<LineChartEntity> {
        return repository.getLineChart(stockCode)
            .doOnNext {
                stockCache.setLineChart(stockCode, it)
            }
            .mergeWith(
                subscribeSource.stockPublish.toFlowable(BackpressureStrategy.LATEST)
                    .flatMap {
                    val f = Flowable.fromCallable {
                        stockCache.getLineChart(stockCode) ?: LineChartEntity(listOf())
                    }
                    f
                }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}