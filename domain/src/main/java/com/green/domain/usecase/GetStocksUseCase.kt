package com.green.domain.usecase

import android.util.Log
import com.green.domain.entity.StockEntity
import com.green.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStocksUseCase @Inject constructor(private val repository: Repository) {
    fun invoke() : Flowable<Collection<StockEntity>> {
        return repository.GetStocks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}