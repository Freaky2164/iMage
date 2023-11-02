package com.image.mosaic.crossed.calculator;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.mosaic.AbstractCalculator;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.crossed.CrossedRectangleArtist;


/**
 * Helper class for the {@link CrossedRectangleArtist} and {@link AbstractShape CrossedShapes}.
 *
 * @author Dominik Fuchss
 *
 */
public final class LowerCalculator extends AbstractCalculator
{
    private static LowerCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static LowerCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new LowerCalculator();
        }
        return instance;
    }


    private LowerCalculator()
    {
    }


    @Override
    protected Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        int yMin;
        if (x < w / 2)
        {
            yMin = (int)Math.ceil(Math.max((h - x - 1), 0));
        }
        else
        {
            yMin = (int)Math.ceil(Math.max(x, 0));
        }
        return IntStream.range(yMin, h).iterator();
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
