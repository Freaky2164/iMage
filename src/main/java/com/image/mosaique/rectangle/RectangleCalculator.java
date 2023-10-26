package com.image.mosaique.rectangle;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.mosaique.AbstractCalculator;


/**
 * Helper class for the {@link RectangleArtist} and {@link RectangleShape}.
 *
 * @author Dominik Fuchss
 *
 */
public final class RectangleCalculator extends AbstractCalculator
{
    private static RectangleCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static RectangleCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new RectangleCalculator();
        }
        return instance;
    }


    private RectangleCalculator()
    {
    }


    @Override
    protected Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        return IntStream.range(0, h).iterator();
    }


    @Override
    public final int averageColor(BufferedImage region)
    {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int ctr = 0;

        for (int x = 0; x < region.getWidth(); x++)
        {
            Iterator<Integer> yIterator = getIteratorForColumn(region.getWidth(), region.getHeight(), x);
            while (yIterator.hasNext())
            {
                int y = yIterator.next();
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
