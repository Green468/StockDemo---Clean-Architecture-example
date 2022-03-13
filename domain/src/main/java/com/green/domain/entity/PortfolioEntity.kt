package com.green.domain.entity

data class PortfolioEntity(
    val portfolioId: Int = 0,
    val stockName: String = "",
    val volume: Int = 0,
    val buyPrice: Double = 0.0,
    var price: Double = 0.0,
    var portfolioValue: Double = 0.0,
    var priceBlinkColor: Int = 0,
    var portfolioColor: Int = 0
)
