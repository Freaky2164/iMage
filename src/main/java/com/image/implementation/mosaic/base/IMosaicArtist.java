/*
 * IMosaicArtist.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.implementation.mosaic.base;


import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * Interface for an artist working on a mosaic
 */
public interface IMosaicArtist
{
    int getTileWidth();


    int getTileHeight();


    Collection<BufferedImage> getTiles();


    BufferedImage getTileForRegion(BufferedImage region);

    TileShape getShape();
}
