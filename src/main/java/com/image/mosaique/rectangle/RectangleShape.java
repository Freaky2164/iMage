package com.image.mosaique.rectangle;


import com.image.mosaique.AbstractCalculator;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueShape;


/**
 * This class represents a rectangle as {@link IMosaiqueShape} based on an {@link BufferedArtImage}.
 */
public class RectangleShape extends AbstractShape
{

    /**
     * Create a new {@link IMosaiqueShape}.
     *
     * @param image the image to use
     * @param width     the width
     * @param height     the height
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
