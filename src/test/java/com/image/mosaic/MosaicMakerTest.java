/*
 * MosaicMakerTest.java
 *
 * created at 2023-11-06 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic;


import java.awt.image.BufferedImage;
import java.util.List;

import org.junit.Test;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.repository.ImageRepository;
import com.image.domain.service.TileLoaderService;
import com.image.implementation.mosaic.MosaicMaker;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.rectangle.RectangleArtist;
import com.image.implementation.repository.FileSystemImageRepository;
import com.image.implementation.repository.TileLoader;


public class MosaicMakerTest
{
    @Test(expected = NullPointerException.class)
    public void testMosaicCreationWithNullParameters()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        mosaicMaker.createMosaic(null, null);
    }


    @Test(expected = NullPointerException.class)
    public void testMosaicCreationWithoutArtist()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        ImageRepository imageRepository = new FileSystemImageRepository();
        ImageAggregate inputImage = imageRepository.findAll().get(0);
        mosaicMaker.createMosaic(inputImage.getImage(), null);
    }


    @Test(expected = NullPointerException.class)
    public void testMosaicCreationWithoutInputImage()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        TileLoaderService tileLoaderService = new TileLoader();
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new RectangleArtist(tiles, 5, 5);
        mosaicMaker.createMosaic(null, mosaicArtist);
    }
}
