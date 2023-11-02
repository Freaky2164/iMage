/*
 * IMosaicShape.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.base;

public interface IMosaicShape
{
    int getAverageColor();

    void drawMe(BufferedArtImage targetRect);

    int getWidth();

    int getHeight();
}
