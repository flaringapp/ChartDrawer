package com.flaringapp.chartdrawer.chart.line

class LineChartPoint(
    val xValue: Float,
    val yValue: Float
) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)

}