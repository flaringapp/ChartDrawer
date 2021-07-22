package com.flaringapp.graphdrawer.graph.renderer

import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties
import com.flaringapp.graphdrawer.graph.renderer.viewport.MutableRendererViewport
import com.flaringapp.graphdrawer.graph.renderer.viewport.RendererViewport

abstract class ViewportRenderer : PropertyHoldingRenderer() {

    private val mViewport = MutableRendererViewport.EMPTY
    val viewport: RendererViewport
        get() = mViewport

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)
        updateViewport(mViewport)
    }

    protected open fun updateViewport(viewport: MutableRendererViewport) {
        viewport.updateAndCalculateSize {
            left = properties.translateX
            top = properties.translateY
            right = mViewport.left + (properties.width / properties.scaleX)
            bottom = mViewport.top + (properties.height / properties.scaleY)
        }
    }

}