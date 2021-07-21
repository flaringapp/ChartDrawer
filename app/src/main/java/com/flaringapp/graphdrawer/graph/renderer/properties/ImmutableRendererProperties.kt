package com.flaringapp.graphdrawer.graph.renderer.properties

class ImmutableRendererProperties(
    override val width: Int = 0,
    override val height: Int = 0,
    override val scaleX: Float = 1f,
    override val scaleY: Float = 1f,
    override val translateX: Float = 0f,
    override val translateY: Float = 0f,
): RendererProperties {

    constructor(properties: RendererProperties) : this(
        properties.width,
        properties.height,
        properties.scaleX,
        properties.scaleY,
        properties.translateX,
        properties.translateY,
    )

}