package edu.uoc.ds.adt;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double x() { return x; }
    public double y() { return y; }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return Double.compare(x, p.x) == 0 && Double.compare(y, p.y) == 0;
    }
}