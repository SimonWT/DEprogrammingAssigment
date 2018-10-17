package sample.methods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Euler {
    protected XYChart.Series series;
    protected XYChart.Series errorSeries;
    private double x0;
    private double X;
    private double N;
    private double y0;
    private double c; //const
    protected double h; //step

    private double maxError=0;

    public Euler(double x0, double x, double n, double y0) {
        this.x0 = x0;
        X = x;
        N = n;
        this.y0 = y0;
        this.c = (y0+x0-1)/Math.exp(-x0);
        this.h = (X-x0)/this.N;
        this.series = new XYChart.Series();
        this.errorSeries = new XYChart.Series();
        this.series.setName("Euler");
        this.errorSeries.setName("Euler");
        setSeries();

    }

    private void setSeries(){
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> errorDatas = FXCollections.observableArrayList();

        datas.add(new XYChart.Data(x0, y0));
        double error = exact(x0) - y0;
        maxError = error;
        errorDatas.add(new XYChart.Data(x0, error));

        double yLast =funcYi(x0, y0);
        for(double i=x0+h; i<=X; i+=h){
            double xi = i;
            if(xi> X) break;
            datas.add(new XYChart.Data(xi, yLast));
            error = exact(xi) - yLast;
            errorDatas.add(new XYChart.Data(xi, exact(xi) - yLast));
            if(error> maxError) maxError = error;
            yLast = funcYi(xi, yLast);
        }
        errorSeries.setData(errorDatas);
        series.setData(datas);
    }

    protected double slope(double x, double y){
        return (-y-x);
    }

    private double getYi(double xLast, double yLast){
        return funcYi(xLast,yLast);
    }

    protected double funcYi(double xLast, double yLast){
        return (eulerY(xLast, yLast));
    }

    protected double eulerY(double xLast, double yLast){
        return yLast+h*slope(xLast, yLast);
    }

    protected double exact(double xLast){
        double c = (y0+x0-1)/Math.exp(-x0);
        return -xLast+1+c*Math.exp(-xLast);
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public XYChart.Series getErrorSeries() {
        return errorSeries;
    }


    public XYChart.Series getImprovedSeries(){
        return  new ImprovedEuler(x0, X, N, y0).getSeries();
    }

    public XYChart.Series getImprovedErrorSeries(){
        return  new ImprovedEuler(x0, X, N, y0).getErrorSeries();
    }


    public XYChart.Series getRungeKuttaSeries(){
        return (new RungeKutta(x0, X, N, y0)).getSeries();
    }

    public XYChart.Series getRungeKuttaErrorSeries(){
        return (new RungeKutta(x0, X, N, y0)).getErrorSeries();
    }

    public double getMaxError() {
        return maxError;
    }

    public double getIEulerError(){
        return (new ImprovedEuler(x0, X, N, y0)).getMaxError();
    }

    public double getRKuttaError(){
        return (new RungeKutta(x0, X, N, y0)).getMaxError();
    }

}
