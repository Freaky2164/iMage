/*
 * MosaicController.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.image.app.repository.FileImage;
import com.image.app.repository.RepositoryHandler;
import com.image.app.window.main.AppFrame;
import com.image.app.window.mosaic.MosaicWindow;
import com.image.mosaic.AbstractArtist;
import com.image.mosaic.MosaicMaker;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.crossed.CrossedRectangleArtist;
import com.image.mosaic.rectangle.RectangleArtist;
import com.image.mosaic.triangle.TriangleArtist;


public class MosaicController
{
    public static final int IMAGE_SIZE = 200;

    private MosaicMaker mosaicMaker;
    private AppFrame mosaicWindow;

    public MosaicController()
    {
        this.mosaicMaker = new MosaicMaker();
        this.mosaicWindow = new AppFrame(this);
    }


    public void initialize()
    {
        List<FileImage> images = RepositoryHandler.getImages();
        mosaicWindow.initialize(images);
    }


    public void createMosaic(FileImage inputImage, int widthInput, int heightInput, String shape)
    {
        if (inputImage != null)
        {
            int tileWidth = widthInput;
            int tileHeight = heightInput;

            if (tileWidth <= 0 || tileHeight <= 0 || tileWidth > inputImage.getWidth() || tileHeight > inputImage.getHeight())
            {
                System.err.println("tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight);
            }

            List<BufferedArtImage> tiles = RepositoryHandler.loadTiles();
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

            BufferedImage outputImage = mosaicMaker.createMosaic(inputImage.getImage(), artist);
            RepositoryHandler.addMosaic(inputImage.getName(), shape + "_" + tileWidth + "x" + tileHeight + ".png", outputImage);
            new MosaicWindow(outputImage);
        }
        else
        {
            JOptionPane.showMessageDialog(mosaicWindow, "No image selected");
        }
    }


    public FileImage getSelectedImage()
    {
        return mosaicWindow.getImagePanel().getSelectedImage();
    }


    public void hasSelectedImage(FileImage fileImage)
    {
        mosaicWindow.getMetadataPanel().showMetadata(fileImage);
    }


    public void addImage(File file)
    {
        Path repositoryPath = RepositoryHandler.addImage(file);
        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            FileImage image = new FileImage(bufferedImage, file.getName(), repositoryPath.toString(), bufferedImage.getWidth(),
                                            bufferedImage.getHeight());
            mosaicWindow.getImagePanel().addImage(image);
        }
        catch (IOException e)
        {
            System.err.println("Could not load the images from the repository: " + e.getMessage());
        }
    }


    public void deleteImage(FileImage selectedImage)
    {
        RepositoryHandler.deleteImage(selectedImage);

        mosaicWindow.getImagePanel().deleteImage(selectedImage);
        mosaicWindow.getMetadataPanel().resetView();
    }


    public void deleteMosaic(FileImage mosaic)
    {
        RepositoryHandler.deleteImage(mosaic);
    }
}
