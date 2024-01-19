/*
 * Height.java
 *
 * created at 2024-01-15 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.value_objects;


import java.util.Objects;

import com.image.domain.exception.IllegalImageHeightException;


public final class Height
{
    public final int value;

    public Height(final int value)
    {
        if (value <= 0)
        {
            throw new IllegalImageHeightException("Height of an image cannot be zero or negative");
        }
        this.value = value;
    }


    public int getHeight()
    {
        return value;
    }


    public Height add(Height heightToAdd)
    {
        return new Height(value + heightToAdd.value);
    }


    public Height subtract(Height heightToSubtract)
    {
        return new Height(value - heightToSubtract.value);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass()))
        {
            return false;
        }
        Height other = (Height)obj;
        return value == other.value;
    }


    @Override
    public String toString()
    {
        return "Height [value=" + value + "]";
    }
}
