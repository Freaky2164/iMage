/*
 * FileSystemImageRepository.java
 *
 * created at 2024-01-16 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.implementation.repository;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.entities.Mosaic;
import com.image.domain.repository.ImageRepository;
import com.image.domain.value_objects.Tile;
import com.image.implementation.mosaic.base.TileShape;


public class FileSystemImageRepository implements ImageRepository
{
    protected String imagePath;
    protected String mosaicPath;

    public FileSystemImageRepository()
    {
        this.imagePath = "src\\main\\resources\\com\\image\\mosaic\\repository\\images\\";
        this.mosaicPath = "src\\main\\resources\\com\\image\\mosaic\\repository\\mosaics\\";
    }


    @Override
    public Optional<ImageAggregate> findById(UUID imageId)
    {
        ImageAggregate imageAggregate = null;
        try
        {
            File directory = new File(imagePath + imageId.toString());
            FileFilter isImage = f -> f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg") || f.getName().endsWith(".png");
            File[] listFiles = directory.listFiles(isImage);
            if (listFiles.length == 1)
            {
                File imageFile = listFiles[0];
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                imageAggregate = new ImageAggregate(imageId, bufferedImage, imageFile.getName(), imageFile.getAbsolutePath());
                findAll(imageId).forEach(imageAggregate::addMosaic);
            }
        }
        catch (IOException e)
        {
            System.err.println("Could not load the image from the repository: " + e.getMessage());
        }
        return Optional.ofNullable(imageAggregate);
    }


    @Override
    public List<ImageAggregate> findAll()
    {
        List<ImageAggregate> imagesList = new ArrayList<>();
        try
        {
            File directory = new File(imagePath);
            File[] images = directory.listFiles();
            if (images != null)
            {
                for (File imageDir : images)
                {
                    FileFilter isImage = f -> f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg") || f.getName().endsWith(".png");
                    File[] listFiles = imageDir.listFiles(isImage);
                    if (listFiles.length == 1)
                    {
                        File imageFile = listFiles[0];
                        BufferedImage bufferedImage = ImageIO.read(imageFile);
                        ImageAggregate imageAggregate = new ImageAggregate(UUID.fromString(imageDir.getName()), bufferedImage, imageFile
                                                                                                                                        .getName(),
                                                                           imageFile.getAbsolutePath());
                        findAll(UUID.fromString(imageDir.getName())).forEach(imageAggregate::addMosaic);
                        imagesList.add(imageAggregate);
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Could not load the images from the repository: " + e.getMessage());
        }
        return imagesList;
    }


    @Override
    public ImageAggregate save(ImageAggregate image)
    {
        try
        {
            File imageDir = ensureDir(imagePath + image.getImageId(), true);
            Files.copy(Path.of(image.getPath()), Path.of(imageDir.getPath(), image.getName()));
        }
        catch (IOException e)
        {
            System.err.println("Could not save the image to the repository: " + e.getMessage());
        }
        return image;
    }


    @Override
    public void delete(UUID imageId)
    {
        File imageDir = new File(imagePath + imageId.toString());
        for (File image : imageDir.listFiles())
        {
            image.delete();
        }
        imageDir.delete();
    }


    @Override
    public List<Mosaic> findAll(UUID imageId)
    {
        if (imageId != null)
        {
            List<Mosaic> mosaicList = new ArrayList<>();
            try
            {
                File directory = new File(mosaicPath + imageId.toString());
                File[] mosaicDirs = directory.listFiles();
                if (mosaicDirs != null)
                {
                    for (File mosaics : mosaicDirs)
                    {
                        FileFilter isImage = f -> f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg") || f.getName().endsWith(
                                                                                                                                        ".png");
                        File[] mosaicsDir = mosaics.listFiles(isImage);
                        if (mosaicsDir.length == 1)
                        {
                            File mosaicFile = mosaicsDir[0];
                            String[] mosaicInformation = mosaicFile.getName().split("_");
                            String tileShape = mosaicInformation[0];
                            int tileWidth = Integer.parseInt(mosaicInformation[1].substring(0, 1));
                            int tileHeight = Integer.parseInt(mosaicInformation[1].substring(2, 3));
                            Tile tile = new Tile(getTileShapeFrom(tileShape), tileWidth, tileHeight);
                            UUID mosaicId = UUID.fromString(mosaics.getName());

                            BufferedImage bufferedImage = ImageIO.read(mosaicFile);
                            Mosaic mosaic = new Mosaic(mosaicId, imageId, bufferedImage, tile, mosaicFile.getName());
                            mosaic.moveTo(mosaicFile.getPath());
                            mosaicList.add(mosaic);
                        }
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println("Could not load the images from the repository: " + e.getMessage());
            }
            return mosaicList;
        }
        return Collections.emptyList();
    }


    @Override
    public Mosaic save(Mosaic mosaic)
    {
        try
        {
            File imageDir = ensureDir(mosaicPath + mosaic.getImageId(), true);
            File mosaicDir = ensureDir(imageDir.getPath() + "//" + mosaic.getMosaicId(), true);
            File mosaicFile = ensureFile(mosaicDir.getPath() + "//" + mosaic.getTile() + "_" + mosaic.getName(), true);
            ImageIO.write(mosaic.getImage(), "png", mosaicFile);
            mosaic.moveTo(mosaicFile.getPath());
        }
        catch (IOException e)
        {
            System.err.println("Could not save mosaic: " + e.getMessage());
        }
        return mosaic;
    }


    @Override
    public void delete(UUID imageId, UUID mosaicId)
    {
        File mosaicDir = new File(mosaicPath + imageId.toString() + "//" + mosaicId.toString());
        for (File mosaic : mosaicDir.listFiles())
        {
            mosaic.delete();
        }
        mosaicDir.delete();
    }


    /**
     * Ensure that a directory exists (or create if allowed by parameter).
     *
     * @param path   the path to the file
     * @param create indicates whether creation is allowed
     * @return the file
     * @throws IOException if something went wrong
     */
    private static File ensureDir(String path, boolean create)
        throws IOException
    {
        File file = new File(path);
        if (file.exists())
        {
            return file;
        }
        if (create)
        {
            file.mkdir();
            return file;
        }
        throw new IOException("The specified directory does not exist: " + path);
    }


    /**
     * Ensure that a file exists (or create if allowed by parameter).
     *
     * @param path   the path to the file
     * @param create indicates whether creation is allowed
     * @return the file
     * @throws IOException if something went wrong
     */
    private static File ensureFile(String path, boolean create)
        throws IOException
    {
        File file = new File(path);
        if (file.exists())
        {
            return file;
        }
        if (create)
        {
            file.createNewFile();
            return file;
        }
        throw new IOException("The specified file does not exist: " + path);
    }


    private TileShape getTileShapeFrom(String shape)
    {
        if ("Rectangle".equals(shape))
        {
            return TileShape.RECTANGLE;
        }
        if ("Triangle".equals(shape))
        {
            return TileShape.TRIANGLE;
        }
        if ("Crossed".contentEquals(shape))
        {
            return TileShape.CROSSED;
        }
        return null;
    }
}
