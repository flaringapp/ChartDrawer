package com.flaringapp.chartdrawer.chart.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import com.flaringapp.chartdrawer.chart.renderer.ViewportRenderer
import com.flaringapp.chartdrawer.chart.renderer.common.RendererPadding
import com.flaringapp.chartdrawer.chart.renderer.properties.RendererProperties

class LineChartYAxisGuidelinesRenderer(
    private val guidelinePaint: Paint,
    private val guidelineGap: Float,
    private val padding: RendererPadding,
) : ViewportRenderer() {

    private val startX: Float = padding.left
    private var endX: Float = 0f

    private val topY: Float = padding.top
    private var bottomY: Float = 0f

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        var bottomGapOffset = properties.translateY % guidelineGap
        if (bottomGapOffset < 0) bottomGapOffset += guidelineGap

        endX = properties.width - padding.right
        bottomY = properties.height - bottomGapOffset - padding.bottom
    }

    override fun render(canvas: Canvas) {
        var drawingY = bottomY
        while (drawingY >= topY) {
            drawGuideline(canvas, drawingY)
            drawingY -= guidelineGap
        }
    }

    private fun drawGuideline(canvas: Canvas, guidelineY: Float) {
        canvas.drawLine(
            startX, guidelineY,
            endX, guidelineY,
            guidelinePaint
        )
    }
}