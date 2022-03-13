package com.green.data.repository

import com.green.domain.entity.PortfolioEntity
import com.green.domain.repository.PortfolioRepository
import io.reactivex.Flowable
import javax.inject.Inject

class FakePortfolioRepositoryImpl @Inject constructor(): PortfolioRepository {
    val portfolioList = mutableListOf<PortfolioEntity>()
    var portfolioNextId = 0

    override fun buyStock(stockCode: String): Flowable<PortfolioEntity> {
        val portfolioEntity = PortfolioEntity(
            portfolioId = generatePortfolioId(),
            stockName = stockCode,
            volume = 10,
            buyPrice = 5.0
        )
        portfolioList.add(portfolioEntity)
        return Flowable.fromCallable { portfolioEntity }
    }

    override fun getPortfolioList(): Flowable<Collection<PortfolioEntity>> {
        return Flowable.fromCallable { portfolioList }
    }

    fun generatePortfolioId(): Int {
        return portfolioNextId++
    }
}