package com.dicoding.sahamiq.core.utils

import android.graphics.Color
import com.dicoding.sahamiq.core.domain.model.Saham
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ChartHelper(private val barChart: BarChart) {

    fun setupBarChart() {
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = true

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter()

        val leftAxis = barChart.axisLeft
        leftAxis.granularity = 1f

        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false
    }

    fun createBarData(sahamList: List<Saham>): BarData {
        val closeEntries = sahamList.mapIndexed { index, saham ->
            BarEntry(index.toFloat(), saham.close!!.toFloat())
        }

        val changeEntries = sahamList.mapIndexed { index, saham ->
            BarEntry(index.toFloat(), saham.change!!.toFloat())
        }

        val percentEntries = sahamList.mapIndexed { index, saham ->
            BarEntry(index.toFloat(), saham.percent.toString().toFloat())
        }

        val closeDataSet = createBarDataSet(closeEntries, "Close", Color.BLUE )
        val changeDataSet = createBarDataSet(changeEntries, "Change", Color.RED)
        val percentDataSet = createBarDataSet(percentEntries, "Percent", Color.GREEN)

        return BarData(closeDataSet, changeDataSet, percentDataSet)
    }

    private fun createBarDataSet(entries: List<BarEntry>, label: String, color: Int): BarDataSet {
        val dataSet = BarDataSet(entries, label)
        dataSet.color = color
        dataSet.valueTextColor = color
        return dataSet
    }
}
