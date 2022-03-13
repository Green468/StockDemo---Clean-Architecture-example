package com.green.domain.entity

data class LineChartEntity(
    var lineChartPoints: Collection<Float>,
    var lastDone: Float = 0f
)
