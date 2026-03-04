package edu.uoc.ds.adt.util;
import edu.uoc.ds.adt.PR1WeatherStationDataQueue;
import edu.uoc.ds.adt.WeatherStationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public final class CSVWeatherDataReader {

    public static final String COL_LAST_UPDATED  = "lastUpdated";
    public static final String COL_STATION_NAME  = "weatherStation.name";
    public static final String COL_PROVINCE      = "weatherStation.province";
    public static final String COL_LATITUDE      = "weatherStation.location.coordinates.0";
    public static final String COL_LONGITUDE     = "weatherStation.location.coordinates.1";
    public static final String COL_AVG_TEMP      = "avgAirTemperature";
    public static final String COL_PRECIPITATION = "precipitation";
    public static final String COL_MIN_TEMP      = "minAirTemperature";
    public static final String COL_MAX_TEMP      = "maxAirTemperature";

    public static void fillQueue(PR1WeatherStationDataQueue queue, String filename) throws java.io.IOException
    {
        CSVFormat fmt = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        int maxlines = queue.pendingCapacity();
        int currentLine = 0;

        FileInputStream file = new FileInputStream(filename);
        try (CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, fmt))
        {
            for (CSVRecord r : parser) {
                if (currentLine < maxlines) {
                    queue.add(new WeatherStationData(
                            DateUtils.parse(r.get(COL_LAST_UPDATED)),
                            r.get(COL_STATION_NAME),
                            r.get(COL_PROVINCE),
                            Double.parseDouble(r.get(COL_LATITUDE)),
                            Double.parseDouble(r.get(COL_LONGITUDE)),
                            Double.parseDouble(r.get(COL_AVG_TEMP)),
                            Double.parseDouble(r.get(COL_PRECIPITATION)),
                            Double.parseDouble(r.get(COL_MIN_TEMP)),
                            Double.parseDouble(r.get(COL_MAX_TEMP))
                    ));
                } else {
                    break;
                }
            }
        }
    }
}