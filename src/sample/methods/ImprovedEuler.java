package sample.methods;

public class ImprovedEuler extends Euler{

    public ImprovedEuler(double x0, double x, double n, double y0) {
        super(x0, x, n, y0);
        super.series.setName("Improved Euler");
        super.errorSeries.setName("Improved Euler");
    }

    //Get y_(i+1) by Improved Euler Method
    @Override
    protected double funcYi(double xLast, double yLast) {
        return yLast+super.h*
                ((super.slope(xLast,yLast)+
                        super.slope(xLast+super.h, super.eulerY(xLast,yLast)))/2);
    }
}
