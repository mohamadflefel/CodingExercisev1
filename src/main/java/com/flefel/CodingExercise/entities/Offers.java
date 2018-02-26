package com.flefel.CodingExercise.entities;

public class Offers
{
    private Hotel[] Hotel;

    public Hotel[] getHotel ()
    {
        return Hotel;
    }

    public void setHotel (Hotel[] Hotel)
    {
        this.Hotel = Hotel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Hotel = "+Hotel+"]";
    }
}
