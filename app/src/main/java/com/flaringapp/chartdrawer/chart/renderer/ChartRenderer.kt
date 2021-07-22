package com.flaringapp.chartdrawer.chart.renderer

import android.graphics.Canvas
import com.flaringapp.chartdrawer.chart.renderer.properties.RendererProperties

interface ChartRenderer {

    fun updateProperties(properties: RendererProperties)

    fun render(canvas: Canvas)

}