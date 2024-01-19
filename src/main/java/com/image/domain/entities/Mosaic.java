/*
 * Mosaic.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.entities;


import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

import com.image.domain.value_objects.Tile;


public class Mosaic
{
    private UUID mosaicId;
    private UUID imageId;
    private BufferedImage image;
    private Tile tile;
    private String name;
    private String path;

    public Mosaic(UUID mosaicId, UUID imageId, BufferedImage image, Tile tile, String name)
    {
        Objects.requireNonNull(mosaicId);
        Objects.requireNonNull(imageId);
        Objects.requireNonNull(image);
        Objects.requireNonNull(tile);
        Validate.notBlank(name);
        this.mosaicId = mosaicId;
        this.imageId = imageId;
        this.image = image;
        this.tile = tile;
        this.name = name;
    }


    public UUID getMosaicId()
    {
        return mosaicId;
    }


    public UUID getImageId()
    {
        return imageId;
    }


    public BufferedImage getImage()
    {
        return image;
    }


    public Tile getTile()
    {
        return tile;
    }


    public String getName()
    {
        return name;
    }


    public String getPath()
    {
        return path;
    }


    public void moveTo(String path)
    {
        this.path = path;
    }
}
