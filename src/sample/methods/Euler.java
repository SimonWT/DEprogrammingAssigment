package sample.methods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Euler {

    private double maxError = 0;

    public Euler(double x0, double x, double n, double y0) {
        this.x0 = x0;
        X = x;
        N = n;
        this.y0 = y0;
        this.h = (X - x0) / this.N;
        this.series = new XYChart.Series();
        this.errorSeries = new XYChart.Series();
        this.series.setName("Euler");
        this.errorSeries.setName("Euler");
        setSeries();
    }

    //Set series for that solution
    private void setSeries() {
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> errorDatas = FXCollections.observableArrayList();

        //Add initial dot to the data set
        datas.add(new XYChart.Data(x0, y0));
        //Find initial error
        double error = Math.abs(exact(x0) - y0);
        maxError = error;
        errorDatas.add(new XYChart.Data(x0, error));

        double y = funcYi(x0, y0); //Euler function from initial dot

        for (double xi = x0 + h; xi <= X; xi += h) {
            //Calculate error
            error = Math.abs(exact(xi) - y);
            //Find max error
            if (error > maxError){
                maxError = error;
                System.out.println(maxError);
            }
            datas.add(new XYChart.Data(xi, y));
            errorDatas.add(new XYChart.Data(xi, error));
            //Euler function for next iteration
            y = funcYi(xi, y);
        }
        errorSeries.setData(errorDatas);
        series.setData(datas);
    }

    //Differential Equation
    protected double slope(double x, double y) {
        return ( -y - x);
    }

    //Helper function, get 'eulerY'
    protected double funcYi(double xLast, double yLast) {
        return (eulerY(xLast, yLast));
    }

    //Get function y by Euler method
    protected double eulerY(double xLast, double yLast) {
        return yLast + h * slope(xLast, yLast);
    }

    //Exact Solution
    protected double exact(double xLast) {
        double c = (y0 + x0 - 1) / Math.exp(-x0);
        return -xLast + 1 + c * Math.exp(-xLast);
    }


    public XYChart.Series getSeries() {
        return series;
    }

    public XYChart.Series getErrorSeries() {
        return errorSeries;
    }

    public XYChart.Series getImprovedSeries() {
        return new ImprovedEuler(x0, X, N, y0).getSeries();
    }

    public XYChart.Series getImprovedErrorSeries() {
        return new ImprovedEuler(x0, X, N, y0).getErrorSeries();
    }

    public XYChart.Series getRungeKuttaSeries() {
        return (new RungeKutta(x0, X, N, y0)).getSeries();
    }

    public XYChart.Series getRungeKuttaErrorSeries() {
        return (new RungeKutta(x0, X, N, y0)).getErrorSeries();
    }

    public double getMaxError() {
        return maxError;
    }

    public double getIEulerError() {
        return (new ImprovedEuler(x0, X, N, y0)).getMaxError();
    }

    public double getRKuttaError() {
        return (new RungeKutta(x0, X, N, y0)).getMaxError();
    }

    protected XYChart.Series series;
    protected XYChart.Series errorSeries;
    private double x0;
    private double X;
    private double N;
    private double y0;
    protected double h; //step

}
