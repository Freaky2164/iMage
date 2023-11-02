/*
 * RepositoryHandler.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.repository;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import com.image.mosaic.base.BufferedArtImage;


public class RepositoryHandler
{
    private static final String IMAGES_PATH = "src\\main\\resources\\com\\image\\mosaic\\repository\\images\\";
    private static final String TILES_PATH = "src\\main\\resources\\com\\image\\mosaic\\tiles";
    private static final String MOSAIC_PATH = "src\\main\\resources\\com\\image\\mosaic\\repository\\mosaics\\";

    public static List<BufferedArtImage> loadTiles()
    {
        List<BufferedArtImage> tiles = new ArrayList<>();
        try
        {
            File directory = ensureFile(TILES_PATH, false);
            FileFilter isImage = f -> f.getName().toLowerCase().endsWith(".jpeg") || f.getName().toLowerCase().endsWith(".jpg")
                                      || f.getName().toLowerCase().endsWith(".png");

            for (File file : directory.listFiles(isImage))
            {
                BufferedArtImage tileImage = new BufferedArtImage(ImageIO.read(file));
                tiles.add(tileImage);
            }

        }
        catch (IOException e)
        {
            System.err.println("Could not load the tiles: " + e.getMessage());
        }

        if (tiles.size() < 10)
        {
            System.err.println("Not enough tiles found");
        }
        return tiles;
    }


    public static void addMosaic(String mosaicDirName, String mosaicName, BufferedImage mosaic)
    {
        try
        {
            ensureDir(MOSAIC_PATH + mosaicDirName, true);
            File mosaicFile = ensureFile(MOSAIC_PATH + mosaicDirName + "\\" + mosaicName, true);
            ImageIO.write(mosaic, "png", mosaicFile);
        }
        catch (IOException e)
        {
            System.err.println("Could not save mosaic: " + e.getMessage());
        }
    }


    /**
     * Ensure that a file exists (or create if allowed by parameter).
     *
     * @param path   the path to the file
     * @param create indicates whether creation is allowed
     * @return the file
     * @throws IOException if something went wrong
     */
    private static File ensureDir(String path, boolean create) throws IOException
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
    private static File ensureFile(String path, boolean create) throws IOException
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


    public static Path addImage(File file)
    {

        try
        {
            return Files.copy(file.toPath(), Path.of(IMAGES_PATH, file.getName()));
        }
        catch (IOException e)
        {
            System.err.println("Could not add file to the repository: " + e.getMessage());
        }
        return null;
    }


    public static void deleteImage(FileImage fileImage)
    {
        if (fileImage != null)
        {
            File file = new File(fileImage.getPath());
            file.delete();
        }
    }


    public static List<FileImage> getImages()
    {
        List<FileImage> imagesList = new ArrayList<>();
        try
        {
            File directory = new File(IMAGES_PATH);
            FileFilter isImage = f -> f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg") || f.getName().endsWith(".png");
            File[] images = directory.listFiles(isImage);
            if (images != null)
            {
                for (File image : images)
                {
                    BufferedImage bufferedImage = ImageIO.read(image);
                    FileImage fileImage = new FileImage(bufferedImage, image.getName(),
                                                    image.getAbsolutePath(), bufferedImage.getWidth(),
                                                    bufferedImage.getHeight());
                    imagesList.add(fileImage);
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Could not load the images from the repository: " + e.getMessage());
        }
        return imagesList;
    }


    public static List<FileImage> getMosaics(FileImage fileImage)
    {
        if (fileImage != null)
        {
            List<FileImage> imagesList = new ArrayList<>();
            try
            {
                File directory = new File(MOSAIC_PATH + fileImage.getName());
                FileFilter isImage = f -> f.getName().endsWith(".jpeg") || f.getName().endsWith(".jpg") || f.getName().endsWith(".png");
                File[] mosaics = directory.listFiles(isImage);
                if (mosaics != null)
                {
                    for (File file : mosaics)
                    {
                        BufferedImage bufferedImage = ImageIO.read(file);
                        FileImage image = new FileImage(bufferedImage, file.getName(),
                                                        file.getAbsolutePath(), bufferedImage.getWidth(),
                                                        bufferedImage.getHeight());
                        imagesList.add(image);
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println("Could not load the images from the repository: " + e.getMessage());
            }
            return imagesList;
        }
        return Collections.emptyList();
    }
}
