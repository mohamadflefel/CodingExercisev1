package com.flefel.CodingExercise.adapters;

import java.util.Date;

public class OffersRequest {
    private String location;
    private boolean isListAll;
      private Date fromDate;
    private Date toDate;
    private int lengthOfStay;
    private int minRating;
    private int maxRating;
    private int minTotalRating;
    private int maxTotalRating;
    private int minGuestRating;
    private int maxGuestRating;

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public boolean getIsListAll() {
        return isListAll;
    }

    public boolean isListAll() {
        return isListAll;
    }

    public void setListAll(boolean isListAll) {
        this.isListAll = isListAll;
    }


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
