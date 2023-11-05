/*
 * IMosaicArtist.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.base;


import java.util.Collection;


public interface IMosaicArtist
{
    int getTileWidth();


    int getTileHeight();


    Collection<BufferedArtImage> getTiles();


    BufferedArtImage getTileForRegion(BufferedArtImage region);
}
