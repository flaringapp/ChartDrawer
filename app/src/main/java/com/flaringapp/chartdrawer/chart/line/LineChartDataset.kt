package com.flaringapp.chartdrawer.chart.line

class LineChartDataset(
    val points: List<LineChartPoint>,
    val minX: Float,
    val maxX: Float,
    val minY: Float,
    val maxY: Float,
) {

    companion object {
        val EMPTY: LineChartDataset by lazy {
            LineChartDataset(emptyList(), 0f, 0f, 0f, 0f)
        }

        fun create(points: List<LineChartPoint>): LineChartDataset {
            if (points.isEmpty()) return EMPTY
            val firstPoint = points.first()

            var minX = firstPoint.xValue
            var maxX = minX
            var minY = firstPoint.yValue
            var maxY = minY

            for (i in 1 until points.size) {
                val point = points[i]
                when {
                    point.xValue > maxX -> maxX = point.xValue
                    point.xValue < minX -> minX = point.xValue
                }

                when {
                    point.yValue > maxY -> maxY = point.yValue
                    point.yValue < minY -> minY = point.yValue
                }
            }

            return LineChartDataset(
                points,
                minX, maxX,
                minY, maxY
            )
        }
    }

}