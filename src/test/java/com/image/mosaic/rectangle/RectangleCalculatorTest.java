/*
 * RectangleCalculatorTest.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.rectangle;


import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class RectangleCalculatorTest
{
    @Test
    public void testRectangleCalculatorWith1x1()
    {
        int width = 1;
        int height = 1;

        String[] expectedPixels = new String[] { "0,0" };
        List<String> actualPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var yIter = RectangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                actualPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedPixels, actualPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith2x2()
    {
        int width = 2;
        int height = 2;

        String[] expectedPixels = new String[] { "0,0", "0,1", "1,0", "1,1" };
        List<String> actualPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var yIter = RectangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                actualPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedPixels, actualPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith3x3()
    {
        int width = 3;
        int height = 3;

        String[] expectedPixels = new String[] { "0,0", "0,1", "0,2", "1,0", "1,1", "1,2", "2,0", "2,1", "2,2" };
        List<String> actualPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var yIter = RectangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                actualPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedPixels, actualPixels.toArray());
    }


    @Test
    public void testTriangleCalculatorWith4x4()
    {
        int width = 4;
        int height = 4;

        String[] expectedPixels = new String[] { "0,0", "0,1", "0,2", "0,3", "1,0", "1,1", "1,2", "1,3", "2,0", "2,1", "2,2", "2,3", "3,0",
                                                 "3,1", "3,2", "3,3" };
        List<String> actualPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var yIter = RectangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                actualPixels.add(x + "," + y);
            }
        }

        assertArrayEquals(expectedPixels, actualPixels.toArray());
    }
}
