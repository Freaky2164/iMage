/*
 * MosaicController.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.service;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.entities.Mosaic;
import com.image.domain.repository.ImageRepository;
import com.image.domain.value_objects.Tile;
import com.image.frontend.listener.MosaicCreationListener;
import com.image.frontend.window.main.AppFrame;
import com.image.frontend.window.mosaic.MosaicWindow;
import com.image.implementation.mosaic.AbstractArtist;
import com.image.implementation.mosaic.MosaicMaker;
import com.image.implementation.mosaic.crossed.CrossedRectangleArtist;
import com.image.implementation.mosaic.rectangle.RectangleArtist;
import com.image.implementation.mosaic.triangle.TriangleArtist;
import com.image.implementation.repository.FileSystemImageRepository;
import com.image.implementation.repository.TileLoader;


public class MosaicController implements MosaicCreationListener
{
    private MosaicMaker mosaicMaker;
    private AppFrame appFrame;
    private ImageRepository imageRepository;
    private TileLoaderService tileLoaderService;
    private List<BufferedImage> tiles;

    public MosaicController()
    {
        mosaicMaker = new MosaicMaker();
        appFrame = new AppFrame(this);
        imageRepository = new FileSystemImageRepository();
        tileLoaderService = new TileLoader();
    }


    public void initialize()
    {
        tiles = tileLoaderService.loadTiles();
        List<ImageAggregate> images = imageRepository.findAll();
        appFrame.initialize(images);
        mosaicMaker.addMosaicCreationListener(this::mosaicCreated);
    }


    public ImageAggregate getSelectedImage()
    {
        return appFrame.getImagePanel().getSelectedImage();
    }


    public void hasSelectedImage(ImageAggregate imageAggregate)
    {
        appFrame.getMetadataPanel().showMetadata(imageAggregate);
    }


    public void addImage(File file)
    {
        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageAggregate imageAggregate = new ImageAggregate(UUID.randomUUID(), bufferedImage, file.getName(), file.getPath());
            imageRepository.save(imageAggregate);
            appFrame.getImagePanel().addImage(imageAggregate);
        }
        catch (IOException e)
        {
            System.err.println("Could not load the images from the repository: " + e.getMessage());
        }
    }


    public void deleteImage(ImageAggregate selectedImage)
    {
        selectedImage.getMosaics().forEach(mosaic -> imageRepository.delete(selectedImage.getImageId(), mosaic.getMosaicId()));
        imageRepository.delete(selectedImage.getImageId());

        appFrame.getImagePanel().deleteImage(selectedImage);
        appFrame.getMetadataPanel().resetView();
    }


    public void createMosaic(ImageAggregate imageAggregate, String shape, int widthInput, int heightInput)
    {
        if (imageAggregate != null)
        {
            int tileWidth = widthInput;
            int tileHeight = heightInput;
            BufferedImage inputImage = imageAggregate.getImage();

            if (tileWidth <= 0 || tileHeight <= 0 || tileWidth > inputImage.getWidth() || tileHeight > inputImage.getHeight())
            {
                System.err.println("tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight);
            }

            AbstractArtist artist = null;
            if (shape.equals("Rectangle"))
            {
                artist = new RectangleArtist(tiles, tileWidth, tileHeight);
            }
            else if (shape.equals("Triangle"))
            {
                artist = new TriangleArtist(tiles, tileWidth, tileHeight);
            }
            else
            {
                artist = new CrossedRectangleArtist(tiles, tileWidth, tileHeight);
            }
            BufferedImage outputImage = mosaicMaker.createMosaic(inputImage, artist);

            Mosaic mosaic = new Mosaic(UUID.randomUUID(), imageAggregate.getImageId(), outputImage,
                                       new Tile(shape, widthInput, heightInput), imageAggregate.getName());
            imageRepository.save(mosaic);
            getSelectedImage().addMosaic(mosaic);

            appFrame.getMetadataPanel().showMetadata(imageAggregate);
        }
        else
        {
            JOptionPane.showMessageDialog(appFrame, "No image selected");
        }
    }


    public void deleteMosaic(Mosaic selectedMosaic)
    {
        if (selectedMosaic != null)
        {
            imageRepository.findById(selectedMosaic.getImageId()).ifPresent(imageAggregate ->
            {
                imageAggregate.deleteMosaic(selectedMosaic.getMosaicId());
                appFrame.getMetadataPanel().showMetadata(imageAggregate);
            });
            imageRepository.delete(selectedMosaic.getImageId(), selectedMosaic.getMosaicId());
        }
    }


    public List<Mosaic> getMosaics()
    {
        ImageAggregate selectedImage = getSelectedImage();
        if (selectedImage != null)
        {
            return imageRepository.findAll(getSelectedImage().getImageId());
        }
        return Collections.emptyList();
    }


    @Override
    public void mosaicCreated(BufferedImage resultImage)
    {
        new MosaicWindow(resultImage);
    }
}