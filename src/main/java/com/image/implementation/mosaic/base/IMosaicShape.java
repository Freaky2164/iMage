/*
 * IMosaicShape.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.implementation.mosaic.base;


import java.awt.image.BufferedImage;


public interface IMosaicShape
{
    int getAverageColor();


    void drawMe(BufferedImage targetRect);


    int getWidth();


    int getHeight();
}
