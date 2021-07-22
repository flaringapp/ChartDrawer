package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ViewportRenderer
import com.flaringapp.graphdrawer.graph.renderer.common.RendererPadding
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

class LineGraphXAxisGuidelinesRenderer(
    private val guidelinePaint: Paint,
    private val guidelineGap: Float,
    private val padding: RendererPadding,
) : ViewportRenderer() {

    private var startX: Float = 0f
    private var endX: Float = 0f

    private val topY: Float = padding.top
    private var bottomY: Float = 0f

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        var leftGapOffset = properties.translateX % guidelineGap
        if (leftGapOffset < 0) leftGapOffset += guidelineGap


        startX = leftGapOffset + padding.left
        endX = properties.width - padding.right
        bottomY = properties.height - padding.bottom
    }

    override fun render(canvas: Canvas) {
        var drawingX = startX
        while (drawingX <= endX) {
            drawGuideline(canvas, drawingX)
            drawingX += guidelineGap
        }
    }

    private fun drawGuideline(canvas: Canvas, guidelineX: Float) {
        canvas.drawLine(
            guidelineX, topY,
            guidelineX, bottomY,
            guidelinePaint
        )
    }
}