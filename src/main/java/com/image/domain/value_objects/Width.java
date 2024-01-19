/*
 * Width.java
 *
 * created at 2024-01-15 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.value_objects;


import java.util.Objects;

import com.image.domain.exception.IllegalImageWidthException;


public final class Width
{
    public final int value;

    public Width(final int value)
    {
        if (value <= 0)
        {
            throw new IllegalImageWidthException("Width of an image cannot be zero or negative");
        }
        this.value = value;
    }


    public int getWidth()
    {
        return value;
    }


    public Width add(Width widthToAdd)
    {
        return new Width(value + widthToAdd.value);
    }


    public Width subtract(Width widthToSubtract)
    {
        return new Width(value - widthToSubtract.value);
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
        Width other = (Width)obj;
        return value == other.value;
    }


    @Override
    public String toString()
    {
        return "Width [value=" + value + "]";
    }
}
