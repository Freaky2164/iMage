/*
 * App.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaique.app;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.image.mosaique.AbstractArtist;
import com.image.mosaique.MosaiqueMaker;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.crossed.CrossedRectangleArtist;
import com.image.mosaique.rectangle.RectangleArtist;
import com.image.mosaique.triangle.TriangleArtist;


/**
 * This class parses all command line parameters and creates a mosaique.
 */
public final class App
{

    public static void main(String[] args)
    {
        if (args.length < 5)
        {
            System.exit(1);
        }

        System.out.println("Starting to create mosaic");

        BufferedImage inputImage = loadInput(args[0]);
        List<BufferedArtImage> tiles = loadTiles(args[1]);

        System.out.println("Loaded input image and tiles");

        int tileWidth = Integer.parseInt(args[3]);
        int tileHeight = Integer.parseInt(args[4]);

        if (tileWidth <= 0 || tileHeight <= 0 || tileWidth > inputImage.getWidth() || tileHeight > inputImage.getHeight())
        {
            System.err.println("tileWidth/tileHeight is invalid: " + tileWidth + "," + tileHeight);
            System.exit(1);
        }

        MosaiqueMaker mosaiqueEasel = new MosaiqueMaker();
        AbstractArtist artist = null;
        if (args[5].equals("Rectangle"))
        {
            artist = new RectangleArtist(tiles, tileWidth, tileHeight);
        }
        else if (args[5].equals("Triangle"))
        {
            artist = new TriangleArtist(tiles, tileWidth, tileHeight);
        }
        else
        {
            artist = new CrossedRectangleArtist(tiles, tileWidth, tileHeight);
        }
        BufferedImage outputImage = mosaiqueEasel.createMosaique(inputImage, artist);

        writeOutput(args[2], outputImage);

        System.out.println("Finished creating mosaic");
    }


    private static BufferedImage loadInput(String inputPath)
    {
        try
        {
            if (!inputPath.toLowerCase().endsWith(".png") && !inputPath.toLowerCase().endsWith(".jpeg") && !inputPath.toLowerCase().endsWith("jpg"))
            {
                System.err.println("Input is neither PNG nor JPG");
                System.exit(1);
            }
            return ImageIO.read(App.ensureFile(inputPath, false));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }


    private static List<BufferedArtImage> loadTiles(String tilesPath)
    {
        List<BufferedArtImage> tiles = new ArrayList<>();
        try
        {
            File directory = App.ensureFile(tilesPath, false);
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
            System.err.println(e.getMessage());
            System.exit(1);
        }

        if (tiles.size() < 10)
        {
            System.err.println("Not enough tiles found");
            System.exit(1);
        }

        return tiles;

    }


    private static void writeOutput(String outputPath, BufferedImage outputImage)
    {
        File output = null;
        try
        {
            output = App.ensureFile(outputPath, true);
            ImageIO.write(outputImage, "png", output);
        }
        catch (IOException e)
        {
            System.err.println("Could not save image: " + e.getMessage());
            System.exit(1);
        }
    }


    /**
     * Ensure that a file exists (or create if allowed by parameter).
     *
     * @param path the path to the file
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
}
