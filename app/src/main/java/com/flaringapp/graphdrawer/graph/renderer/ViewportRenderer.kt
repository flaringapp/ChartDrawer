package com.flaringapp.graphdrawer.graph.renderer

import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties
import com.flaringapp.graphdrawer.graph.renderer.viewport.MutableRendererViewport
import com.flaringapp.graphdrawer.graph.renderer.viewport.RendererViewport

abstract class ViewportRenderer: PropertyHoldingRenderer() {

    private val mViewport = MutableRendererViewport.EMPTY
    val viewport: RendererViewport
        get() = mViewport

    override fun updateProperties(properties: RendererProperties) {
        super.updateProperties(properties)
        updateViewport()
    }

    private fun updateViewport() {
        mViewport.updateAndCalculateSize {
            left = properties.translateX
            top = properties.translateY
            right = mViewport.left + (properties.width * properties.scale)
            bottom = mViewport.top + (properties.height * properties.scale)
        }
    }
}