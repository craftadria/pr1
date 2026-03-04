package edu.uoc.ds.adt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PR1StackTest {

    PR1Stack pr1s;

    private void fillStack(Point[] points) {
        for (Point point : points) {
            pr1s.push(point);
        }
    }

    @Before
    public void setUp() {
        double a = 0;
        double b = 5;
        double thetaMax = 10 * Math.PI;
        double step = 0.05;

        Point[] points = SpiralGenerator.generate(a, b, thetaMax, step);

        this.pr1s = new PR1Stack(points.length);

        assertNotNull(this.pr1s.getStack());
        fillStack(points);
    }

    @After
    public void release() {
        this.pr1s = null;
    }


    @org.junit.Test
    public void stackTest() {

        assertEquals(629, this.pr1s.getStack().size());

        Point point = (Point)pr1s.pop();

        assertEquals(156.99, point.x(), 0.05);
        assertEquals(-2.50, point.y(), 0.05);

        point = (Point)pr1s.pop();
        assertEquals(156.41, point.x(), 0.05);
        assertEquals(-10.33, point.y(), 0.05);

        point = (Point)pr1s.pop();
        assertEquals(155.45, point.x(), 0.05);
        assertEquals(-18.10, point.y(), 0.05);

        point = (Point)pr1s.pop();
        assertEquals(154.10, point.x(), 0.05);
        assertEquals(-25.81, point.y(), 0.05);

        point = (Point)pr1s.pop();
        assertEquals(152.38, point.x(), 0.05);
        assertEquals(-33.42, point.y(), 0.05);

        point = (Point)pr1s.pop();
        assertEquals(150.27, point.x(), 0.05);
        assertEquals(-40.93, point.y(), 0.05);

        assertEquals(623, this.pr1s.getStack().size());
    }
}
