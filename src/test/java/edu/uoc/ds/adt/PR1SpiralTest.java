package edu.uoc.ds.adt;

import org.junit.Test;
import static org.junit.Assert.*;

public class PR1SpiralTest {

    @org.junit.Test
    public void testGenerateNumberOfPoints() {

        double a = 0;
        double b = 1;
        double thetaMax = Math.PI;
        double step = Math.PI / 4;

        Point[] points = SpiralGenerator.generate(a, b, thetaMax, step);

        int expected = (int) Math.floor(thetaMax / step) + 1;

        assertEquals(expected, points.length);
    }

    @org.junit.Test
    public void testFirstPointIsCorrect() {

        double a = 2;
        double b = 1;

        Point[] points = SpiralGenerator.generate(a, b, Math.PI, 0.1);

        // Quan theta = 0:
        // r = a + b*0 = a
        // x = a * cos(0) = a
        // y = a * sin(0) = 0

        assertEquals(2.0, points[0].x(), 0.0001);
        assertEquals(0.0, points[0].y(), 0.0001);
    }

    @org.junit.Test
    public void testSpiralGrowth() {

        double a = 0;
        double b = 1;
        double step = Math.PI / 2;

        Point[] points = SpiralGenerator.generate(a, b, Math.PI, step);

        // segon punt (theta = π/2)
        // r = π/2
        // x ≈ 0
        // y ≈ r

        assertEquals(0.0, points[1].x(), 0.0001);
        assertTrue(points[1].y() > 0);
    }
}