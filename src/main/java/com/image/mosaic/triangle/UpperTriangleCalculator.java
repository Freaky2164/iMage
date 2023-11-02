package com.image.mosaic.triangle;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.mosaic.AbstractCalculator;


/**
 * Helper class for the {@link TriangleArtist} and {@link UpperTriangleShape}.
 */
public final class UpperTriangleCalculator extends AbstractCalculator
{
    private static UpperTriangleCalculator instance;

    /**
     * Get the one and only instance of the calculator.
     *
     * @return the instance
     */
    public static UpperTriangleCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new UpperTriangleCalculator();
        }
        return instance;
    }


    private UpperTriangleCalculator()
    {
    }


    @Override
    protected Iterator<Integer> getIteratorForColumn(int w, int h, int x)
    {
        float yBound = Math.min(x + 1, h);
        return IntStream.range(0, (int)Math.floor(yBound)).iterator();
    }


    public int averageColor(BufferedImage region)
    {
        long r = 0;
        long g = 0;
        long b = 0;
        long a = 0;
        int sum = 0;

        for (int x = 0; x < region.getWidth(); x++)
        {
            float yBound = Math.min(x + 1, region.getHeight());
            for (int y = 0; y < yBound; y++)
            {
                int pixelRGB = region.getRGB(x, y);

                Color c = new Color(pixelRGB, true);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
                a += c.getAlpha();
                sum++;
            }
        }
        return new Color((int)(r / sum), (int)(g / sum), (int)(b / sum), (int)(a / sum)).getRGB();
    }
}
