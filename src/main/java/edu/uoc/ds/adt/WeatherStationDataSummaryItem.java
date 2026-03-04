package edu.uoc.ds.adt;

public class WeatherStationDataSummaryItem {

    private final double accumulatedPrecipitation;
    private final double meanAvgAirTemperature;
    private final int numRows;

    public WeatherStationDataSummaryItem(double accumulatedPrecipitation,
                                         double meanAvgAirTemperature,
                                         int numRows) {
        this.accumulatedPrecipitation = accumulatedPrecipitation;
        this.meanAvgAirTemperature = meanAvgAirTemperature;
        this.numRows = numRows;
    }

    public double getAccumulatedPrecipitation() {
        return accumulatedPrecipitation;
    }

    public double getMeanAvgAirTemperature() {
        return meanAvgAirTemperature;
    }

    public int numRows() {
        return numRows;
    }
}