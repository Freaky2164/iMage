package com.image.mosaic.triangle;


import com.image.mosaic.AbstractCalculator;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicShape;


/**
 * This abstract class represents an upper triangle as {@link IMosaicShape} based on an
 * {@link BufferedArtImage}.
 *
 * Two different triangles are possible: Upper and Lower.
 *
 * Image:
 *
 * <pre>
 * (0,0)-----------|
 *   | \           |
 *   |  \          |
 *   |   \  UPPER  |
 *   |    \        |
 *   |     \       |
 *   |      \      |
 *   |       \     |
 *   |        \    |
 *   |         \   |
 *   |          \  |
 *   | LOWER     \ |
 *   |            \|
 *   |-----------(w,h)
 * </pre>
 *
 */
public class UpperTriangleShape extends AbstractShape
{
    /**
     * Create a new {@link IMosaicShape} by image.
     *
     * @param image  the image to use
     * @param width  the width
     * @param height the height
     */
    public UpperTriangleShape(BufferedArtImage image, int width, int height)
    {
        super(image, width, height);
    }


    @Override
    protected AbstractCalculator getCalculator()
    {
        return UpperTriangleCalculator.getInstance();
    }
}
