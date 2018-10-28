package sample.methods;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Exact {
    private XYChart.Series series;

    public Exact(double x0, double y0, double x, double n) {
        this.series = setSeries(x0, x, y0, n);
    }

    //Exact solution of IVP problem
    private double exactFunction(double y0, double x0, double x){
        double c = (y0+x0-1)/Math.exp(-x0);
        return -x+1+c*Math.exp(-x);
    }

    //Set series for that solution
    private XYChart.Series setSeries(double x0, double X, double y0, double N){
        double h = (X-x0)/N;
        XYChart.Series series = new XYChart.Series();
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        for(double x=x0; x<=X; x+=h){
            datas.add(new XYChart.Data(x, exactFunction(y0, x0, x)));
        }
        series.setName("Exact");
        series.setData(datas);
        return series;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
