package com.flaringapp.chartdrawer.chart.line

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.flaringapp.chartdrawer.chart.line.renderers.LineChartRenderer
import com.flaringapp.chartdrawer.chart.renderer.ComplexRenderer
import com.flaringapp.chartdrawer.chart.renderer.ChartRenderer
import com.flaringapp.chartdrawer.chart.renderer.RendererContainer
import com.flaringapp.chartdrawer.chart.renderer.properties.MutableRendererProperties
import com.flaringapp.chartdrawer.chart.renderer.viewport.MutableRendererViewport

class LineChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), RendererContainer<ChartRenderer> {

    private val lineRenderer = LineChartRenderer()

    private val renderers = ComplexRenderer<ChartRenderer>()

    private val properties = MutableRendererProperties()

    init {
        setWillNotDraw(false)

        renderers += lineRenderer

        notifyRenderersPropertiesChanged()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (w == oldw && h == oldh) return

        properties.width = width
        properties.height = height

        notifyRenderersPropertiesChanged()

        updateAffineToFitDataset(lineRenderer.plotRenderer.dataset)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        renderers.render(canvas)
    }

    override fun addRenderer(renderer: ChartRenderer) {
        rendererSensitiveAction {
            renderers += renderer
        }
    }

    override fun removeRenderer(renderer: ChartRenderer) {
        rendererSensitiveAction {
            renderers -= renderer
        }
    }

    fun updateDataset(dataset: LineChartDataset) {
        rendererSensitiveAction {
            lineRenderer.plotRenderer.setDataset(dataset)
            updateAffineToFitDataset(dataset)
        }
    }

    // Fit dataset inside a view
    private fun updateAffineToFitDataset(dataset: LineChartDataset) {
        if (properties.width == 0 || properties.height == 0) return

        val drawingRect = lineRenderer.plotRenderer.drawingRect
        val fitViewport = MutableRendererViewport(
            dataset.minX, dataset.maxY,
            dataset.maxX, dataset.minY
        )

        var chartHeight = fitViewport.top
        if (fitViewport.bottom < 0) chartHeight -= fitViewport.bottom

        properties.scaleX = drawingRect.width() / fitViewport.width
        properties.scaleY = drawingRect.height() / chartHeight

        // Scale down a bit
        properties.scaleX *= 0.8f
        properties.scaleY *= 0.6f

        // Position in the center
        properties.translateX = -fitViewport.left * properties.scaleX
        properties.translateY = -fitViewport.top * properties.scaleY +
                lineRenderer.plotRenderer.drawingRect.height()

        notifyRenderersPropertiesChanged()
    }

    private fun rendererSensitiveAction(action: () -> Unit) {
        action()
        invalidate()
    }

    private fun notifyRenderersPropertiesChanged() {
        renderers.updateProperties(
            properties.asImmutable()
        )
    }

}