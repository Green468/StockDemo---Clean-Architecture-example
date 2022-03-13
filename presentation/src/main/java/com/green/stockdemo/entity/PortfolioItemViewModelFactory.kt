package com.green.stockdemo.entity

import com.green.domain.entity.PortfolioEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioItemViewModelFactory @Inject constructor() {
    fun create(portfolioEntity: PortfolioEntity): PortfolioItemViewModel {
        return PortfolioItemViewModel(portfolioEntity)
    }
}