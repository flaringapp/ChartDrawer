package com.flaringapp.graphdrawer.graph.renderer.properties

class MutableRendererProperties(
    override var width: Int = 0,
    override var height: Int = 0,
    override var scaleX: Float = 1f,
    override var scaleY: Float = 1f,
    override var translateX: Float = 0f,
    override var translateY: Float = 0f,
): RendererProperties {

    fun asImmutable() = ImmutableRendererProperties(this)

}