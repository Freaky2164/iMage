package com.image.implementation.mosaic.base;

/**
 * Enum for categorizing the different shapes of tiles
 */
public enum TileShape
{
    RECTANGLE("Rectangle"),
    TRIANGLE("Triangle"),
    CROSSED("Crossed");

    private String shape;

    TileShape(String shape)
    {
        this.shape = shape;
    }


    @Override
    public String toString()
    {
        return shape;
    }
}
