package com.green.domain.entity

data class StockEntity(
    var stockName: String = "",
    var price: Double = 0.0,
    var updateDirection: Int = 0
)