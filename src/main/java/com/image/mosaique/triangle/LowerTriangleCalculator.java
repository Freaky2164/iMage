package com.image.mosaique.triangle;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.mosaique.AbstractCalculator;


/**
 * Helper class for the {@link TriangleArtist} and {@link LowerTriangleShape}.
 */
public final class LowerTriangleCalculator extends AbstractCalculator
{
    private static LowerTriangleCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static LowerTriangleCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new LowerTriangleCalculator();
        }
        return instance;
    }


    private LowerTriangleCalculator()
    {
    }


    @Override
    protected Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        float yBound = Math.max(x + 1, 0);
        return IntStream.range((int)Math.ceil(yBound), h).iterator();
    }


    /**
     * Calculate average for lower triangle of a region.
     *
     * @param region the region
     * @return the average color as argb
     */
    public int averageColor(BufferedImage region)
    {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int ctr = 0;

        for (int x = 0; x < region.getWidth(); x++)
        {
            float yBound = Math.max(x + 1, 0);
            for (int y = region.getHeight() - 1; y >= yBound; y--)
            {
                int col = region.getRGB(x, y);

                Color c = new Color(col, true);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
                a += c.getAlpha();
                ctr++;
            }
        }
        return new Color((int)(r / ctr), (int)(g / ctr), (int)(b / ctr), (int)(a / ctr)).getRGB();
    }
}
