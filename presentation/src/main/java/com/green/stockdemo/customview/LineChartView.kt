package com.green.stockdemo.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class LineChartView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    data class LineChartModel(
        val chartPoints: Collection<Float>,
        var lastDone: Float
    )

    var lineChartModel: LineChartModel? = null
    val chartPathUpPaint = Paint().apply {
        setARGB(255, 0, 255, 0)
        strokeWidth = 4f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
    }

    val chartLastDonePaint = Paint().apply {
        setARGB(255, 0, 0, 255)
        strokeWidth = 4f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
    }

    val chartPathDownPaint = Paint().apply {
        setARGB(255, 255, 0, 0)
        strokeWidth = 4f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawARGB(255, 255, 255, 255)
        lineChartModel?.apply {
            if (chartPoints.isNotEmpty()) {
                val chartPoints = this.chartPoints.toMutableList()
                chartPoints.add(lastDone)
                val paint = if (chartPoints.last() > chartPoints.first()) chartPathUpPaint else chartPathDownPaint
                var minPointValue = chartPoints.minOf { t -> t }
                if (minPointValue > lastDone) minPointValue = lastDone
                var maxPointValue = chartPoints.maxOf { t -> t }
                if (maxPointValue < lastDone) maxPointValue = lastDone
                val lastPointIndex = chartPoints.size - 1
                val diffValue = maxPointValue - minPointValue
                if (diffValue > 0) {
                    val maxY = height * 0.8f
                    val minY = height * 0.2f
                    val diffY = maxY - minY
                    val lastDoneY = diffY * (maxPointValue - lastDone) / diffValue + minY
                    canvas?.drawLine(0f, lastDoneY, width.toFloat(), lastDoneY, chartLastDonePaint)
                    val path = Path()
                    val pointYList = chartPoints.map { t -> diffY * (maxPointValue - t) / diffValue + minY }
                    val pointXList = (0..lastPointIndex).map { t -> t.toFloat() / lastPointIndex * width }
                    path.moveTo(pointXList[0], pointYList[0])
                    for (i in 1..lastPointIndex) {
                        path.lineTo(pointXList[i], pointYList[i])
                    }
                    canvas?.drawPath(path, paint)
                }
                else {
                    canvas?.drawLine(0f, height.toFloat() / 2, 0f, height.toFloat() / 2, chartLastDonePaint)
                }
            }
            else {
                canvas?.drawLine(0f, height.toFloat() / 2, 0f, height.toFloat() / 2, chartLastDonePaint)
            }
        }
    }
}