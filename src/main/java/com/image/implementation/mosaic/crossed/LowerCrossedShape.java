package com.image.implementation.mosaic.crossed;


import java.awt.image.BufferedImage;

import com.image.implementation.AbstractCalculator;
import com.image.implementation.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicShape;
import com.image.implementation.mosaic.crossed.calculator.CrossedLowerCalculator;


/**
 * This class represents a lower cross as {@link IMosaicShape}
 */
public final class LowerCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public LowerCrossedShape(BufferedImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return CrossedLowerCalculator.getInstance();
    }
}
