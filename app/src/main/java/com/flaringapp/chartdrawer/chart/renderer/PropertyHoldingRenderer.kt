package com.flaringapp.chartdrawer.chart.renderer

import com.flaringapp.chartdrawer.chart.renderer.properties.DefaultRendererProperties
import com.flaringapp.chartdrawer.chart.renderer.properties.RendererProperties

abstract class PropertyHoldingRenderer : ChartRenderer {

    protected open var properties: RendererProperties = DefaultRendererProperties

    override fun updateProperties(properties: RendererProperties) {
        this.properties = properties
    }

}