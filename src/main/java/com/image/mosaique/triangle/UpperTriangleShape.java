package com.image.mosaique.triangle;


import com.image.mosaique.AbstractCalculator;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueShape;


/**
 * This abstract class represents an upper triangle as {@link IMosaiqueShape} based on an
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
     * Create a new {@link IMosaiqueShape} by image.
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