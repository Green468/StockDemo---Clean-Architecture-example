package com.green.domain.usecase

import com.green.domain.entity.PortfolioEntity
import com.green.domain.entity.SubscribeSource
import com.green.domain.repository.PortfolioRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPortfolioListUseCase @Inject constructor(val portfolioRepository: PortfolioRepository, val subscribeSource: SubscribeSource) {
    fun invoke(): Flowable<Collection<PortfolioEntity>> {
        return portfolioRepository.getPortfolioList()
            .doOnNext {
                val stocks = mutableListOf<String>()
                it.forEach { pe ->
                    if (!stocks.contains(pe.stockName))
                        stocks.add(pe.stockName)
                }
                subscribeSource.subscribeStocks(stocks)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}