package com.flaringapp.graphdrawer.graph.renderer.viewport

class MutableRendererViewport(
    override var left: Float,
    override var top: Float,
    override var right: Float,
    override var bottom: Float,
    override var width: Float,
    override var height: Float,
) : RendererViewport {

    fun updateAndCalculateSize(updateAction: MutableRendererViewport.() -> Unit) {
        updateAction(this)

        width = right - left
        height = bottom - top
    }

    companion object {
        val EMPTY: MutableRendererViewport by lazy {
            MutableRendererViewport(0f, 0f, 0f, 0f, 0f, 0f)
        }
    }

}