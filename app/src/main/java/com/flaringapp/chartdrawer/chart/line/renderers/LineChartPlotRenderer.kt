package com.flaringapp.chartdrawer.chart.line.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.flaringapp.chartdrawer.chart.line.LineChartPoint
import com.flaringapp.chartdrawer.chart.line.LineChartDataset
import com.flaringapp.chartdrawer.chart.renderer.ViewportRenderer
import com.flaringapp.chartdrawer.chart.renderer.common.RendererPadding
import com.flaringapp.chartdrawer.chart.renderer.properties.RendererProperties
import com.flaringapp.chartdrawer.chart.renderer.viewport.MutableRendererViewport

class LineChartPlotRenderer(
    private val pointPaint: Paint,
    private val linePaint: Paint,
    private val pointRadius: Float,
    private val padding: RendererPadding,
) : ViewportRenderer() {

    var dataset: LineChartDataset = LineChartDataset.EMPTY
        private set

    var drawingRect: RectF = RectF()
        private set

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)

        drawingRect = padding.asRect(properties.width, properties.height)
    }

    // TODO optimize rendering with validation if point is inside viewport
    override fun render(canvas: Canvas) {
        canvas.save()

        canvas.clipRect(drawingRect)

        canvas.translate(properties.translateX, properties.translateY)
        canvas.translate(padding.left, padding.top)

        // Invert y axis because canvas is inverted relative to chart
        canvas.scale(
            1f, -1f,
            0f, viewportY((dataset.maxY - dataset.minY) / 2f)
        )

        renderPointsAndLines(canvas)

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

    fun setDataset(dataset: LineChartDataset) {
        this.dataset = dataset
    }

    private fun renderPointsAndLines(canvas: Canvas) {
        if (dataset.points.isEmpty()) return

        val firstPoint = dataset.points.first()
        renderPoint(canvas, firstPoint)

        if (dataset.points.size == 1) return

        val path = Path()
        path.moveTo(
            viewportX(firstPoint.xValue),
            viewportY(firstPoint.yValue),
        )

        for (i in 1 until dataset.points.size) {
            val point = dataset.points[i]
            renderPoint(canvas, point)
            path.lineTo(
                viewportX(point.xValue),
                viewportY(point.yValue)
            )
        }

        renderLine(canvas, path)
    }

    private fun renderPoint(canvas: Canvas, point: LineChartPoint) {
        canvas.drawCircle(
            viewportX(point.xValue),
            viewportY(point.yValue),
            pointRadius,
            pointPaint
        )
    }

    private fun renderLine(
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