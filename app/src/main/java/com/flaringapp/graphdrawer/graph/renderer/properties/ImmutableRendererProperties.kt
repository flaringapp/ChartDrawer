package com.flaringapp.graphdrawer.graph.renderer.properties

class ImmutableRendererProperties(
    override val width: Int = 0,
    override val height: Int = 0,
    override val scale: Float = 1f,
    override val translateX: Float = 0f,
    override val translateY: Float = 0f,
): RendererProperties {

    constructor(properties: RendererProperties) : this(
        properties.width,
        properties.height,
        properties.scale,
        properties.translateX,
        properties.translateY,
    )

}