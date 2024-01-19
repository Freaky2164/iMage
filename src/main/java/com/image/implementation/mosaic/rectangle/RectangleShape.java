package com.image.implementation.mosaic.rectangle;


import java.awt.image.BufferedImage;

import com.image.implementation.mosaic.AbstractCalculator;
import com.image.implementation.mosaic.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicShape;


/**
 * This class represents a rectangle as {@link IMosaicShape} based on an {@link FileInformation}.
 */
public class RectangleShape extends AbstractShape
{

    /**
     * Create a new {@link IMosaicShape}.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public RectangleShape(BufferedImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return RectangleCalculator.getInstance();
    }
}
