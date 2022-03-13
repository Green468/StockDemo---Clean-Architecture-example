package com.green.domain.usecase

import com.green.domain.entity.StockEntity
import com.green.domain.repository.Repository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscribeStocksUseCase @Inject constructor(private val repository: Repository) {
    fun invoke(stocks: Collection<String>) : Flowable<Collection<StockEntity>> {
        return repository.subscribeStocks(stocks).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}