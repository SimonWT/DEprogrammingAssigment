package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;
import java.awt.*;


public class Controller {

    private Grid grid;

    @FXML
    private LineChart<Number,Number> MyChart1;

    @FXML
    private LineChart ErrorChart;

    @FXML
    private LineChart ErrorAnalChart;

    @FXML
    private NumberAxis xAxis1;

    @FXML
    private NumberAxis yAxis1;

    @FXML
    private NumberAxis xAxisError;

    @FXML
    private NumberAxis yAxisError;

    @FXML
    private NumberAxis xErrorAnal;

    @FXML
    private NumberAxis yErrorAnal;


    @FXML
    private TextField textFieldx0;

    @FXML
    private TextField textFieldx1;

    @FXML
    private TextField textFieldx2;

    @FXML
    private TextField textFieldx3;

    @FXML
    private TextField errAnaln0;

    @FXML
    private TextField errAnalN;

    @FXML
    public void initialize(){

        textFieldx0.setText("0");
        textFieldx1.setText("1");
        textFieldx2.setText("20");
        textFieldx3.setText("20");

        errAnaln0.setText("0");
        errAnalN.setText("20");

        xAxis1.setLabel("x");
        yAxis1.setLabel("y");

        xErrorAnal.setLabel("x");
        yErrorAnal.setLabel("error");


        xAxisError.setLabel("N");
        yAxisError.setLabel("error");

        setCharts(0, 20, 1, 20, 0, 20);
    }

    private void setCharts(double x0, double X, double y0, double N, double n0, double eN){
        grid = new Grid(x0, X, y0, N, n0, eN);
        xAxis1.setLowerBound(x0);
        MyChart1.getData().clear();
        ErrorChart.getData().clear();
        ErrorAnalChart.getData().clear();

        MyChart1.getData().addAll(grid.getFuncSeries(), grid.getSeriesEuler(),
                 grid.getSeriesImprovedEuler(), grid.getSeriesRungeKutta());
        ErrorChart.getData().addAll(grid.getSeriesEulerError(), grid.getSeriesImprovedEulerError(),
                                                                   grid.getSeriesRungeKuttaError());
        ErrorAnalChart.getData().addAll(grid.getSeriesEulerErrorN(), grid.getSeriesImprovedEulerErrorN(),
                grid.getSeriesRungeKuttaErrorN());


    }

    public void updateAllInfo(){
        Double x0=Double.parseDouble(textFieldx0.getText());
        Double y0=Double.parseDouble(textFieldx1.getText());
        Double X=Double.parseDouble(textFieldx2.getText());
        Double N=Double.parseDouble(textFieldx3.getText());

        Double n0 = Double.parseDouble(errAnaln0.getText());
        Double eN = Double.parseDouble(errAnalN.getText());

        System.out.println("CHANGED: x0= "+x0+" y0= "+y0+" X= "+X+ " N= "+N);

        setCharts(x0, X, y0, N, n0, eN);
    }
}
