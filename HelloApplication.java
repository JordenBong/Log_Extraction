package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.chart.LineChart;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.chart.LineChart;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        //Defining the x axis
        NumberAxis xAxis = new NumberAxis(5,13,1);
        xAxis.setLabel("Months");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        yAxis.setLabel("Frequency");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("Number of Jobs Exceeding Time Limit");

        series.getData().add(new XYChart.Data(6, 98));
        series.getData().add(new XYChart.Data(7, 56));
        series.getData().add(new XYChart.Data(8, 40));
        series.getData().add(new XYChart.Data(9, 66));
        series.getData().add(new XYChart.Data(10, 49));
        series.getData().add(new XYChart.Data(11, 74));
        series.getData().add(new XYChart.Data(12, 54));


        //Setting the data to Line chart
        linechart.getData().add(series);

        //Creating a Group object
        Group root = new Group(linechart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Line Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }

}