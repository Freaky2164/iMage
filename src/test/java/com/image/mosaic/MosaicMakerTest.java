/*
 * MosaicMakerTest.java
 *
 * created at 2023-11-06 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic;


import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.repository.ImageRepository;
import com.image.domain.service.TileLoaderService;
import com.image.implementation.MosaicMaker;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.crossed.CrossedArtist;
import com.image.implementation.mosaic.rectangle.RectangleArtist;
import com.image.implementation.mosaic.triangle.TriangleArtist;
import com.image.repository.TestFileSystemImageRepository;
import com.image.repository.TestTileLoader;


@ExtendWith(MockitoExtension.class)
public class MosaicMakerTest
{
    @Mock
    MosaicMaker mosaicMaker;

    @Test
    public void testMosaicCreationWithNullParameters()
    {
        doThrow(IllegalArgumentException.class).when(mosaicMaker).createMosaic(null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> mosaicMaker.createMosaic(null, null));
    }


    @Test
    public void testMosaicCreationWithoutArtist()
    {
        ImageAggregate imageAggregate = mock(ImageAggregate.class);
        doThrow(IllegalArgumentException.class).when(mosaicMaker).createMosaic(imageAggregate, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> mosaicMaker.createMosaic(imageAggregate, null));
    }


    @Test
    public void testMosaicCreationWithoutInputImage()
    {
        IMosaicArtist mosaicArtist = mock(IMosaicArtist.class);
        doThrow(IllegalArgumentException.class).when(mosaicMaker).createMosaic(null, mosaicArtist);

        Assertions.assertThrows(IllegalArgumentException.class, () -> mosaicMaker.createMosaic(null, mosaicArtist));
    }


    @Test
    public void testRectangleMosaicCreation()
    {
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate imageAggregate = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new RectangleArtist(tiles, 5, 5);
        mosaicMaker.createMosaic(imageAggregate, mosaicArtist);
        mosaicMaker.addMosaicCreationListener(mosaic ->
        {
            Assertions.assertNotNull(mosaic);
            Assertions.assertEquals("Rectangle_5x5", mosaic.getTile());
            Assertions.assertEquals(imageAggregate.getImage().getWidth(), mosaic.getImage().getWidth());
            Assertions.assertEquals(imageAggregate.getImage().getHeight(), mosaic.getImage().getWidth());
        });
    }


    @Test
    public void testTriangleMosaicCreation()
    {
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate imageAggregate = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new TriangleArtist(tiles, 5, 5);
        mosaicMaker.createMosaic(imageAggregate, mosaicArtist);
        mosaicMaker.addMosaicCreationListener(mosaic ->
        {
            Assertions.assertNotNull(mosaic);
            Assertions.assertEquals("Triangle_5x5", mosaic.getTile());
            Assertions.assertEquals(imageAggregate.getImage().getWidth(), mosaic.getImage().getWidth());
            Assertions.assertEquals(imageAggregate.getImage().getHeight(), mosaic.getImage().getWidth());
        });
    }


    @Test
    public void testCrossedMosaicCreation()
    {
        ImageRepository imageRepository = new TestFileSystemImageRepository();
        TileLoaderService tileLoaderService = new TestTileLoader();
        ImageAggregate imageAggregate = imageRepository.findAll().get(0);
        List<BufferedImage> tiles = tileLoaderService.loadTiles();
        IMosaicArtist mosaicArtist = new CrossedArtist(tiles, 5, 5);
        mosaicMaker.createMosaic(imageAggregate, mosaicArtist);
        mosaicMaker.addMosaicCreationListener(mosaic ->
        {
            Assertions.assertNotNull(mosaic);
            Assertions.assertEquals("Crossed_5x5", mosaic.getTile());
            Assertions.assertEquals(imageAggregate.getImage().getWidth(), mosaic.getImage().getWidth());
            Assertions.assertEquals(imageAggregate.getImage().getHeight(), mosaic.getImage().getWidth());
        });
    }
}
