package com.flaringapp.chartdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.flaringapp.chartdrawer.chart.line.LineChartPoint
import com.flaringapp.chartdrawer.chart.line.LineChartDataset
import com.flaringapp.chartdrawer.chart.line.LineChartView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chartView = findViewById<LineChartView>(R.id.viewLineChart)
        val buttonAppendNewPoint = findViewById<Button>(R.id.buttonAppendNewPoint)
        val buttonRefreshDataset = findViewById<Button>(R.id.buttonRefreshDataset)

        var dataset: LineChartDataset = randomDataset()

        chartView.updateDataset(dataset)

        buttonAppendNewPoint.setOnClickListener {
            dataset = appendRandomPointToDataset(dataset)
            chartView.updateDataset(dataset)
        }
        buttonRefreshDataset.setOnClickListener {
            dataset = randomDataset()
            chartView.updateDataset(dataset)
        }
    }

    private fun appendRandomPointToDataset(dataset: LineChartDataset): LineChartDataset {
        val points = dataset.points.toMutableList()
        points.add(randomPoint(points.size.toFloat()))
        return LineChartDataset.create(points)
    }

    private fun randomDataset(): LineChartDataset {
        val size = Random.nextInt(20, 40)
        val points = List(size) { index ->
            randomPoint(index.toFloat())
        }
        return LineChartDataset.create(points)
    }

    private fun randomPoint(x: Float): LineChartPoint {
        return LineChartPoint(x to Random.nextInt(5, 40).toFloat())
    }

}