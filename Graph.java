import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;

public class Graph extends Application{
   
   public void start(Stage stage) {
        
        GetPartition a = new GetPartition();
        a.calculatePartition();
        
        
        String EPYC = "EPYC";
        String GPU = "GPU";
        String OPETRON = "OPTERON";
        
        int epyc6 = a.getPartition("epyc", 6);
        int gpu6 = a.getPartition("gpu", 6);
        int opetron6 = a.getPartition("opteron", 6);
        
        int epyc7 = a.getPartition("epyc", 7);
        int gpu7 = a.getPartition("gpu", 7);
        int opetron7 = a.getPartition("opteron", 7);
        
        int epyc8 = a.getPartition("epyc", 8);
        int gpu8 = a.getPartition("gpu", 8);
        int opetron8 = a.getPartition("opteron", 8);
        
        int epyc9 = a.getPartition("epyc", 9);
        int gpu9 = a.getPartition("gpu", 9);
        int opetron9 = a.getPartition("opteron", 9);
        
        int epyc10 = a.getPartition("epyc", 10);
        int gpu10 = a.getPartition("gpu", 10);
        int opetron10 = a.getPartition("opteron", 10);
        
        int epyc11 = a.getPartition("epyc", 11);
        int gpu11 = a.getPartition("gpu", 11);
        int opetron11 = a.getPartition("opteron", 11);
        
        int epyc12 = a.getPartition("epyc", 12);
        int gpu12 = a.getPartition("gpu", 12);
        int opetron12 = a.getPartition("opteron", 12);
        
        
        stage.setTitle("Bar Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Number of Partitions Based on Types and Months");
        xAxis.setLabel("Partition");       
        yAxis.setLabel("Value");
        
        
        XYChart.Series series1 = new XYChart.Series(); 
        series1.setName("June");
        series1.getData().add(new XYChart.Data(EPYC, epyc6));
        series1.getData().add(new XYChart.Data(GPU, gpu6));
        series1.getData().add(new XYChart.Data(OPETRON, opetron6));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("July");
        series2.getData().add(new XYChart.Data(EPYC, epyc7));
        series2.getData().add(new XYChart.Data(GPU, gpu7));
        series2.getData().add(new XYChart.Data(OPETRON, opetron7));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("August");
        series3.getData().add(new XYChart.Data(EPYC, epyc8));
        series3.getData().add(new XYChart.Data(GPU, gpu8));
        series3.getData().add(new XYChart.Data(OPETRON, opetron8));
        
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("September");
        series4.getData().add(new XYChart.Data(EPYC, epyc9));
        series4.getData().add(new XYChart.Data(GPU, gpu9));
        series4.getData().add(new XYChart.Data(OPETRON, opetron9));
        
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("October");
        series5.getData().add(new XYChart.Data(EPYC, epyc10));
        series5.getData().add(new XYChart.Data(GPU, gpu10));
        series5.getData().add(new XYChart.Data(OPETRON, opetron10));
        
        XYChart.Series series6 = new XYChart.Series();
        series6.setName("November");
        series6.getData().add(new XYChart.Data(EPYC, epyc11));
        series6.getData().add(new XYChart.Data(GPU, gpu11));
        series6.getData().add(new XYChart.Data(OPETRON, opetron11));
        
        XYChart.Series series7 = new XYChart.Series();
        series7.setName("December");
        series7.getData().add(new XYChart.Data(EPYC, epyc12));
        series7.getData().add(new XYChart.Data(GPU, gpu12));
        series7.getData().add(new XYChart.Data(OPETRON, opetron12));
        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String [] args){
        launch(args);
    }
}    
