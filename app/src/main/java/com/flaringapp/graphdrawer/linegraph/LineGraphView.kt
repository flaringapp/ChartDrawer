package com.flaringapp.graphdrawer.linegraph

import android.content.Context
import android.util.AttributeSet
import android.view.View

class LineGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setWillNotDraw(false)
    }

}