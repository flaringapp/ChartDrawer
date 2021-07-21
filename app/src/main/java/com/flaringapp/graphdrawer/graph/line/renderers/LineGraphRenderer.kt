package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Color
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ComplexRenderer
import com.flaringapp.graphdrawer.graph.renderer.GraphRenderer

// TODO stylize outside
class LineGraphRenderer: ComplexRenderer<GraphRenderer>() {

    val axesRenderer = LineGraphAxesRenderer(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            color = Color.BLACK
        },
        leftPadding = 100f,
        bottomPadding = 60f,
    )

    init {
        renderers += axesRenderer
    }

}