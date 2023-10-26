package com.image.mosaique.crossed;


import com.image.mosaique.AbstractCalculator;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueShape;
import com.image.mosaique.crossed.calculator.LowerCalculator;


public class LowerCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaiqueShape} by image.
     *
     * @param image the image to use
     * @param width the width
     * @param height the height
     */
    public LowerCrossedShape(BufferedArtImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return LowerCalculator.getInstance();
    }
}
