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
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.entities.Mosaic;
import com.image.domain.exception.MosaicCreationException;
import com.image.domain.repository.ImageRepository;
import com.image.frontend.listener.MosaicCreationListener;
import com.image.frontend.window.main.AppFrame;
import com.image.frontend.window.mosaic.ViewMosaicWindow;
import com.image.implementation.mosaic.AbstractArtist;
import com.image.implementation.mosaic.MosaicMaker;
import com.image.implementation.mosaic.crossed.CrossedArtist;
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
        return imageRepository.findById(appFrame.getImagePanel().getSelectedImageId()).orElseThrow();
    }


    public void hasSelectedImage(UUID imageId)
    {
        imageRepository.findById(imageId).ifPresent(imageAggregate -> appFrame.getMetadataPanel().showMetadata(imageAggregate));
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

        appFrame.getImagePanel().deleteImage(selectedImage.getImageId());
        appFrame.getMetadataPanel().resetView();
    }


    public void createMosaic(ImageAggregate imageAggregate, String shape, int widthInput, int heightInput)
    {
        if (imageAggregate != null)
        {
            try
            {
                int tileWidth = widthInput;
                int tileHeight = heightInput;
                BufferedImage inputImage = imageAggregate.getImage();

                if (tileWidth > inputImage.getWidth() || tileHeight > inputImage.getHeight())
                {
                    System.err.println("tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight);
                    JOptionPane.showMessageDialog(appFrame, "tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight,
                                                  "Mosaic creation error", JOptionPane.ERROR_MESSAGE);
                }

                AbstractArtist artist = createArtist(shape, tileWidth, tileHeight);
                Executors.newSingleThreadExecutor().execute(() -> mosaicMaker.createMosaic(imageAggregate, artist));
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(appFrame, "Creation of the mosaic failed because of: \n\r" + e.getMessage(),
                                              "Mosaic creation error", JOptionPane.ERROR_MESSAGE);
                throw new MosaicCreationException("Could not create mosaic: " + e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(appFrame, "No image selected");
        }
    }


    private AbstractArtist createArtist(String shape, int tileWidth, int tileHeight)
    {
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
            artist = new CrossedArtist(tiles, tileWidth, tileHeight);
        }
        return artist;
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
    public void mosaicCreated(Mosaic mosaic)
    {
        imageRepository.save(mosaic);
        imageRepository.findById(mosaic.getImageId()).ifPresent(imageAggregate -> appFrame.getMetadataPanel().showMetadata(imageAggregate));
        new ViewMosaicWindow(mosaic.getImage());
    }
}
