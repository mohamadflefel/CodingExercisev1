package com.flefel.CodingExercise.entities;


public class OfferDateRange
{
    private String[] travelEndDate;

    private String lengthOfStay;

    private String[] travelStartDate;

    public String[] getTravelEndDate ()
    {
        return travelEndDate;
    }

    public void setTravelEndDate (String[] travelEndDate)
    {
        this.travelEndDate = travelEndDate;
    }

    public String getLengthOfStay ()
    {
        return lengthOfStay;
    }

    public void setLengthOfStay (String lengthOfStay)
    {
        this.lengthOfStay = lengthOfStay;
    }

    public String[] getTravelStartDate ()
    {
        return travelStartDate;
    }

    public void setTravelStartDate (String[] travelStartDate)
    {
        this.travelStartDate = travelStartDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [travelEndDate = "+travelEndDate+", lengthOfStay = "+lengthOfStay+", travelStartDate = "+travelStartDate+"]";
    }
}

