package com.image.implementation.mosaic;


import java.awt.image.BufferedImage;
import java.util.Objects;

import com.image.implementation.mosaic.base.IMosaicShape;
import com.image.implementation.mosaic.base.ImageUtils;


public abstract class AbstractShape implements IMosaicShape
{
    protected final BufferedImage image;
    private AbstractCalculator calc;
    private int average;

    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    protected AbstractShape(BufferedImage image, int width, int height)
    {
        this.image = ImageUtils.scale(Objects.requireNonNull(image), width, height);
        calc = getCalculator();
        average = calc.averageColor(this.image);
    }


    /**
     * Get the calculator for the shape.
     *
     * @return the calculator
     */
    protected abstract AbstractCalculator getCalculator();


    @Override
    public final int getAverageColor()
    {
        return average;
    }


    @Override
    public final void drawMe(BufferedImage targetRect)
    {
        if (targetRect.getWidth() > getWidth() || targetRect.getHeight() > getHeight())
        {
            throw new IllegalArgumentException("Dimensions of target are too big for this tile");
        }

        int width = Math.min(getWidth(), targetRect.getWidth());
        int height = Math.min(getHeight(), targetRect.getHeight());

        calc.applyTiling(image, targetRect, width, height);
    }


    @Override
    public final int getWidth()
    {
        return image.getWidth();
    }


    @Override
    public final int getHeight()
    {
        return image.getHeight();
    }
}
