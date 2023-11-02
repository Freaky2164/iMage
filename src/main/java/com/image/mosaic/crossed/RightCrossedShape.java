package com.image.mosaic.crossed;


import com.image.mosaic.AbstractCalculator;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicShape;
import com.image.mosaic.crossed.calculator.RightCalculator;


public class RightCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image the image to use
     * @param width the width
     * @param height the height
     */
    public RightCrossedShape(BufferedArtImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return RightCalculator.getInstance();
    }
}
