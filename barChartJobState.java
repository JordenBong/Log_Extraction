package com.example.assignmentfop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class barChartJobState extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Job State");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Number of Job State from June to December");
        xAxis.setLabel("Job State");
        yAxis.setLabel("Number of Job State");
        JobStatus a = new JobStatus();
        a.JobState();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2022");
        for(int i=0;i<a.num.length;i++) {
            series1.getData().add(new XYChart.Data(a.jobState[i], a.num[i]));
        }

        Scene scene = new Scene(bc, 800, 800);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
}
