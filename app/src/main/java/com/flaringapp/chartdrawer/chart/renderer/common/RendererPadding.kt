package com.flaringapp.chartdrawer.chart.renderer.common

import android.graphics.RectF

class RendererPadding(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
) {

    fun asRect(width: Int, height: Int): RectF {
        return asRect(width.toFloat(), height.toFloat())
    }

    fun asRect(width: Float, height: Float): RectF {
        return RectF(left, top, width - right, height - bottom)
    }

}