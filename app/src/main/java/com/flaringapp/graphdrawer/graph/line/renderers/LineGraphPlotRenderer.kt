package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.flaringapp.graphdrawer.graph.line.LineGraphPlot
import com.flaringapp.graphdrawer.graph.line.LineGraphPlotSet
import com.flaringapp.graphdrawer.graph.renderer.ViewportRenderer
import com.flaringapp.graphdrawer.graph.renderer.common.RendererPadding
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties
import com.flaringapp.graphdrawer.graph.renderer.viewport.MutableRendererViewport

class LineGraphPlotRenderer(
    private val pointPaint: Paint,
    private val linePaint: Paint,
    private val pointRadius: Float,
    private val padding: RendererPadding,
) : ViewportRenderer() {

    var dataset: LineGraphPlotSet = LineGraphPlotSet.EMPTY
        private set

    var drawingRect: RectF = RectF()
        private set

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        drawingRect = padding.asRect(properties.width, properties.height)
    }

    // TODO optimize rendering with validation if plot is inside viewport
    override fun render(canvas: Canvas) {
        canvas.save()

        canvas.clipRect(drawingRect)

        canvas.translate(properties.translateX, properties.translateY)
        canvas.translate(padding.left, padding.top)

        // Invert y axis because canvas is inverted relative to plot
        canvas.scale(
            1f, -1f,
            0f, viewportY((dataset.maxY - dataset.minY) / 2f)
        )

        renderPlots(canvas)

        canvas.restore()
    }

    override fun updateViewport(viewport: MutableRendererViewport) {
        val viewportWidth = (properties.width - padding.left - padding.right) / properties.scaleX
        val viewportHeight = (properties.height - padding.top - padding.bottom) / properties.scaleY
        viewport.updateAndCalculateSize {
            left = properties.translateX / properties.scaleX
            top = properties.translateY / properties.scaleY
            right = viewport.left + viewportWidth
            bottom = viewport.top + viewportHeight
        }
    }

    fun setDataset(dataset: LineGraphPlotSet) {
        this.dataset = dataset
    }

    private fun renderPlots(canvas: Canvas) {
        if (dataset.plots.isEmpty()) return

        val firstPoint = dataset.plots.first()
        renderPlotPoint(canvas, firstPoint)

        if (dataset.plots.size == 1) return

        val path = Path()
        path.moveTo(
            viewportX(firstPoint.xValue),
            viewportY(firstPoint.yValue),
        )

        for (i in 1 until dataset.plots.size) {
            val point = dataset.plots[i]
            renderPlotPoint(canvas, point)
            path.lineTo(
                viewportX(point.xValue),
                viewportY(point.yValue)
            )
        }

        renderPlotLine(canvas, path)
    }

    private fun renderPlotPoint(canvas: Canvas, plot: LineGraphPlot) {
        canvas.drawCircle(
            viewportX(plot.xValue),
            viewportY(plot.yValue),
            pointRadius,
            pointPaint
        )
    }

    private fun renderPlotLine(
        canvas: Canvas,
        path: Path
    ) {
        canvas.drawPath(path, linePaint)
    }

    private fun viewportX(x: Float): Float {
        return x * drawingRect.width() / viewport.width
    }

    private fun viewportY(y: Float): Float {
        return y * drawingRect.height() / viewport.height
    }

}