package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sample.methods.Euler;
import sample.methods.Exact;

import javax.sound.sampled.Line;

public class Grid {

    private double x0;
    private double y0;
    private double X;
    private double N;
    private double c;
    private double n0;  //lower bound of Error Analyze chart
    private double eN;  //upper bound

    private XYChart.Series seriesFunction;
    private XYChart.Series seriesEuler;
    private XYChart.Series seriesEulerError;
    private XYChart.Series seriesImprovedEuler;
    private XYChart.Series seriesImprovedEulerError;
    private XYChart.Series seriesRungeKutta;
    private XYChart.Series seriesRungeKuttaError;



    public Grid(double x0, double X, double y0, double N, double n0, double eN) {
        this.seriesFunction = new Exact(x0, y0, X, N).getSeries();
        Euler euler = new Euler(x0, X, N , y0);
        this.seriesEuler = euler.getSeries();
        this.seriesEulerError = euler.getErrorSeries();
        this.seriesImprovedEuler =  euler.getImprovedSeries();
        this.seriesImprovedEulerError =  euler.getImprovedErrorSeries();
        this.seriesRungeKutta =  euler.getRungeKuttaSeries();
        this.seriesRungeKuttaError =  euler.getRungeKuttaErrorSeries();
    }

    private void errorAnalseries(){

        for(double i=n0; i<N; i++){
            Euler euler = new Euler(x0, X, i , y0);
            
        }
    }


    public XYChart.Series getFuncSeries(){
        return seriesFunction;
    }

    public XYChart.Series getSeriesEuler(){
        return seriesEuler;
    }

    public XYChart.Series getSeriesImprovedEuler(){
        return seriesImprovedEuler;
    }

    public XYChart.Series getSeriesRungeKutta() {
        return seriesRungeKutta;
    }

    public XYChart.Series getSeriesEulerError() {
        return seriesEulerError;
    }

    public XYChart.Series getSeriesImprovedEulerError() {
        return seriesImprovedEulerError;
    }

    public XYChart.Series getSeriesRungeKuttaError() {
        return seriesRungeKuttaError;
    }
}

