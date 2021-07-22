package com.flaringapp.graphdrawer.graph.line

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.flaringapp.graphdrawer.graph.line.renderers.LineGraphRenderer
import com.flaringapp.graphdrawer.graph.renderer.ComplexRenderer
import com.flaringapp.graphdrawer.graph.renderer.GraphRenderer
import com.flaringapp.graphdrawer.graph.renderer.RendererContainer
import com.flaringapp.graphdrawer.graph.renderer.properties.MutableRendererProperties
import com.flaringapp.graphdrawer.graph.renderer.viewport.MutableRendererViewport

class LineGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), RendererContainer<GraphRenderer> {

    private val lineRenderer = LineGraphRenderer()

    private val renderers = ComplexRenderer<GraphRenderer>()

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

        notifyRenderersPropertiesChanged()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        renderers.render(canvas)
    }

    override fun addRenderer(renderer: GraphRenderer) {
        rendererSensitiveAction {
            renderers += renderer
        }
    }

    override fun removeRenderer(renderer: GraphRenderer) {
        rendererSensitiveAction {
            renderers -= renderer
        }
    }

    fun updateDataset(dataset: LineGraphPlotSet) {
        rendererSensitiveAction {
            lineRenderer.plotRenderer.setDataset(dataset)
            updateAffineToFitDataset(dataset)
        }
    }

    // Fit dataset inside a view
    private fun updateAffineToFitDataset(dataset: LineGraphPlotSet) {
        if (properties.width == 0 || properties.height == 0) return

        val currentViewport = lineRenderer.plotRenderer.viewport
        val fitViewport = MutableRendererViewport(
            dataset.minX, dataset.maxY,
            dataset.maxX, dataset.minY
        )

        var graphSize = fitViewport.top
        if (fitViewport.bottom < 0) graphSize -= fitViewport.bottom

        properties.scaleX *= currentViewport.width / fitViewport.width
        properties.scaleY *= lineRenderer.plotRenderer.drawingRect.height() / graphSize

        // Scale down a bit
        properties.scaleX *= 0.8f
        properties.scaleY *= 0.6f

        // Position in the center
        properties.translateX = -fitViewport.left * properties.scaleX
        properties.translateY = -fitViewport.top * properties.scaleY +
                lineRenderer.plotRenderer.drawingRect.height()
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