package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ViewportRenderer
import com.flaringapp.graphdrawer.graph.renderer.common.RendererPadding
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

class LineGraphAxesRenderer(
    private val axisPaint: Paint,
    private val padding: RendererPadding,
) : ViewportRenderer() {

    private var cx: Float = 0f
    private var cy: Float = 0f
    private var xAxisEndX = 0f
    private var yAxisStartY = padding.top

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        cx = padding.left
        cy = properties.height - padding.bottom
        xAxisEndX = properties.width - padding.right
    }

    override fun render(canvas: Canvas) {
        /**
         * TODO
         * optimize using a path of 2 lines (3 points). We can ignore axis which does not fit
         * on the viewport
         */
        drawXAxis(canvas)
        drawYAxis(canvas)
    }

    private fun drawXAxis(canvas: Canvas) {
        canvas.drawLine(
            cx, cy,
            xAxisEndX, cy,
            axisPaint
        )
    }

    private fun drawYAxis(canvas: Canvas) {
        canvas.drawLine(
            cx, cy,
            cx, yAxisStartY,
            axisPaint
        )
    }

}