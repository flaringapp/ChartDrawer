package com.flaringapp.chartdrawer.chart.line.renderers

import android.graphics.Color
import android.graphics.Paint
import com.flaringapp.chartdrawer.chart.renderer.ComplexRenderer
import com.flaringapp.chartdrawer.chart.renderer.ChartRenderer
import com.flaringapp.chartdrawer.chart.renderer.common.RendererPadding

// TODO stylize outside
class LineChartRenderer : ComplexRenderer<ChartRenderer>() {

    companion object {
        private const val PADDING_LEFT = 100f
        private const val PADDING_TOP = 60f
        private const val PADDING_RIGHT = 40f
        private const val PADDING_BOTTOM = 60f

        private val paddings =
            RendererPadding(PADDING_LEFT, PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM)
    }

    val axesRenderer = LineChartAxesRenderer(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            color = Color.BLACK
        },
        padding = paddings
    )

    val xAxisGuidelinesRenderer = LineChartXAxisGuidelinesRenderer(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 4f
            color = Color.LTGRAY
            alpha = 128
        },
        guidelineGap = 160f,
        padding = paddings,
    )

    val yAxisGuidelinesRenderer = LineChartYAxisGuidelinesRenderer(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 4f
            color = Color.LTGRAY
            alpha = 128
        },
        guidelineGap = 100f,
        padding = paddings,
    )

    val plotRenderer = LineChartPlotRenderer(
        Paint().apply {
            style = Paint.Style.FILL
            color = Color.BLUE
            isAntiAlias = true
        },
        Paint().apply {
            style = Paint.Style.STROKE
            color = Color.BLUE
            strokeWidth = 8f
            isAntiAlias = true
        },
        10f,
        padding = paddings
    )

    init {
        renderers += axesRenderer
        renderers += xAxisGuidelinesRenderer
        renderers += yAxisGuidelinesRenderer
        renderers += plotRenderer
    }

}