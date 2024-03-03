/*
 * ImageUtils.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.implementation.mosaic.base;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Utility class providing functionality for scaling images to given size
 */
public class ImageUtils
{
    public static BufferedImage scale(BufferedImage originalImage, int targetWidth, int targetHeight)
    {
        BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = scaledImage.createGraphics();
        graphics2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2d.dispose();
        return scaledImage;
    }
}
