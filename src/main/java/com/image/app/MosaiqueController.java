/*
 * MosaiqueController.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;


import java.awt.image.BufferedImage;
import java.util.List;

import com.image.app.repository.FileImage;
import com.image.app.repository.RepositoryHandler;
import com.image.app.window.main.MosaiqueFrame;
import com.image.app.window.mosaique.MosaiqueWindow;
import com.image.mosaique.AbstractArtist;
import com.image.mosaique.MosaiqueMaker;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.crossed.CrossedRectangleArtist;
import com.image.mosaique.rectangle.RectangleArtist;
import com.image.mosaique.triangle.TriangleArtist;


public class MosaiqueController
{
    private MosaiqueMaker mosaiqueMaker;
    private MosaiqueFrame mosaiqueWindow;

    public MosaiqueController()
    {
        this.mosaiqueMaker = new MosaiqueMaker();
        this.mosaiqueWindow = new MosaiqueFrame(this);
    }


    public void initialize()
    {
        List<FileImage> images = RepositoryHandler.getImages();
        mosaiqueWindow.initialize(images);
    }


    public void createMosaique(int widthInput, int heightInput, String shape)
    {
        System.out.println("Starting to create mosaic");

        BufferedImage inputImage = RepositoryHandler.loadInput("C:\\Users\\p.faller\\Documents\\GitHub\\iMage\\src\\main\\resources\\com\\image\\mosaique\\input\\Beispielbild1.jpg");
        List<BufferedArtImage> tiles = RepositoryHandler.loadTiles("C:\\Users\\p.faller\\Documents\\GitHub\\iMage\\src\\main\\resources\\com\\image\\mosaique\\tiles");

        System.out.println("Loaded input image and tiles");

        int tileWidth = widthInput;
        int tileHeight = heightInput;

        if (tileWidth <= 0 || tileHeight <= 0 || tileWidth > inputImage.getWidth() || tileHeight > inputImage.getHeight())
        {
            System.err.println("tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight);
            System.exit(1);
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
        BufferedImage outputImage = mosaiqueMaker.createMosaique(inputImage, artist);

        RepositoryHandler.writeOutput("C:\\Users\\p.faller\\Documents\\GitHub\\iMage\\src\\main\\resources\\com\\image\\mosaique\\output\\Mosaik.png",
                                      outputImage);

        System.out.println("Finished creating mosaic");

        new MosaiqueWindow().showMosaique(outputImage);
    }


    public FileImage getSelectedImage()
    {
        return mosaiqueWindow.getImagePanel().getSelectedImage();
    }


    public void hasSelectedImage(FileImage fileImage)
    {
        mosaiqueWindow.getMenuPanel().activateMosaiqueCreation();
        mosaiqueWindow.getMetadataPanel().showMetadata(fileImage);
    }


    public void updateImages()
    {
        mosaiqueWindow.getImagePanel().repaint();
    }
}
