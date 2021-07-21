package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ViewportRenderer
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

class LineGraphAxesRenderer(
    private val axisPaint: Paint,
    private val leftPadding: Float = 0f,
    private val bottomPadding: Float = 0f,
) : ViewportRenderer() {

    private var cx: Float = 0f
    private var cy: Float = 0f

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        cx = properties.translateX + leftPadding
        cy = properties.translateY + (properties.height - bottomPadding)
    }

    override fun render(canvas: Canvas) {
        canvas.save()
        canvas.translate(leftPadding, -bottomPadding)

        /**
         * TODO
         * optimize using a path of 2 lines (3 points). We can ignore axis which does not fit
         * on the viewport
         */
        drawXAxis(canvas)
        drawYAxis(canvas)

        canvas.restore()
    }

    private fun drawXAxis(canvas: Canvas) {
        canvas.drawLine(
            cx, cy,
            cx, 0f,
            axisPaint
        )
    }

    private fun drawYAxis(canvas: Canvas) {
        canvas.drawLine(
            cx, cy,
            properties.width.toFloat(), cy,
            axisPaint
        )
    }

}