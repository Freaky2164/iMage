/*
 * LowerTriangleCalculatorTest.java
 *
 * created at 2023-10-25 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.triangle;


import org.junit.Test;


public class LowerTriangleCalculatorTest
{

    @Test
    public void test()
    {
        int width = 2;
        int height = 2;
        String[] actualsPixels = new String[4];
        for (int x = 0; x < width; x++)
        {
            var yIter = UpperTriangleCalculator.getInstance().getIteratorForColumn(width, height, x);
            while (yIter.hasNext())
            {
                int y = yIter.next();
                System.out.println(x + "," + y);
            }
        }
    }
}
