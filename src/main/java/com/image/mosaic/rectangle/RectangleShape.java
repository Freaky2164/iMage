package com.image.mosaic.rectangle;


import com.image.mosaic.AbstractCalculator;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicShape;


/**
 * This class represents a rectangle as {@link IMosaicShape} based on an {@link BufferedArtImage}.
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
    public RectangleShape(BufferedArtImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return RectangleCalculator.getInstance();
    }
}
