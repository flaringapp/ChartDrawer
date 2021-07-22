package com.flaringapp.chartdrawer.chart.renderer.viewport

class MutableRendererViewport(
    override var left: Float,
    override var top: Float,
    override var right: Float,
    override var bottom: Float,
    override var width: Float,
    override var height: Float,
) : RendererViewport {

    constructor(left: Float, top: Float, right: Float, bottom: Float): this(
        left, top, right, bottom, right - left, bottom - top
    )

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