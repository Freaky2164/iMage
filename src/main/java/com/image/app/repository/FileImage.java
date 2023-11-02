/*
 * FileImage.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.repository;


import java.awt.image.BufferedImage;
import java.util.List;


public class FileImage
{
    private BufferedImage image;
    private String name;
    private String path;
    private int width;
    private int height;
    private List<String> createdMosaiques;

    public FileImage(BufferedImage image, String name, String path, int width, int height)
    {
        this.image = image;
        this.name = name;
        this.path = path;
        this.width = width;
        this.height = height;
    }


    public BufferedImage getImage()
    {
        return image;
    }


    public String getName()
    {
        return name;
    }


    public String getPath()
    {
        return path;
    }


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    public List<String> getCreatedMosaiques()
    {
        return createdMosaiques;
    }
}
