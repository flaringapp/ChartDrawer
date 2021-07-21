package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ViewportRenderer
import com.flaringapp.graphdrawer.graph.renderer.common.RendererPadding
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

class LineGraphYAxisGuidelinesRenderer(
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

        endX = properties.width - padding.right
        bottomY = properties.height - (properties.translateX % guidelineGap) - padding.bottom
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