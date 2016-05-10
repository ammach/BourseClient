package com.example.ammach.bourse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helpers.ServeurDetailAction;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PreviewLineChartView;

public class DetailActionActivity extends AppCompatActivity  {

    private LineChartView chart;
    private PreviewLineChartView previewChart;
    private LineChartData data;
    private LineChartData previewData;

    public static Handler handler;
    List<Line> lines;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_action);

        String nameAction=getIntent().getStringExtra("nameAction");
        String valAction=getIntent().getStringExtra("valAction");
        position=getIntent().getIntExtra("position",0);

        getSupportActionBar().setTitle("ACTION : "+nameAction);

        Toast.makeText(DetailActionActivity.this,nameAction+" "+position, Toast.LENGTH_SHORT).show();

        chart = (LineChartView) findViewById(R.id.chart);
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);

        // Generate data for previewed chart and copy of that data for preview chart.
       // generateDefaultData();
        final List<PointValue> values = new ArrayList<PointValue>();
        lines = new ArrayList<Line>();
        data = new LineChartData(lines);
        data.setAxisXBottom(new Axis().setName("temps(1000ms)"));
        data.setAxisYLeft(new Axis().setHasLines(true).setName("valeur(%)"));

        // prepare preview data, is better to use separate deep copy for preview chart.
        // Set color to grey to make preview area more visible.
        previewData = new LineChartData(data);
//        previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
        //
        chart.setLineChartData(data);
        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        chart.setZoomEnabled(false);
        chart.setScrollEnabled(false);

        previewChart.setLineChartData(previewData);
        previewChart.setViewportChangeListener(new ViewportListener());

        previewX(false);

        new ServeurDetailAction();

        handler=new Handler(){
            int i=0;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ArrayList<String> valeurs= (ArrayList<String>) msg.obj;
                if (valeurs.get(0).equals("action")){
                    String valeur=valeurs.get(position+1);
                    Toast.makeText(DetailActionActivity.this, ""+valeur, Toast.LENGTH_SHORT).show();
                    values.add(new PointValue(msg.arg1,Float.parseFloat(valeur)));
                    Line line = new Line(values);
                    line.setColor(ChartUtils.COLOR_GREEN);
                    line.setHasPoints(false);// too many values so don't draw points.
                    lines.add(line);
                    data.setLines(lines);
                    chart.setLineChartData(data);
                    previewData = new LineChartData(data);
                    previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
                    previewChart.setLineChartData(previewData);
                }
            }
        };
    }





    private void previewX(boolean animate) {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dx = tempViewport.width() / 4;
        tempViewport.inset(dx, 6);
        if (animate) {
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        } else {
            previewChart.setCurrentViewport(tempViewport);
        }
        previewChart.setZoomType(ZoomType.HORIZONTAL);
    }
    private void previewY() {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dy = tempViewport.height() / 4;
        tempViewport.inset(0, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
        previewChart.setZoomType(ZoomType.VERTICAL);
    }
    private void previewXY() {
        // Better to not modify viewport of any chart directly so create a copy.
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        // Make temp viewport smaller.
        float dx = tempViewport.width() / 4;
        float dy = tempViewport.height() / 4;
        tempViewport.inset(dx, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
    }


    private class ViewportListener implements ViewportChangeListener {

        @Override
        public void onViewportChanged(Viewport newViewport) {
            // don't use animation, it is unnecessary when using preview chart.
            chart.setCurrentViewport(newViewport);
        }

    }


}
