package edu.uoc.ds.adt;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Immutable value object that represents one row of the weatherData.csv file.
 */
public class WeatherStationData {

    private final LocalDateTime lastUpdated;
    private final String        stationName;
    private final String        province;
    private final double        latitude;
    private final double        longitude;
    private final double        avgAirTemperature;
    private final double        precipitation;
    private final double        minAirTemperature;
    private final double        maxAirTemperature;

    public WeatherStationData(LocalDateTime lastUpdated,
                              String stationName,
                              String province,
                              double latitude,
                              double longitude,
                              double avgAirTemperature,
                              double precipitation,
                              double minAirTemperature,
                              double maxAirTemperature) {
        this.lastUpdated       = lastUpdated;
        this.stationName       = stationName;
        this.province          = province;
        this.latitude          = latitude;
        this.longitude         = longitude;
        this.avgAirTemperature = avgAirTemperature;
        this.precipitation     = precipitation;
        this.minAirTemperature = minAirTemperature;
        this.maxAirTemperature = maxAirTemperature;
    }

    public LocalDateTime getLastUpdated()       { return lastUpdated;       }
    public String        getStationName()       { return stationName;       }
    public String        getProvince()          { return province;          }
    public double        getLatitude()          { return latitude;          }
    public double        getLongitude()         { return longitude;         }
    public double        getAvgAirTemperature() { return avgAirTemperature; }
    public double        getPrecipitation()     { return precipitation;     }
    public double        getMinAirTemperature() { return minAirTemperature; }
    public double        getMaxAirTemperature() { return maxAirTemperature; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherStationData d)) return false;
        return Double.compare(d.latitude,          latitude)          == 0
                && Double.compare(d.longitude,         longitude)         == 0
                && Double.compare(d.avgAirTemperature, avgAirTemperature) == 0
                && Double.compare(d.precipitation,     precipitation)     == 0
                && Double.compare(d.minAirTemperature, minAirTemperature) == 0
                && Double.compare(d.maxAirTemperature, maxAirTemperature) == 0
                && Objects.equals(lastUpdated, d.lastUpdated)
                && Objects.equals(stationName, d.stationName)
                && Objects.equals(province,    d.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastUpdated, stationName, province,
                latitude, longitude, avgAirTemperature,
                precipitation, minAirTemperature, maxAirTemperature);
    }

    @Override
    public String toString() {
        return "WeatherStationData{"
                + "lastUpdated="     + lastUpdated
                + ", station='"     + stationName  + '\''
                + ", province='"    + province     + '\''
                + ", lat="          + latitude
                + ", lon="          + longitude
                + ", avgTemp="      + avgAirTemperature
                + ", precip="       + precipitation
                + ", minTemp="      + minAirTemperature
                + ", maxTemp="      + maxAirTemperature
                + '}';
    }
}