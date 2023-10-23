package com.image.mosaique.crossed;


import com.image.mosaique.AbstractCalculator;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueShape;
import com.image.mosaique.crossed.calculator.LeftCalculator;


public class LeftCrossedShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaiqueShape} by image.
     *
     * @param image
     *              the image to use
     * @param w
     *              the width
     * @param h
     *              the height
     */
    public LeftCrossedShape(BufferedArtImage image, int w, int h)
    {
        super(image, w, h);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return LeftCalculator.getInstance();
    }
}
