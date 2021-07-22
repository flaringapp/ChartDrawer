package com.flaringapp.graphdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flaringapp.graphdrawer.graph.line.LineGraphPlot
import com.flaringapp.graphdrawer.graph.line.LineGraphPlotSet
import com.flaringapp.graphdrawer.graph.line.LineGraphView

class MainActivity : AppCompatActivity() {

    private val mockedDataset = listOf(
        LineGraphPlot(1f to 4f),
        LineGraphPlot(2f to 6f),
        LineGraphPlot(3f to 5f),
        LineGraphPlot(4f to 2f),
        LineGraphPlot(5f to 4f),
        LineGraphPlot(6f to 5f),
        LineGraphPlot(7f to 6f),
        LineGraphPlot(8f to 6f),
        LineGraphPlot(9f to 5f),
        LineGraphPlot(10f to 6f),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val graphView = findViewById<LineGraphView>(R.id.viewLineGraph)
        graphView.updateDataset(
            LineGraphPlotSet.create(mockedDataset)
        )

    }

}