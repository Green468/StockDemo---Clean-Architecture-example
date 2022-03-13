package com.green.domain.entity

import com.green.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscribeSource @Inject constructor(val repository: Repository) {
    var stockPublish = PublishSubject.create<StockEntity>()
    var buyStatusPublish = PublishSubject.create<PortfolioEntity>()
    var compositeDisposable = CompositeDisposable()

    fun subscribeStocks(stocks: Collection<String>) {
        compositeDisposable.dispose()
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(repository.subscribeStocks(stocks)
            .flatMap {
                Flowable.fromIterable(it)
            }
            .subscribe {
            stockPublish.onNext(it)
        })
    }

    fun onClear() {
        compositeDisposable.dispose()
    }
}