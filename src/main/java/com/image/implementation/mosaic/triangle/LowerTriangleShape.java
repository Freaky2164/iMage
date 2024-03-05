package com.image.implementation.mosaic.triangle;


import java.awt.image.BufferedImage;

import com.image.implementation.AbstractCalculator;
import com.image.implementation.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicShape;


/**
 * This abstract class represents a lower triangle as {@link IMosaicShape}
 *
 * Two different triangles are possible: Upper and Lower.
 */
public final class LowerTriangleShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public LowerTriangleShape(BufferedImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return LowerTriangleCalculator.getInstance();
    }
}
