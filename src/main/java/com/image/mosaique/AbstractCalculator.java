package com.image.mosaique;


import java.awt.image.BufferedImage;
import java.util.Iterator;

import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;
import com.image.mosaique.base.IMosaiqueShape;


/**
 * The abstract base class for all calculators for {@link IMosaiqueArtist} and
 * {@link IMosaiqueShape}.
 *
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
    public final void applyTiling(BufferedImage src, BufferedArtImage dest, int width, int height)
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
     * @param width the width of the overall region
     * @param height the height of the overall region
     * @param x the current column
     * @return an iterator over y values
     */
    protected abstract Iterator<Integer> getIteratorForColumn(int width, int height, int x);
}
