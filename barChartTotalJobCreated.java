package com.example.assignmentfop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class barChartTotalJobCreated extends Application {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);
    final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Total Number of Job Created every month from June to December");
        sbc.setTitle("Total Number of Job Created every month from June to December");
        xAxis.setLabel("Month");
        series1.setName("Job Scheduled Allocate");
        series2.setName("Job Scheduled BackFill");
        JobCreated a=new JobCreated();
        a.countJobCreated();
        JobBackFill b=new JobBackFill();
        b.countBackFill();
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(a.month)));
        yAxis.setLabel("Total Number of Job Created");
        for(int i=0;i<a.month.length;i++)
        series1.getData().add(new XYChart.Data<String, Number>(a.month[i], a.countJobAllocate[i]));

       for(int i=0;i<a.month.length;i++)
        series2.getData().add(new XYChart.Data<String, Number>(b.month[i], b.countJobBackFill[i]));

        Scene scene = new Scene(sbc, 800, 800);
        sbc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
    }
}