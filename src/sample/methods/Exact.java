package sample.methods;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Exact {
    private double x0;
    private double y0;
    private double X;
    private double N;
    private double c;

    private XYChart.Series series;

    public Exact(double x0, double y0, double x, double n) {
        this.x0 = x0;
        this.y0 = y0;
        X = x;
        N = n;
        this.series = setSeries(x0, X, y0, N);
    }

    private XYChart.Series setSeries(double x0, double X, double y0, double N){
        double h = (X-x0)/N;
        this.c =  (y0+x0-1)/Math.exp(-x0);
        XYChart.Series series = new XYChart.Series();
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        for(double i=x0; i<X; i+=h){
            datas.add(new XYChart.Data(i,-i+1+c*Math.exp(-i)));
        }
        series.setName("Exact");
        series.setData(datas);
        return series;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
