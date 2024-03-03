package com.image.domain.value_objects;


import java.util.Objects;

import com.image.domain.exception.IllegalTileException;
import com.image.implementation.mosaic.base.TileShape;


/**
 * Value object for information about a tile
 */
public final class Tile
{
    public final TileShape shape;
    public final int width;
    public final int height;

    public Tile(final TileShape shape, final int width, final int height)
    {
        Objects.requireNonNull(shape);
        if (width != height && (width <= 0 || height <= 0))
        {
            throw new IllegalTileException("Tiles may only be of equal width and height with values greater than zero");
        }
        this.shape = shape;
        this.width = width;
        this.height = height;
    }


    public TileShape getShape()
    {
        return shape;
    }


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(height, shape, width);
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
        Tile other = (Tile)obj;
        return height == other.height && Objects.equals(shape, other.shape) && width == other.width;
    }


    @Override
    public String toString()
    {
        return shape + "_" + width + "x" + height;
    }
}
