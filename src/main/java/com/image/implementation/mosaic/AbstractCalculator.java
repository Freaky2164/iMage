package com.image.implementation.mosaic;


import java.awt.image.BufferedImage;
import java.util.Iterator;

import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.base.IMosaicShape;


/**
 * The abstract base class for all calculators for {@link IMosaicArtist} and {@link IMosaicShape}.
 */
public abstract class AbstractCalculator
{

    /**
     * Calculate a specific average color for a given region.
     *
     * @param region the region
     * @return the average color as ARGB
     */
    public abstract int averageColor(BufferedImage region);


    /**
     * Draw the shape of the specific rectangle based on the target region and the scaled instance.
     *
     * @param src    the source image
     * @param dest   the target region
     * @param width  the width to be drawn
     * @param height the height to be drawn
     */
    public final void applyTiling(BufferedImage src, BufferedImage dest, int width, int height)
    {
        for (int x = 0; x < width; x++)
        {
            var yIter = getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                dest.setRGB(x, y, src.getRGB(x, y));
            }
        }
    }


    /**
     * Get an iterator over all y values of a given column x.
     *
     * @param width  the width of the overall region
     * @param height the height of the overall region
     * @param x      the current column
     * @return an iterator over y values
     */
    protected abstract Iterator<Integer> getIteratorForColumn(int width, int height, int x);
}
