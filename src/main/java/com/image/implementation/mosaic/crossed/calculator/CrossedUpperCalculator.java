package com.image.implementation.mosaic.crossed.calculator;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.implementation.AbstractCalculator;
import com.image.implementation.AbstractShape;
import com.image.implementation.mosaic.crossed.CrossedArtist;


/**
 * Helper class for the {@link CrossedArtist} and {@link AbstractShape CrossedShapes}.
 *
 */
public final class CrossedUpperCalculator extends AbstractCalculator
{
    private static CrossedUpperCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static CrossedUpperCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new CrossedUpperCalculator();
        }
        return instance;
    }


    private CrossedUpperCalculator()
    {
    }


    @Override
    public Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        int yMax;
        if (x < w / 2)
        {
            yMax = (int)Math.floor(Math.max(x + 1, 0));
        }
        else
        {
            yMax = (int)Math.floor(Math.max((h - x), 0));
        }
        return IntStream.range(0, yMax).iterator();
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
