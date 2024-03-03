package com.image.implementation.mosaic.base;


import java.awt.image.BufferedImage;

/**
 * Interface for a mosaic shape
 */
public interface IMosaicShape
{
    int getAverageColor();


    void drawMe(BufferedImage targetRect);


    int getWidth();


    int getHeight();
}
