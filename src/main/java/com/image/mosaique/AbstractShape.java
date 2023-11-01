package com.image.mosaique;


import java.awt.image.BufferedImage;
import java.util.Objects;

import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueShape;
import com.image.mosaique.base.ImageUtils;


public abstract class AbstractShape implements IMosaiqueShape
{
    protected final BufferedImage image;
    private AbstractCalculator calc;
    private int average;

    /**
     * Create a new {@link IMosaiqueShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    protected AbstractShape(BufferedArtImage image, int width, int height)
    {
        this.image = ImageUtils.scale(Objects.requireNonNull(image.toBufferedImage()), width, height);
        this.calc = getCalculator();
        this.average = this.calc.averageColor(this.image);
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
    public final void drawMe(BufferedArtImage targetRect)
    {
        if (targetRect.getWidth() > this.getWidth() || targetRect.getHeight() > this.getHeight())
        {
            throw new IllegalArgumentException("Dimensions of target are too big for this tile");
        }

        int width = Math.min(this.getWidth(), targetRect.getWidth());
        int height = Math.min(this.getHeight(), targetRect.getHeight());

        this.calc.applyTiling(image, targetRect, width, height);
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
