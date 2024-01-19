/*
 * CrossedCalculatorTest.java
 *
 * created at 2023-10-25 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.crossed;


import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.image.implementation.mosaic.crossed.calculator.CrossedLeftCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedLowerCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedRightCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedUpperCalculator;


public class CrossedCalculatorTest
{
    @Test
    public void testCrossedCalculatorWith1x1()
    {
        int width = 1;
        int height = 1;

        String[] expectedLeftPixels = new String[] { "0,0" };
        String[] expectedRightPixels = new String[] { "0,0" };
        String[] expectedUpperPixels = new String[] { "0,0" };
        String[] expectedLowerPixels = new String[] { "0,0" };

        List<String> actualLeftPixels = new ArrayList<>();
        List<String> actualRightPixels = new ArrayList<>();
        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var rightYIter = CrossedRightCalculator.getInstance().getIteratorForColumn(width, height, x);
            var leftYIter = CrossedLeftCalculator.getInstance().getIteratorForColumn(width, height, x);
            var upperYIter = CrossedUpperCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = CrossedLowerCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (rightYIter.hasNext())
            {
                int y = rightYIter.next();
                actualRightPixels.add(x + "," + y);
            }
            while (leftYIter.hasNext())
            {
                int y = leftYIter.next();
                actualLeftPixels.add(x + "," + y);
            }
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

        assertArrayEquals(expectedRightPixels, actualRightPixels.toArray());
        assertArrayEquals(expectedLeftPixels, actualLeftPixels.toArray());
        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testCrossedCalculatorWith2x2()
    {
        int width = 2;
        int height = 2;

        String[] expectedLeftPixels = new String[] { "0,0", "0,1" };
        String[] expectedRightPixels = new String[] { "1,0", "1,1" };
        String[] expectedUpperPixels = new String[] { "0,0", "1,0" };
        String[] expectedLowerPixels = new String[] { "0,1", "1,1" };

        List<String> actualLeftPixels = new ArrayList<>();
        List<String> actualRightPixels = new ArrayList<>();
        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var rightYIter = CrossedRightCalculator.getInstance().getIteratorForColumn(width, height, x);
            var leftYIter = CrossedLeftCalculator.getInstance().getIteratorForColumn(width, height, x);
            var upperYIter = CrossedUpperCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = CrossedLowerCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (rightYIter.hasNext())
            {
                int y = rightYIter.next();
                actualRightPixels.add(x + "," + y);
            }
            while (leftYIter.hasNext())
            {
                int y = leftYIter.next();
                actualLeftPixels.add(x + "," + y);
            }
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

        assertArrayEquals(expectedRightPixels, actualRightPixels.toArray());
        assertArrayEquals(expectedLeftPixels, actualLeftPixels.toArray());
        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testCrossedCalculatorWith3x3()
    {
        int width = 3;
        int height = 3;

        String[] expectedLeftPixels = new String[] { "0,0", "0,1", "0,2", "1,1" };
        String[] expectedRightPixels = new String[] { "1,1", "2,0", "2,1", "2,2" };
        String[] expectedUpperPixels = new String[] { "0,0", "1,0", "1,1", "2,0" };
        String[] expectedLowerPixels = new String[] { "0,2", "1,1", "1,2", "2,2" };

        List<String> actualLeftPixels = new ArrayList<>();
        List<String> actualRightPixels = new ArrayList<>();
        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var rightYIter = CrossedRightCalculator.getInstance().getIteratorForColumn(width, height, x);
            var leftYIter = CrossedLeftCalculator.getInstance().getIteratorForColumn(width, height, x);
            var upperYIter = CrossedUpperCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = CrossedLowerCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (rightYIter.hasNext())
            {
                int y = rightYIter.next();
                actualRightPixels.add(x + "," + y);
            }
            while (leftYIter.hasNext())
            {
                int y = leftYIter.next();
                actualLeftPixels.add(x + "," + y);
            }
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

        assertArrayEquals(expectedRightPixels, actualRightPixels.toArray());
        assertArrayEquals(expectedLeftPixels, actualLeftPixels.toArray());
        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }


    @Test
    public void testCrossedCalculatorWith4x4()
    {
        int width = 4;
        int height = 4;

        String[] expectedLeftPixels = new String[] { "0,0", "0,1", "0,2", "0,3", "1,1", "1,2" };
        String[] expectedRightPixels = new String[] { "2,1", "2,2", "3,0", "3,1", "3,2", "3,3" };
        String[] expectedUpperPixels = new String[] { "0,0", "1,0", "1,1", "2,0", "2,1", "3,0" };
        String[] expectedLowerPixels = new String[] { "0,3", "1,2", "1,3", "2,2", "2,3", "3,3" };

        List<String> actualLeftPixels = new ArrayList<>();
        List<String> actualRightPixels = new ArrayList<>();
        List<String> actualUpperPixels = new ArrayList<>();
        List<String> actualLowerPixels = new ArrayList<>();

        for (int x = 0; x < width; x++)
        {
            var rightYIter = CrossedRightCalculator.getInstance().getIteratorForColumn(width, height, x);
            var leftYIter = CrossedLeftCalculator.getInstance().getIteratorForColumn(width, height, x);
            var upperYIter = CrossedUpperCalculator.getInstance().getIteratorForColumn(width, height, x);
            var lowerYIter = CrossedLowerCalculator.getInstance().getIteratorForColumn(width, height, x);

            while (rightYIter.hasNext())
            {
                int y = rightYIter.next();
                actualRightPixels.add(x + "," + y);
            }
            while (leftYIter.hasNext())
            {
                int y = leftYIter.next();
                actualLeftPixels.add(x + "," + y);
            }
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

        assertArrayEquals(expectedRightPixels, actualRightPixels.toArray());
        assertArrayEquals(expectedLeftPixels, actualLeftPixels.toArray());
        assertArrayEquals(expectedUpperPixels, actualUpperPixels.toArray());
        assertArrayEquals(expectedLowerPixels, actualLowerPixels.toArray());
    }
}
