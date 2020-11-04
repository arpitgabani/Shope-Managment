package com.example.shopapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart2);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> custmor = new ArrayList<>();

        custmor.add(new BarEntry(1,0));
        custmor.add(new BarEntry(2,0));
        custmor.add(new BarEntry(3,0));
        custmor.add(new BarEntry(4,0));
        custmor.add(new BarEntry(5,0));
        custmor.add(new BarEntry(6,0));
        custmor.add(new BarEntry(7,0));
        custmor.add(new BarEntry(8,0));
        custmor.add(new BarEntry(9,0));
        custmor.add(new BarEntry(10,1829));
        custmor.add(new BarEntry(11,1057));
        custmor.add(new BarEntry(12,0));

        BarDataSet barDataSet = new BarDataSet(custmor,"Incomes");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Shope Application");
        barChart.animateY(2000);

    }
}




