package com.flaringapp.graphdrawer.graph.renderer

interface RendererContainer<T : GraphRenderer> {

    fun addRenderer(renderer: T)
    fun removeRenderer(renderer: T)

    operator fun plusAssign(renderer: T) {
        addRenderer(renderer)
    }

    operator fun minusAssign(renderer: T) {
        addRenderer(renderer)
    }

}