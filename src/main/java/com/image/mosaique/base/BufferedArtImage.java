/*
 * BufferedArtImage.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaique.base;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class BufferedArtImage
{
    private BufferedImage image;
    private int width;
    private int height;

    public BufferedArtImage(int width, int height)
    {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }


    public BufferedArtImage(BufferedImage input)
    {
        this.image = input;
        this.width = input.getWidth();
        this.height = input.getHeight();
    }


    public BufferedArtImage createBlankImage()
    {
        return new BufferedArtImage(width, height);
    }


    public BufferedImage toBufferedImage()
    {
        return image;
    }


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    public BufferedArtImage getSubimage(int x, int y, int width, int height)
    {
        BufferedImage subimage = image.getSubimage(x, y, width, height);
        return new BufferedArtImage(subimage);
    }


    public void setSubimage(int x, int y, BufferedArtImage tile)
    {
        Graphics2D graphics2d = this.toBufferedImage().createGraphics();
        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        graphics2d.drawImage(tile.toBufferedImage(), x, y, null);
        graphics2d.dispose();
    }


    public void setRGB(int x, int y, int rgb)
    {
        this.image.setRGB(x, y, rgb);
    }
}
