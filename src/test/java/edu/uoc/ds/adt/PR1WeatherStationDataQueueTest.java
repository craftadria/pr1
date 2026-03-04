package edu.uoc.ds.adt;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.io.IOException;

public class PR1WeatherStationDataQueueTest {
    private static final int SIZE = 1304;
    private PR1WeatherStationDataQueue pr1q;

    @Before
    public void setUp() throws IOException {
        pr1q = new PR1WeatherStationDataQueue(SIZE);
        edu.uoc.ds.adt.util.CSVWeatherDataReader.fillQueue(pr1q, "weatherData.csv");
    }

    @org.junit.Test
    public void testLoadCSV_sizeAndOrder() throws IOException {
        Assert.assertEquals(SIZE, pr1q.pendingSize());
        Assert.assertTrue(pr1q.isProcessedEmpty());
        WeatherStationData first = pr1q.peek();
        Assert.assertNotNull(first);
        Assert.assertEquals("MANRESA", first.getStationName());
        Assert.assertEquals(SIZE, pr1q.pendingSize());
    }

    @org.junit.Test
    public void queueTest() {
        Assert.assertEquals(pr1q.pendingCapacity(), this.pr1q.getPendingQueue().size());

        WeatherStationData weatherData1 = this.pr1q.getPendingQueue().poll();

        Assert.assertEquals("MANRESA",weatherData1.getStationName());
        Assert.assertEquals(0,weatherData1.getPrecipitation(),0);
        Assert.assertEquals(18.4,weatherData1.getAvgAirTemperature(),0);

        WeatherStationData weatherData2 = this.pr1q.getPendingQueue().poll();

        Assert.assertEquals("MANRESA",weatherData2.getStationName());
        Assert.assertEquals(0,weatherData2.getPrecipitation(),0);
        Assert.assertEquals(17.0,weatherData2.getAvgAirTemperature(),0);

        Assert.assertEquals(1, pr1q.getMeanPrecipitation(), 0.1);
        Assert.assertEquals(15.96, pr1q.getMeanAvgAirTemperature(),0.1);
    }

    @org.junit.Test
    public void queueTest2(){
        Assert.assertEquals(SIZE, this.pr1q.getPendingQueue().size());
        WeatherStationDataSummaryItem dataInfo1 = this.pr1q.getWeatherStationDataSummaryItem(2023);
        Assert.assertEquals(1381.30, dataInfo1.getAccumulatedPrecipitation(), 0.05);
        Assert.assertEquals(16.17, dataInfo1.getMeanAvgAirTemperature(),0.05);
        Assert.assertEquals(1213, dataInfo1.numRows());

        WeatherStationDataSummaryItem dataInfo2 = this.pr1q.getWeatherStationDataSummaryItem(2024);
        Assert.assertEquals(24.60, dataInfo2.getAccumulatedPrecipitation(), 0.05);
        Assert.assertEquals(7.91, dataInfo2.getMeanAvgAirTemperature(),0.05);
        Assert.assertEquals(19, dataInfo2.numRows());

        WeatherStationDataSummaryItem dataInfo3 = this.pr1q.getWeatherStationDataSummaryItem(2025);
        Assert.assertEquals(0.60, dataInfo3.getAccumulatedPrecipitation(), 0.05);
        Assert.assertEquals(13.08, dataInfo3.getMeanAvgAirTemperature(),0.05);
        Assert.assertEquals(46, dataInfo3.numRows());

        WeatherStationDataSummaryItem dataInfo4 = this.pr1q.getWeatherStationDataSummaryItem(2026);
        Assert.assertEquals(14.0, dataInfo4.getAccumulatedPrecipitation(), 0.05);
        Assert.assertEquals(17.74, dataInfo4.getMeanAvgAirTemperature(),0.05);
        Assert.assertEquals(26, dataInfo4.numRows());
    }

    @org.junit.Test
    public void testAveragePrecipitation() {
        Assert.assertEquals(
                pr1q.getMeanPrecipitation(),
                pr1q.getAveragePrecipitation(),
                0.0001
        );
    }

    @After
    public void release() {
        this.pr1q = null;
    }

}