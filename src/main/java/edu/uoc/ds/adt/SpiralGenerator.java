package edu.uoc.ds.adt;

public class SpiralGenerator {

    private final double a; // separació inicial
    private final double b; // increment per volta

    public SpiralGenerator(double a, double b){
        this.a = a;
        this.b = b;
    }

    public static Point[] generate(double a, double b, double thetaMax, double step) {
        int n = (int) Math.floor(thetaMax / step) + 1;
        Point[] points = new Point[n];

        double theta = 0.0;

        for (int i = 0; i < n; i++) {
            double r = a + b * theta;

            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);

            points[i] = new Point(x, y);

            theta += step;
        }

        return points;
    }
}