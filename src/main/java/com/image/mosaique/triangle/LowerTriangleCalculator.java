package com.image.mosaique.triangle;


import java.util.Iterator;
import java.util.stream.IntStream;

import com.image.mosaique.AbstractCalculator;


/**
 * Helper class for the {@link TriangleArtist} and {@link LowerTriangleShape}.
 *
 * @author Dominik Fuchss
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
        float m = (1F * h) / w;
        float yBound = Math.max((x + 1) * m, 0);
        return IntStream.range((int)Math.ceil(yBound), h).iterator();
    }
}
