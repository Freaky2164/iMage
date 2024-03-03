/*
 * TileShape.java
 *
 * created at 2024-01-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
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
