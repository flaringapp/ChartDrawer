package com.flaringapp.graphdrawer.graph.renderer

import android.graphics.Canvas
import com.flaringapp.graphdrawer.graph.renderer.properties.RendererProperties

interface GraphRenderer {

    fun updateProperties(properties: RendererProperties)

    fun render(canvas: Canvas)

}