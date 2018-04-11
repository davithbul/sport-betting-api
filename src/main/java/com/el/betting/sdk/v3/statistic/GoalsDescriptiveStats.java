package com.el.betting.sdk.v3.statistic;

import java.util.LinkedHashMap;

import static com.el.betting.common.MathUtils.format;

/**
 * Represents Goals descriptive statistics, for analyzing goal count (scored or conceded) stability
 * over the time and over the teams.
 */
public class GoalsDescriptiveStats {
    /** goal list */
    private int[] values;
    /** The count of games */
    private int gamesCount;
    /** AVG goal count */
    private double mean;
    /** shows team's stability, if it's low, it means team stats pretty stable, otherwise the numbers are pretty wide */
    private double stdDeviation;
    /** if it closed to mean, it means the distribution is normal */
    private double median;
    /** Most frequent goal counts with frequencies, the first one is highest frequent goal number, the last is the least frequent. */
    private LinkedHashMap<Integer, Double> goalFrequencies;
    /** It gives the number, for which 99% of the time will be less goals */
    private double percentile99;
    private double percentile95;
    private double percentile70;
    /** How many times it was less than 2.5 goals */
    private double probabilityUnder25;
    /** How many times it was less than 1.5 goals */
    private double probabilityUnder15;
    private double probabilityUnder05;
    private double probabilityUnder35;
    private double probabilityUnder45;
    private double probabilityUnder55;
    /**
     * shows what is the team tendency, if it's less than 0, than the most of goals are in over than mean, otherwise under mean.
     * If skewness is less than −1 or greater than +1, the distribution is highly skewed.
     * If skewness is between −1 and −½ or between +½ and +1, the distribution is moderately skewed.
     * If skewness is between −½ and +½, the distribution is approximately symmetric.
     */
    private double skewness;
    /**
     * Kurtosis measures how the tails are distributed compared with peak.
     * If the value is big it means the peak is high and sharp, and tails are shorter.
     * That's why we are interested more on higher kurtosis, which will mean team is more stable and most of
     * it goals are around peak, and tails (not standard goal count) are rare.
     * A distribution with excess kurtosis <0 (std kurtosis <3) is called platykurtic. Compared to a normal distribution, its tails are shorter and thinner, and often its central peak is lower and broader.
     * A distribution with excess kurtosis >0 (std kurtosis >3) is called leptokurtic. Compared to a normal distribution, its tails are longer and fatter, and often its central peak is higher and sharper.
     * The smallest possible excess kurtosis is -2 (normal kurtosis 1), and the largest is ∞, as shown here: http://brownmath.com/stat/shape.htm
     */
    private double kurtosis;

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(int gamesCount) {
        this.gamesCount = gamesCount;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public LinkedHashMap<Integer, Double> getGoalFrequencies() {
        return goalFrequencies;
    }

    public void setGoalFrequencies(LinkedHashMap<Integer, Double> goalFrequencies) {
        this.goalFrequencies = goalFrequencies;
    }

    public int getMostFrequentGoalCount() {
        return goalFrequencies.keySet().iterator().next();
    }

    /** It return goal count, for which 99% of the time will be less than returned goals count */
    public double getPercentile99() {
        return percentile99;
    }

    public void setPercentile99(double percentile99) {
        this.percentile99 = percentile99;
    }

    public double getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(double percentile95) {
        this.percentile95 = percentile95;
    }

    public double getPercentile70() {
        return percentile70;
    }

    public void setPercentile70(double percentile70) {
        this.percentile70 = percentile70;
    }

    public double getProbabilityUnder25() {
        return probabilityUnder25;
    }

    public void setProbabilityUnder25(double probabilityUnder25) {
        this.probabilityUnder25 = probabilityUnder25;
    }

    public double getProbabilityUnder15() {
        return probabilityUnder15;
    }

    public void setProbabilityUnder15(double probabilityUnder15) {
        this.probabilityUnder15 = probabilityUnder15;
    }

    public double getProbabilityUnder05() {
        return probabilityUnder05;
    }

    public void setProbabilityUnder05(double probabilityUnder05) {
        this.probabilityUnder05 = probabilityUnder05;
    }

    public double getProbabilityUnder35() {
        return probabilityUnder35;
    }

    public void setProbabilityUnder35(double probabilityUnder35) {
        this.probabilityUnder35 = probabilityUnder35;
    }

    public double getProbabilityUnder45() {
        return probabilityUnder45;
    }

    public void setProbabilityUnder45(double probabilityUnder45) {
        this.probabilityUnder45 = probabilityUnder45;
    }

    public double getProbabilityUnder55() {
        return probabilityUnder55;
    }

    public void setProbabilityUnder55(double probabilityUnder55) {
        this.probabilityUnder55 = probabilityUnder55;
    }

    public double getSkewness() {
        return skewness;
    }

    public void setSkewness(double skewness) {
        this.skewness = skewness;
    }

    public double getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(double kurtosis) {
        this.kurtosis = kurtosis;
    }

    @Override
    public String toString() {
        return
                "values=" + values +
                "gamesCount=" + gamesCount +
                        ", mean=" + format(mean, 2) +
                        ", median=" + format(median, 2) +
                        ", stdDeviation=" + format(stdDeviation, 2) +
                        ", mostFrequentGoalCount=" + format(getMostFrequentGoalCount(), 2) +
                        ", percentile99=" + format(percentile99, 2) +
                        ", percentile95=" + format(percentile95, 2) +
                        ", percentile70=" + format(percentile70, 2) +
                        ", probabilityUnder55=" + format(probabilityUnder55, 2) +
                        ", probabilityUnder45=" + format(probabilityUnder45, 2) +
                        ", probabilityUnder35=" + format(probabilityUnder35, 2) +
                        ", probabilityUnder25=" + format(probabilityUnder25, 2) +
                        ", probabilityUnder15=" + format(probabilityUnder15, 2) +
                        ", probabilityUnder05=" + format(probabilityUnder05, 2) +
                        ", skewness=" + format(skewness, 2) +
                        ", kurtosis=" + format(kurtosis, 2);
    }
}
