package com.flaringapp.chartdrawer.chart.renderer

import android.graphics.Canvas
import com.flaringapp.chartdrawer.chart.renderer.properties.RendererProperties

open class ComplexRenderer<T : ChartRenderer>(
    initialRenderers: List<T> = emptyList()
) : PropertyHoldingRenderer(), RendererContainer<T> {

    constructor(vararg initialRenderers: T) : this(initialRenderers.toList())

    protected val renderers: MutableList<T> = initialRenderers.toMutableList()

    override fun addRenderer(renderer: T) {
        renderer.updateProperties(properties)
        renderers += renderer
    }

    override fun removeRenderer(renderer: T) {
        renderers -= renderer
    }

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)
        renderers.forEach { it.updateProperties(properties) }
    }

    override fun render(canvas: Canvas) {
        renderers.forEach { renderer ->
            renderer.render(canvas)
        }
    }

}