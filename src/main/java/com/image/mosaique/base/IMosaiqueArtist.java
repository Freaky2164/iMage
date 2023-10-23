/*
 * IMosaiqueArtist.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaique.base;

public interface IMosaiqueArtist
{

    int getTileWidth();


    int getTileHeight();


    BufferedArtImage getTileForRegion(BufferedArtImage region);
}
