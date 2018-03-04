package com.flefel.CodingExercise.adapters;

import java.util.Date;

public class OffersSearchValidatorRequest {
    private Date fromDate;
    private Date toDate;
    private int lengthOfStay;
    private int minRating;
    private int maxRating;
    private int minTotalRating;
    private int maxTotalRating;
    private int minGuestRating;
    private int maxGuestRating;

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setMinTotalRating(int minTotalRating) {
        this.minTotalRating = minTotalRating;
    }

    public int getMinTotalRating() {
        return minTotalRating;
    }

    public void setMaxTotalRating(int maxTotalRating) {
        this.maxTotalRating = maxTotalRating;
    }

    public int getMaxTotalRating() {
        return maxTotalRating;
    }

    public void setMinGuestRating(int minGuestRating) {
        this.minGuestRating = minGuestRating;
    }

    public int getMinGuestRating() {
        return minGuestRating;
    }

    public void setMaxGuestRating(int maxGuestRating) {
        this.maxGuestRating = maxGuestRating;
    }

    public int getMaxGuestRating() {
        return maxGuestRating;
    }
}
