package com.example.habit_tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.habit_tracker.Logic.StatLogic;
import com.example.habit_tracker.Network.ServerRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class StatisticActivity extends AppCompatActivity implements IView{

    BarChart barChart;
    String[] days;
    ArrayList<BarEntry> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setTitle("Satistic");

        days = new String [7];
        data = new ArrayList<BarEntry>();

        barChart = (BarChart) findViewById(R.id.barChart);

        ServerRequest serverRequest = new ServerRequest();
        StatLogic logic = new StatLogic(this, serverRequest);
        logic.getStat(LoginActivity.username);


    }

    /**
     * Jump to to-do view
     * @param view
     */
    public void toToDo(View view){
        Intent in = new Intent(StatisticActivity.this,homeActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Habit view
     * @param view
     * Given view
     */
    public void toHabit(View view){
        Intent in = new Intent(StatisticActivity.this,HabitActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Skill view
     * @param view
     * Given view
     */
    public void toSkill(View view){
        Intent in = new Intent(StatisticActivity.this,SkillActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Project View
     * @param view
     * Given  view
     */
    public void toProject(View view){
        Intent in = new Intent(StatisticActivity.this,ProjectActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Setting view
     * @param view
     * Given view
     */
    public void toSetting(View view){
        Intent in = new Intent(StatisticActivity.this,SettingActivity.class);
        startActivity(in);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void showText(int n,String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<Integer,String> mapIn = new HashMap<Integer, String>();
        HashMap<Integer,Integer> cont = new HashMap<Integer, Integer>();
        String [] st_list = s.split(",");
        for(int i = 1; i < st_list.length-1;i++) {
            String[] parts = st_list[i].split(":");
            String con = parts[1].substring(1,parts[1].length()-1);
            int num;
            if(con.equals("ul")){
                num = 0;
            }
            else {
                num = Integer.parseInt(con);
            }
            String date = parts[0].substring(2,parts[0].length()-1);
            map.put(date, i);
            mapIn.put(i,date);
            cont.put(i, num);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDateTime now = LocalDateTime.now();
        String timenow = dtf.format(now);

        int index = map.get(timenow) - 6;

        for(int i = index; i < index + 7;i++){
            String theDay = mapIn.get(i);
            days[i-index]= theDay.substring(0,2)+"/"+theDay.substring(2,4);
            BarEntry entry = new BarEntry(i-index, cont.get(i));
            data.add(entry);
        }
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        XAxis xaxis = barChart.getXAxis();
        xaxis.setDrawGridLines(false);
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularity(1f);
        xaxis.setDrawLabels(true);
        xaxis.setDrawAxisLine(false);
        xaxis.setValueFormatter(new IndexAxisValueFormatter(days));

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setEnabled(false);

        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        List<IBarDataSet> dataSets = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(data, "Contribution");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        dataSets.add(barDataSet);

        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    @Override
    public void toastText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchActivity(int n) {

    }
}