package sample.methods;

public class RungeKutta extends Euler {
    public RungeKutta(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Runge-Kutta");
        super.errorSeries.setName("Runge-Kutta");
    }

    @Override
    protected double funcYi(double xLast, double yLast) {
        double k1 = super.h*super.slope(xLast, yLast);
        double k2 = super.h*super.slope(xLast+super.h/2, yLast + k1/2);
        double k3 = super.h*super.slope(xLast+super.h/2, yLast + k2/2);
        double k4 = super.h*super.slope(xLast+super.h, yLast + k3);
        return (yLast+ (1.0/6.0)*(k1+2*k2+2*k3+k4));
    }
}
