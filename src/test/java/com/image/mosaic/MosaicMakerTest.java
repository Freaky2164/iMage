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

import org.junit.Assert;
import org.junit.Test;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.repository.ImageRepository;
import com.image.domain.service.TileLoaderService;
import com.image.implementation.mosaic.MosaicMaker;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.crossed.CrossedRectangleArtist;
import com.image.implementation.mosaic.rectangle.RectangleArtist;
import com.image.implementation.mosaic.triangle.TriangleArtist;
import com.image.repository.TestFileSystemImageRepository;
import com.image.repository.TestTileLoader;


public class MosaicMakerTest
{
    @Test(expected = IllegalArgumentException.class)
    public void testMosaicCreationWithNullParameters()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        mosaicMaker.createMosaic(null, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMosaicCreationWithoutArtist()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        ImageAggregate inputImage = imageRepository.findAll().get(0);
        mosaicMaker.createMosaic(inputImage.getImage(), null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMosaicCreationWithoutInputImage()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        TileLoaderService tileLoaderService = new TestTileLoader();
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new RectangleArtist(tiles, 5, 5);
        mosaicMaker.createMosaic(null, mosaicArtist);
    }


    @Test
    public void testRectangleMosaicCreation()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate inputImage = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new RectangleArtist(tiles, 5, 5);
        BufferedImage mosaic = mosaicMaker.createMosaic(inputImage.getImage(), mosaicArtist);

        Assert.assertNotNull(mosaic);
        Assert.assertEquals(inputImage.getImage().getWidth(), mosaic.getWidth());
        Assert.assertEquals(inputImage.getImage().getHeight(), mosaic.getHeight());
    }


    @Test
    public void testTriangleMosaicCreation()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate inputImage = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new TriangleArtist(tiles, 5, 5);
        BufferedImage mosaic = mosaicMaker.createMosaic(inputImage.getImage(), mosaicArtist);

        Assert.assertNotNull(mosaic);
        Assert.assertEquals(inputImage.getImage().getWidth(), mosaic.getWidth());
        Assert.assertEquals(inputImage.getImage().getHeight(), mosaic.getHeight());
    }


    @Test
    public void testCrossedMosaicCreation()
    {
        MosaicMaker mosaicMaker = new MosaicMaker();
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate inputImage = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new CrossedRectangleArtist(tiles, 5, 5);
        BufferedImage mosaic = mosaicMaker.createMosaic(inputImage.getImage(), mosaicArtist);

        Assert.assertNotNull(mosaic);
        Assert.assertEquals(inputImage.getImage().getWidth(), mosaic.getWidth());
        Assert.assertEquals(inputImage.getImage().getHeight(), mosaic.getHeight());
    }
}
