package com.flaringapp.graphdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.flaringapp.graphdrawer.graph.line.LineGraphPlot
import com.flaringapp.graphdrawer.graph.line.LineGraphPlotSet
import com.flaringapp.graphdrawer.graph.line.LineGraphView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val graphView = findViewById<LineGraphView>(R.id.viewLineGraph)
        val buttonAppendNewPoint = findViewById<Button>(R.id.buttonAppendNewPoint)
        val buttonRefreshDataset = findViewById<Button>(R.id.buttonRefreshDataset)

        var dataset: LineGraphPlotSet = randomDataset()

        graphView.updateDataset(dataset)

        buttonAppendNewPoint.setOnClickListener {
            dataset = appendRandomPointToDataset(dataset)
            graphView.updateDataset(dataset)
        }
        buttonRefreshDataset.setOnClickListener {
            dataset = randomDataset()
            graphView.updateDataset(dataset)
        }
    }

    private fun appendRandomPointToDataset(dataset: LineGraphPlotSet): LineGraphPlotSet {
        val points = dataset.plots.toMutableList()
        points.add(randomPoint(points.size.toFloat()))
        return LineGraphPlotSet.create(points)
    }

    private fun randomDataset(): LineGraphPlotSet {
        val size = Random.nextInt(20, 40)
        val plots = List(size) { index ->
            randomPoint(index.toFloat())
        }
        return LineGraphPlotSet.create(plots)
    }

    private fun randomPoint(x: Float): LineGraphPlot {
        return LineGraphPlot(x to Random.nextInt(5, 40).toFloat())
    }

}