package com.flaringapp.graphdrawer.graph.renderer

import com.flaringapp.graphdrawer.graph.renderer.properties.DefaultRendererProperties
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

abstract class PropertyHoldingRenderer : GraphRenderer {

    protected open var properties: RendererProperties = DefaultRendererProperties

    override fun updateProperties(properties: RendererProperties) {
        this.properties = properties
    }

}