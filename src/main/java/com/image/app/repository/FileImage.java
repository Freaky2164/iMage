/*
 * FileImage.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.repository;


import java.awt.Image;
import java.util.List;


public class FileImage
{
    private Image image;
    private String path;
    private int width;
    private int height;
    private List<String> createdMosaiques;

    public FileImage(Image image, String path, int width, int height)
    {
        this.image = image;
        this.path = path;
        this.width = width;
        this.height = height;
    }


    public Image getImage()
    {
        return image;
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
