/*
 * TriangleCalculatorTest.java
 *
 * created at 2023-10-25 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.triangle;


import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.image.implementation.mosaic.triangle.LowerTriangleCalculator;
import com.image.implementation.mosaic.triangle.UpperTriangleCalculator;


public class TriangleCalculatorTest
{
    @Test
    public void testTriangleCalculatorWith1x1()
    {
        int width = 1;
        int height = 1;

        String[] expectedUpperPixels = new String[] { "0,0" };
        String[] expectedLowerPixels = new String[] {};

        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var upperYIter = UpperTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = LowerTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (upperYIter.hasNext())
            {
                int y = upperYIter.next();
                actualUpperPixels.add(x + "," + y);
            }
            while (lowerYIter.hasNext())
            {
                int y = lowerYIter.next();
                actualLowerPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith2x2()
    {
        int width = 2;
        int height = 2;

        String[] expectedUpperPixels = new String[] { "0,0", "1,0", "1,1" };
        String[] expectedLowerPixels = new String[] { "0,1" };

        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var upperYIter = UpperTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = LowerTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (upperYIter.hasNext())
            {
                int y = upperYIter.next();
                actualUpperPixels.add(x + "," + y);
            }
            while (lowerYIter.hasNext())
            {
                int y = lowerYIter.next();
                actualLowerPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith3x3()
    {
        int width = 3;
        int height = 3;

        String[] expectedUpperPixels = new String[] { "0,0", "1,0", "1,1", "2,0", "2,1", "2,2" };
        String[] expectedLowerPixels = new String[] { "0,1", "0,2", "1,2" };

        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var upperYIter = UpperTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = LowerTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (upperYIter.hasNext())
            {
                int y = upperYIter.next();
                actualUpperPixels.add(x + "," + y);
            }
            while (lowerYIter.hasNext())
            {
                int y = lowerYIter.next();
                actualLowerPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith4x4()
    {
        int width = 4;
        int height = 4;

        String[] expectedUpperPixels = new String[] { "0,0", "1,0", "1,1", "2,0", "2,1", "2,2", "3,0", "3,1", "3,2", "3,3" };
        String[] expectedLowerPixels = new String[] { "0,1", "0,2", "0,3", "1,2", "1,3", "2,3" };

        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var upperYIter = UpperTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = LowerTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (upperYIter.hasNext())
            {
                int y = upperYIter.next();
                actualUpperPixels.add(x + "," + y);
            }
            while (lowerYIter.hasNext())
            {
                int y = lowerYIter.next();
                actualLowerPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }
}
