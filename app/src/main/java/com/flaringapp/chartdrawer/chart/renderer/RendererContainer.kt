package com.flaringapp.chartdrawer.chart.renderer

interface RendererContainer<T : ChartRenderer> {

    fun addRenderer(renderer: T)
    fun removeRenderer(renderer: T)

    operator fun plusAssign(renderer: T) {
        addRenderer(renderer)
    }

    operator fun minusAssign(renderer: T) {
        addRenderer(renderer)
    }

}