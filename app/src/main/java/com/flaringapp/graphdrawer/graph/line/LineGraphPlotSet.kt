package com.flaringapp.graphdrawer.graph.line

class LineGraphPlotSet(
    val plots: List<LineGraphPlot>,
    val minX: Float,
    val maxX: Float,
    val minY: Float,
    val maxY: Float,
) {

    companion object {
        val EMPTY: LineGraphPlotSet by lazy {
            LineGraphPlotSet(emptyList(), 0f, 0f, 0f, 0f)
        }

        fun create(plots: List<LineGraphPlot>): LineGraphPlotSet {
            if (plots.isEmpty()) return EMPTY
            val firstPoint = plots.first()

            var minX = firstPoint.xValue
            var maxX = minX
            var minY = firstPoint.yValue
            var maxY = minY

            for (i in 1 until plots.size) {
                val point = plots[i]
                when {
                    point.xValue > maxX -> maxX = point.xValue
                    point.xValue < minX -> minX = point.xValue
                }

                when {
                    point.yValue > maxY -> maxY = point.yValue
                    point.yValue < minY -> minY = point.yValue
                }
            }

            return LineGraphPlotSet(
                plots,
                minX, maxX,
                minY, maxY
            )
        }
    }

}