package com.green.domain.repository

import com.green.domain.entity.PortfolioEntity
import io.reactivex.Flowable

interface PortfolioRepository {
    fun buyStock(stockCode: String): Flowable<PortfolioEntity>
    fun getPortfolioList(): Flowable<Collection<PortfolioEntity>>
}