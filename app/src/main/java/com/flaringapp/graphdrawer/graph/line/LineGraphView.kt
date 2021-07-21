package com.flaringapp.graphdrawer.graph.line

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.flaringapp.graphdrawer.graph.renderer.ComplexRenderer
import com.flaringapp.graphdrawer.graph.renderer.GraphRenderer
import com.flaringapp.graphdrawer.graph.renderer.RendererContainer

class LineGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), RendererContainer<GraphRenderer> {

    private val renderers = ComplexRenderer<GraphRenderer>()

    init {
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        renderers.render(canvas)
    }

    override fun addRenderer(renderer: GraphRenderer) {
        rendererSensitiveAction {
            renderers += renderer
        }
    }

    override fun removeRenderer(renderer: GraphRenderer) {
        rendererSensitiveAction {
            renderers -= renderer
        }
    }

    private fun rendererSensitiveAction(action: () -> Unit) {
        action()
        invalidate()
    }
}