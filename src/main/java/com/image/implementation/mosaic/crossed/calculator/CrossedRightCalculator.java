package com.image.implementation.mosaic.crossed.calculator;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.implementation.mosaic.AbstractCalculator;
import com.image.implementation.mosaic.AbstractShape;
import com.image.implementation.mosaic.crossed.CrossedArtist;


/**
 * Helper class for the {@link CrossedArtist} and {@link AbstractShape CrossedShapes}.
 *
 */
public final class CrossedRightCalculator extends AbstractCalculator
{
    private static CrossedRightCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static CrossedRightCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new CrossedRightCalculator();
        }
        return instance;
    }


    private CrossedRightCalculator()
    {
    }


    @Override
    public Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        int yMin = (int)Math.floor(Math.max((h - x - 1), 0));
        int yMax = (int)Math.ceil(Math.max(x + 1, 0));
        return IntStream.range(yMin, yMax).iterator();
    }


    @Override
    public int averageColor(BufferedImage region)
    {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int ctr = 0;

        for (int x = 0; x < region.getWidth(); x++)
        {
            var yIter = getIteratorForColumn(region.getWidth(), region.getHeight(), x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
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
