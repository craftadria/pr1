package edu.uoc.ds.adt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uoc.ds.adt.util.DateUtils;

public class PR1WeatherStationDataQueue {

    private final PR1Queue<WeatherStationData> pendingQueue;
    private final PR1Stack<WeatherStationData> processedStack;

    public PR1WeatherStationDataQueue(int size) throws java.io.IOException {
        this.pendingQueue = new PR1Queue<>(size);
        this.processedStack = new PR1Stack<>(size);
    }

    public void add(WeatherStationData item)
    {
        this.pendingQueue.add(item);
    }

    /** Returns (without removing) the head of the pending queue. */
    public WeatherStationData peek() {
        return pendingQueue.peek();
    }

    /**
     * Dequeues and returns the head of the pending queue.
     * Does NOT push the record onto the processed stack.
     */
    public WeatherStationData poll() {
        return pendingQueue.poll();
    }

    /** Number of records still pending in the queue. */
    public int pendingSize() {
        return pendingQueue.size();
    }

    /** Declared capacity of the queue. */
    public int pendingCapacity() {
        return pendingQueue.CAPACITY;
    }

    /** True when no records remain in the pending queue. */
    public boolean isPendingEmpty() {
        return pendingQueue.isEmpty();
    }

    /** Returns the underlying PR1Queue. */
    public PR1Queue<WeatherStationData> getPendingQueue() {
        return pendingQueue;
    }

    /**
     * Dequeues the head record from the pending queue and pushes it onto
     * the processed stack.
     *
     * @return the processed record, or null if the queue was empty
     */
    public WeatherStationData processNext() {
        WeatherStationData record = pendingQueue.poll();
        if (record != null) {
            processedStack.push(record);
        }
        return record;
    }

    /** Returns (without removing) the most recently processed record. */
    public WeatherStationData peekProcessed() {
        return processedStack.peek();
    }

    /**
     * Undoes the last processNext(): pops from the stack and re-enqueues
     * at the tail of the pending queue.
     *
     * @return the record moved back, or null if the stack was empty
     */
    public WeatherStationData undoProcessed() {
        WeatherStationData record = processedStack.pop();
        if (record != null) {
            pendingQueue.add(record);
        }
        return record;
    }

    /** Number of records already processed (in the stack). */
    public int processedSize() {
        return processedStack.size();
    }

    /** True when no records have been processed yet. */
    public boolean isProcessedEmpty() {
        return processedStack.isEmpty();
    }

    /** Returns the underlying PR1Stack. */
    public PR1Stack<WeatherStationData> getProcessedStack() {
        return processedStack;
    }

    /**
     * Drains pendingQueue into a List and re-enqueues all elements.
     * Used internally by every statistical method.
     */
    private List<WeatherStationData> drainAndRestore() {
        List<WeatherStationData> temp = new ArrayList<>();
        while (!pendingQueue.isEmpty()) {
            temp.add(pendingQueue.poll());
        }
        for (WeatherStationData d : temp) {
            pendingQueue.add(d);
        }
        return temp;
    }

    /**
     * Arithmetic average of precipitation over all pending records.
     *
     * @return average in mm, or 0.0 when the queue is empty
     */
    public double getAveragePrecipitation() {
        List<WeatherStationData> all = drainAndRestore();
        if (all.isEmpty()) return 0.0;
        double sum = 0.0;
        for (WeatherStationData d : all) sum += d.getPrecipitation();
        return sum / all.size();
    }

    /**
     * Arithmetic mean of precipitation over all pending records.
     *
     * @return average in mm, or 0.0 when the queue is empty
     */
    public double getMeanPrecipitation(){
        List<WeatherStationData> all = drainAndRestore();
        if (all.isEmpty()) return 0.0;

        double sum = 0.0;
        for (WeatherStationData d : all) sum += d.getPrecipitation();
        return sum / all.size();
    }

    public double getMeanAvgAirTemperature(){
        List<WeatherStationData> all = drainAndRestore();
        if (all.isEmpty()) return 0.0;

        double sum = 0.0;
        for (WeatherStationData d : all) sum += d.getAvgAirTemperature();
        return sum / all.size();
    }
    /**
     * Arithmetic mean of average air temperature over all pending records.
     *
     * @return average in ºC, or 0.0 when the queue is empty
     */
    public double getAverageAirTemperature() {
        List<WeatherStationData> all = drainAndRestore();
        if (all.isEmpty()) return 0.0;
        double sum = 0.0;
        for (WeatherStationData d : all) sum += d.getAvgAirTemperature();
        return sum / all.size();
    }

    /**
     * Groups pending records by calendar year and returns the average
     * precipitation per year.
     *
     * @return Map keyed by year; empty when the queue is empty
     */
    public Map<Integer, Double> getAvgPrecipitationByYear() {
        List<WeatherStationData> all = drainAndRestore();
        Map<Integer, Double>  sums   = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        for (WeatherStationData d : all) {
            int year = DateUtils.getYear(d.getLastUpdated());
            sums.merge(year,  d.getPrecipitation(), Double::sum);
            counts.merge(year, 1, Integer::sum);
        }
        Map<Integer, Double> result = new HashMap<>();
        sums.forEach((year, total) -> result.put(year, total / counts.get(year)));
        return result;
    }

    /**
     * Groups pending records by calendar year and returns the average air
     * temperature per year.
     *
     * @return Map keyed by year; empty when the queue is empty
     */
    public Map<Integer, Double> getAvgAirTemperatureByYear() {
        List<WeatherStationData> all = drainAndRestore();
        Map<Integer, Double>  sums   = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        for (WeatherStationData d : all) {
            int year = DateUtils.getYear(d.getLastUpdated());
            sums.merge(year,  d.getAvgAirTemperature(), Double::sum);
            counts.merge(year, 1, Integer::sum);
        }
        Map<Integer, Double> result = new HashMap<>();
        sums.forEach((year, total) -> result.put(year, total / counts.get(year)));
        return result;
    }

    public WeatherStationDataSummaryItem getWeatherStationDataSummaryItem(int year) {

        List<WeatherStationData> all = drainAndRestore();

        double totalPrecipitation = 0.0;
        double totalTemperature = 0.0;
        int count = 0;

        for (WeatherStationData d : all) {

            int recordYear = DateUtils.getYear(d.getLastUpdated());

            if (recordYear == year) {
                totalPrecipitation += d.getPrecipitation();
                totalTemperature += d.getAvgAirTemperature();
                count++;
            }
        }

        if (count == 0) {
            return new WeatherStationDataSummaryItem(0.0, 0.0, 0);
        }

        double meanTemp = totalTemperature / count;

        return new WeatherStationDataSummaryItem(
                totalPrecipitation,
                meanTemp,
                count
        );
    }
}
