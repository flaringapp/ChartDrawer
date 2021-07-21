package com.flaringapp.graphdrawer.graph.line

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.flaringapp.graphdrawer.graph.renderer.ComplexRenderer
import com.flaringapp.graphdrawer.graph.renderer.GraphRenderer
import com.flaringapp.graphdrawer.graph.renderer.RendererContainer
import com.flaringapp.graphdrawer.graph.renderer.properties.MutableRendererProperties

class LineGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), RendererContainer<GraphRenderer> {

    private val renderers = ComplexRenderer<GraphRenderer>()

    private val properties = MutableRendererProperties()

    init {
        setWillNotDraw(false)

        notifyRenderersPropertiesChanged()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (w == oldw && h == oldh) return

        properties.width = width
        properties.height = height

        notifyRenderersPropertiesChanged()
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

    private fun notifyRenderersPropertiesChanged() {
        renderers.updateProperties(
            properties.asImmutable()
        )
    }

}