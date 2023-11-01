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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.ImageUtils;


public class RepositoryHandler
{
    private static final String IMAGES_PATH = "src\\main\\resources\\com\\image\\mosaique\\repository\\";
    private static final int IMAGE_SIZE = 200;

    public static BufferedImage loadInput(String inputPath)
    {
        try
        {
            if (!inputPath.toLowerCase().endsWith(".png") && !inputPath.toLowerCase().endsWith(".jpeg") && !inputPath.toLowerCase()
                                                                                                                     .endsWith("jpg"))
            {
                System.err.println("Input is neither PNG nor JPG");
            }
            File inputFile = new File(inputPath);
            return ImageIO.read(inputFile);
        }
        catch (IOException e)
        {
            System.err.println("Could not load the input file: " + e.getMessage());
            return null;
        }
    }


    public static List<BufferedArtImage> loadTiles(String tilesPath)
    {
        List<BufferedArtImage> tiles = new ArrayList<>();
        try
        {
            File directory = ensureFile(tilesPath, false);
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


    public static void writeOutput(String outputPath, BufferedImage outputImage)
    {
        File output = null;
        try
        {
            output = ensureFile(outputPath, true);
            ImageIO.write(outputImage, "png", output);
        }
        catch (IOException e)
        {
            System.err.println("Could not save image: " + e.getMessage());
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


    public static void addImage(File file)
    {
        File targetFile = new File(IMAGES_PATH + file.getName());
        try
        {
            Files.copy(file.toPath(), targetFile.toPath());
        }
        catch (IOException e)
        {
            System.err.println("Could not add file to the repository: " + e.getMessage());
        }
    }


    public static void deleteImage(FileImage fileImage)
    {
        File file = new File(fileImage.getPath());
        file.delete();
    }


    public static List<FileImage> getImages()
    {
        List<FileImage> imagesList = new ArrayList<>();
        try
        {
            File directory = new File(IMAGES_PATH);
            FileFilter isImage = f -> f.getName().toLowerCase().endsWith(".jpeg") || f.getName().toLowerCase().endsWith(".jpg")
                                      || f.getName().toLowerCase().endsWith(".png");

            for (File file : directory.listFiles(isImage))
            {
                BufferedImage bufferedImage = ImageIO.read(file);
                FileImage image = new FileImage(ImageUtils.scale(bufferedImage, IMAGE_SIZE, IMAGE_SIZE), file.getAbsolutePath(),
                                                bufferedImage.getWidth(),
                                                bufferedImage.getHeight());
                imagesList.add(image);
            }

        }
        catch (IOException e)
        {
            System.err.println("Could not load the images from the repository: " + e.getMessage());
        }
        return imagesList;
    }
}
