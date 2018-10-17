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

    private XYChart.Series seriesFunction;   //Data set of exact function
    private XYChart.Series seriesEuler;      //Data set of Euler method
    private XYChart.Series seriesEulerError; //Data set of Euler method's errors
    private XYChart.Series seriesEulerErrorN; //Data set of Euler method's errors depends of N
    private XYChart.Series seriesImprovedEuler; //Data set of Improved Euler method
    private XYChart.Series seriesImprovedEulerError; //Data set of Improved Euler method's errors
    private XYChart.Series seriesImprovedEulerErrorN; //Data set of Improved Euler method's errors depends of N
    private XYChart.Series seriesRungeKutta;  //Data set of Runge-Kutta method
    private XYChart.Series seriesRungeKuttaError; //Data set of Runge-Kutta method's errors
    private XYChart.Series seriesRungeKuttaErrorN; //Data set of Runge-Kutta method's errors depends of N


    public Grid(double x0, double X, double y0, double N, double n0, double eN) {
        this.seriesFunction = new Exact(x0, y0, X, N).getSeries();
        Euler euler = new Euler(x0, X, N, y0);
        this.seriesEuler = euler.getSeries();
        this.seriesEulerError = euler.getErrorSeries();
        this.seriesImprovedEuler = euler.getImprovedSeries();
        this.seriesImprovedEulerError = euler.getImprovedErrorSeries();
        this.seriesRungeKutta = euler.getRungeKuttaSeries();
        this.seriesRungeKuttaError = euler.getRungeKuttaErrorSeries();
        errorAnalseries(x0, X, y0, N, n0, eN);
    }

    private void errorAnalseries(double x0, double X, double y0, double N, double n0, double eN) {
        ObservableList<XYChart.Data> dataEEu = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> dataEIEu = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> dataERK = FXCollections.observableArrayList();
        this.seriesEulerErrorN = new XYChart.Series();
        this.seriesImprovedEulerErrorN = new XYChart.Series();
        this.seriesRungeKuttaErrorN = new XYChart.Series();
        for (double i = n0; i < eN; i++) {
            Euler euler = new Euler(x0, X, i, y0);
            dataEEu.add(new XYChart.Data(i, euler.getMaxError()));
            dataEIEu.add(new XYChart.Data(i, euler.getIEulerError()));
            dataERK.add(new XYChart.Data(i, euler.getRKuttaError()));
        }
        this.seriesEulerErrorN.setData(dataEEu);
        this.seriesEulerErrorN.setName("Euler");
        this.seriesImprovedEulerErrorN.setData(dataEIEu);
        this.seriesImprovedEulerErrorN.setName("Improved Euler");
        this.seriesRungeKuttaErrorN.setData(dataERK);
        this.seriesRungeKuttaErrorN.setName("Runge-Kutta");

    }


    public XYChart.Series getFuncSeries() {
        return seriesFunction;
    }

    public XYChart.Series getSeriesEuler() {
        return seriesEuler;
    }

    public XYChart.Series getSeriesImprovedEuler() {
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

    public XYChart.Series getSeriesEulerErrorN() {
        return seriesEulerErrorN;
    }

    public XYChart.Series getSeriesImprovedEulerErrorN() {
        return seriesImprovedEulerErrorN;
    }

    public XYChart.Series getSeriesRungeKuttaErrorN() {
        return seriesRungeKuttaErrorN;
    }
}

