/*
 * UpperCalculatorTest.java
 *
 * created at 2023-10-25 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaique.crossed;


import org.junit.Test;

import com.image.mosaique.crossed.calculator.UpperCalculator;


public class CrossedCalculatorTest
{
    @Test
    public void testUpperCalculatorWith9Pixel()
    {
        int width = 3;
        int height = 3;
        String[] actualsPixels = new String[4];
        for (int x = 0; x < width; x++)
        {
            var yIter = UpperCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                actualsPixels[x] = x + "," + y;
            }
        }
    }
}
