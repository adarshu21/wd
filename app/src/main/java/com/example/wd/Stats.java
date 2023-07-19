package com.example.wd;

import static java.lang.Boolean.TRUE;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Stats extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference t1 = database.getReference("v1");
    DatabaseReference t2 = database.getReference("v2");
    DatabaseReference t3 = database.getReference("v3");
    DatabaseReference t4 = database.getReference("v4");
    DatabaseReference t5 = database.getReference("v5");
    DatabaseReference t6 = database.getReference("v6");
    int va[]=new int[8];
    int va2[]=new int[8];
    int va3[]=new int[8];
    int v4,v5,v6;
    GraphView graphView;
    GraphView graphView2;
    GraphView graphView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().hide();
        t1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {



            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    String value = String.valueOf(task.getResult().getValue());
                    for(int i=0,c=0;i<=22;i=i+3)
                    {
                        va[c]= Integer.parseInt(value.substring(i,i+2));
                        c++;
                    }
                    t2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                        //Nested the retrieve code blocks
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            } else {
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                String value = String.valueOf(task.getResult().getValue());
                                for(int i=0,c=0;i<=22;i=i+3)
                                {
                                    va2[c]= Integer.parseInt(value.substring(i,i+2));
                                    c++;
                                }
                                t3.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                                    //Nested the retrieve code blocks
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (!task.isSuccessful()) {
                                            Log.e("firebase", "Error getting data", task.getException());
                                        } else {
                                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                            String value = String.valueOf(task.getResult().getValue());
                                            for(int i=0,c=0;i<=22;i=i+3)
                                            {
                                                va3[c]= Integer.parseInt(value.substring(i,i+2));
                                                c++;
                                            }
                                            t4.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                                                //Nested the retrieve code blocks
                                                @Override
                                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                    if (!task.isSuccessful()) {
                                                        Log.e("firebase", "Error getting data", task.getException());
                                                    } else {
                                                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                                        String value = String.valueOf(task.getResult().getValue());
                                                        v4=Integer.parseInt(value);
                                                        t5.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                                                            //Nested the retrieve code blocks
                                                            @Override
                                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                                if (!task.isSuccessful()) {
                                                                    Log.e("firebase", "Error getting data", task.getException());
                                                                } else {
                                                                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                                                    String value = String.valueOf(task.getResult().getValue());
                                                                    v5=Integer.parseInt(value);
                                                                    t6.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


                                                                        //Nested the retrieve code blocks
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                                            if (!task.isSuccessful()) {
                                                                                Log.e("firebase", "Error getting data", task.getException());
                                                                            } else {
                                                                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                                                                String value = String.valueOf(task.getResult().getValue());
                                                                                v6=Integer.parseInt(value);
                                                                                getv();

                                                                            }
                                                                        }

                                                                    });

                                                                }
                                                            }

                                                        });

                                                    }
                                                }

                                            });

                                        }
                                    }

                                });


                            }
                        }

                    });

                }
            }

        });


    }
    void getv() {
        graphView = findViewById(R.id.graph1);
        graphView2 = findViewById(R.id.graph2);
        graphView3 = findViewById(R.id.graph3);
        TextView tv = findViewById(R.id.vol);
        TextView tv2 = findViewById(R.id.vol2);
        TextView tv3 = findViewById(R.id.cur);
        String s=v4+"";
        String s2=v5+"";
        String s3=v6+"";
        tv.setText(s);
        tv2.setText(s2);
        tv3.setText(s3);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2008, va[0]),
                new DataPoint(2009, va[1]),
                new DataPoint(2010, va[2]),
                new DataPoint(2011, va[3]),
                new DataPoint(2012, va[4]),
                new DataPoint(2013, va[5]),
                new DataPoint(2014, va[6]),
                new DataPoint(2015, va[7])
        });
        GridLabelRenderer glr = graphView.getGridLabelRenderer();
        glr.setPadding(125);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graphView.getGridLabelRenderer().setHighlightZeroLines(TRUE);
        graphView.getViewport().setMinX(2008);
        graphView.getViewport().setMaxX(2015);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);
        graphView.getGridLabelRenderer().setTextSize(25);
        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(30);
        graphView.getGridLabelRenderer().setHorizontalAxisTitleTextSize(60);
        graphView.getGridLabelRenderer().setVerticalAxisTitleTextSize(60);
        graphView.getGridLabelRenderer().setLabelHorizontalHeight(40);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(8);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.setTitle("Real Time Voltage");
        graphView.setTitleColor(R.color.black);
        graphView.setTitleTextSize(60);
        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        series.setAnimated(true);
        series.setDrawBackground(true);
        series.setCustomPaint(paint);
        series.setAnimated(true);
        graphView.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2008, va2[0]),
                new DataPoint(2009, va2[1]),
                new DataPoint(2010, va2[2]),
                new DataPoint(2011, va2[3]),
                new DataPoint(2012, va2[4]),
                new DataPoint(2013, va2[5]),
                new DataPoint(2014, va2[6]),
                new DataPoint(2015, va2[7])
        });
        GridLabelRenderer glr2 = graphView2.getGridLabelRenderer();
        glr2.setPadding(125);
        graphView2.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graphView2.getGridLabelRenderer().setHighlightZeroLines(TRUE);
        graphView2.getViewport().setMinX(2008);
        graphView2.getViewport().setMaxX(2015);
        graphView2.getViewport().setMinY(0);
        graphView2.getViewport().setMaxY(100);
        graphView2.getGridLabelRenderer().setTextSize(25);
        graphView2.getGridLabelRenderer().setHorizontalLabelsAngle(30);
        graphView2.getGridLabelRenderer().setHorizontalAxisTitleTextSize(60);
        graphView2.getGridLabelRenderer().setVerticalAxisTitleTextSize(60);
        graphView2.getGridLabelRenderer().setLabelHorizontalHeight(40);
        graphView2.getGridLabelRenderer().setNumHorizontalLabels(8);
        graphView2.getViewport().setYAxisBoundsManual(true);
        graphView2.getViewport().setXAxisBoundsManual(true);
        graphView2.setTitle("Cell Voltage");
        graphView2.setTitleColor(R.color.black);
        graphView2.setTitleTextSize(60);
        graphView2.getViewport().setScrollable(true);
        graphView2.getViewport().setScrollableY(true);
        graphView2.getViewport().setScalable(true);
        graphView2.getViewport().setScalableY(true);
        GridLabelRenderer gridLabel2 = graphView2.getGridLabelRenderer();
        series2.setCustomPaint(paint);
        series2.setAnimated(true);
        series2.setDrawBackground(true);
        series2.setAnimated(true);
        graphView2.addSeries(series2);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2008, va3[0]),
                new DataPoint(2009, va3[1]),
                new DataPoint(2010, va3[2]),
                new DataPoint(2011, va3[3]),
                new DataPoint(2012, va3[4]),
                new DataPoint(2013, va3[5]),
                new DataPoint(2014, va3[6]),
                new DataPoint(2015, va3[7])
        });
        GridLabelRenderer glr3 = graphView3.getGridLabelRenderer();
        glr3.setPadding(125);
        graphView3.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        graphView3.getGridLabelRenderer().setHighlightZeroLines(TRUE);
        graphView3.getViewport().setMinX(2008);
        graphView3.getViewport().setMaxX(2015);
        graphView3.getViewport().setMinY(0);
        graphView3.getViewport().setMaxY(100);
        graphView3.getGridLabelRenderer().setTextSize(25);
        graphView3.getGridLabelRenderer().setHorizontalLabelsAngle(30);
        graphView3.getGridLabelRenderer().setHorizontalAxisTitleTextSize(60);
        graphView3.getGridLabelRenderer().setVerticalAxisTitleTextSize(60);
        graphView3.getGridLabelRenderer().setLabelHorizontalHeight(40);
        graphView3.getGridLabelRenderer().setNumHorizontalLabels(8);
        graphView3.getViewport().setYAxisBoundsManual(true);
        graphView3.getViewport().setXAxisBoundsManual(true);
        graphView3.setTitle("Current");
        graphView3.setTitleColor(R.color.black);
        graphView3.setTitleTextSize(60);
        graphView3.getViewport().setScrollable(true);
        graphView3.getViewport().setScrollableY(true);
        graphView3.getViewport().setScalable(true);
        graphView3.getViewport().setScalableY(true);
        GridLabelRenderer gridLabel3 = graphView3.getGridLabelRenderer();
        series3.setAnimated(true);
        series3.setDrawBackground(true);
        series3.setCustomPaint(paint);
        series3.setAnimated(true);
        graphView3.addSeries(series3);
        series3.setDrawDataPoints(true);
        series3.setDataPointsRadius(10);
    }
}
