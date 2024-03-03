package com.image.implementation.mosaic.crossed;


import java.awt.image.BufferedImage;

import com.image.implementation.mosaic.AbstractCalculator;
import com.image.implementation.mosaic.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicShape;
import com.image.implementation.mosaic.crossed.calculator.CrossedRightCalculator;


/**
 * This class represents a right cross as {@link IMosaicShape}
 */
public final class RightCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public RightCrossedShape(BufferedImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return CrossedRightCalculator.getInstance();
    }
}
