package com.image.implementation.mosaic.crossed;


import java.awt.image.BufferedImage;

import com.image.implementation.mosaic.AbstractCalculator;
import com.image.implementation.mosaic.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicShape;
import com.image.implementation.mosaic.crossed.calculator.CrossedLeftCalculator;


/**
 * This class represents a left cross as {@link IMosaicShape}
 */
public class LeftCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public LeftCrossedShape(BufferedImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return CrossedLeftCalculator.getInstance();
    }
}
