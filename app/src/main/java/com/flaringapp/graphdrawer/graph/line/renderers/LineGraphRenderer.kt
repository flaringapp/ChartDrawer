package com.flaringapp.graphdrawer.graph.line.renderers

import android.graphics.Color
import android.graphics.Paint
import com.flaringapp.graphdrawer.graph.renderer.ComplexRenderer
import com.flaringapp.graphdrawer.graph.renderer.GraphRenderer
import com.flaringapp.graphdrawer.graph.renderer.common.RendererPadding

// TODO stylize outside
class LineGraphRenderer : ComplexRenderer<GraphRenderer>() {

    companion object {
        private const val PADDING_LEFT = 100f
        private const val PADDING_TOP = 60f
        private const val PADDING_RIGHT = 40f
        private const val PADDING_BOTTOM = 60f

        private val paddings =
            RendererPadding(PADDING_LEFT, PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM)
    }

    val axesRenderer = LineGraphAxesRenderer(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            color = Color.BLACK
        },
        padding = paddings
    )

    init {
        renderers += axesRenderer
    }

}