package com.flaringapp.graphdrawer.graph.renderer

import android.graphics.Canvas
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

open class ComplexRenderer<T: GraphRenderer>(
    initialRenderers: List<T> = emptyList()
): PropertyHoldingRenderer(), RendererContainer<T> {

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