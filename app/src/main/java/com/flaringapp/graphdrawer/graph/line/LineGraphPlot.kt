package com.flaringapp.graphdrawer.graph.line

class LineGraphPlot(
    val xValue: Float,
    val yValue: Float
) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)

}