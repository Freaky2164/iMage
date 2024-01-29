/*
 * TileLoader.java
 *
 * created at 2024-01-17 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.implementation.repository;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.image.domain.service.TileLoaderService;


public class TileLoader implements TileLoaderService
{
    protected String tilesPath;
    protected int numberOfThreads;

    public TileLoader()
    {
        this.tilesPath = "src\\main\\resources\\com\\image\\mosaic\\tiles";
        this.numberOfThreads = 4;
    }


    public List<BufferedImage> loadTiles()
    {
        List<BufferedImage> tiles = new ArrayList<>();
        try
        {
            File directory = ensureFile(tilesPath, false);
            FileFilter isImage = f -> f.getName().toLowerCase().endsWith(".jpeg") || f.getName().toLowerCase().endsWith(".jpg")
                                      || f.getName().toLowerCase().endsWith(".png");
            List<Thread> threads = new ArrayList<>();
            File[] files = directory.listFiles(isImage);
            for (int i = 0; i < numberOfThreads; i++)
            {
                int threadNumber = i;
                Thread thread = new Thread(() ->
                {
                    try
                    {
                        int numberOfFilesPerThread = files.length / numberOfThreads;
                        int tileStartNumber = numberOfFilesPerThread * threadNumber;
                        int tileEndNumber = threadNumber != 4 ? tileStartNumber + numberOfFilesPerThread : files.length;
                        for (int j = tileStartNumber; j < tileEndNumber; j++)
                        {
                            if (files[j] != null)
                            {
                                BufferedImage tileImage = ImageIO.read(files[j]);
                                tiles.add(tileImage);
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        System.err.println("Could not load the tiles: " + e.getMessage());
                    }
                });
                threads.add(thread);
                thread.start();
            }
            threads.forEach(t ->
            {
                try
                {
                    t.join();
                }
                catch (InterruptedException e)
                {
                    System.err.println("Interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            });
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
}
