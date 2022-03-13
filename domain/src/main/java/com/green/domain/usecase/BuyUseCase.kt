package com.green.domain.usecase

import android.util.Log
import com.green.domain.entity.PortfolioEntity
import com.green.domain.entity.SubscribeSource
import com.green.domain.repository.PortfolioRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuyUseCase @Inject constructor(val portfolioRepository: PortfolioRepository, val subscribeSource: SubscribeSource) {
    fun invoke(stockCode: String): Flowable<PortfolioEntity> {
        Log.d("Green", "Green test next page 0")
        return portfolioRepository.buyStock(stockCode).doOnNext {
            Log.d("Green", "Green test next page 1")
            subscribeSource.buyStatusPublish.onNext(it)
        }.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
    }
}