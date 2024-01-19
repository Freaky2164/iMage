package com.image.implementation.mosaic;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.image.domain.service.MosaicMakerService;
import com.image.frontend.listener.MosaicCreationListener;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.rectangle.RectangleArtist;


/**
 * This class defines a {@link MosaicMakerService} which operates on {@link FileInformation
 * BufferedArtImages}.
 */
public final class MosaicMaker implements MosaicMakerService
{
    private static final int WIDTH_FOR_ONE_THREAD = 100;
    private List<MosaicCreationListener> mosaicCreationListeners = new ArrayList<>();

    @Override
    public BufferedImage createMosaic(BufferedImage inputImage, IMosaicArtist artist)
    {
        BufferedImage resultImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int numberOfThreads = inputImage.getWidth() / WIDTH_FOR_ONE_THREAD;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i <= numberOfThreads; i++)
        {
            int threadStartingWidth = i * WIDTH_FOR_ONE_THREAD;
            Thread thread = new Thread(() -> setTileToImageRegion(threadStartingWidth, inputImage, artist, resultImage));
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
                Thread.currentThread().interrupt();
            }
        });

        mosaicCreated(resultImage);
        return resultImage;
    }


    private void setTileToImageRegion(int threadStartingWidth, BufferedImage inputImage, IMosaicArtist artist,
                                                BufferedImage resultImage)
    {
        IMosaicArtist backupArtist = new RectangleArtist(artist.getTiles(), artist.getTileWidth(), artist.getTileHeight());

        int tileWidth = artist.getTileWidth();
        int tileHeight = artist.getTileHeight();

        int threadWidth = threadStartingWidth + WIDTH_FOR_ONE_THREAD < inputImage.getWidth()
            ? threadStartingWidth + WIDTH_FOR_ONE_THREAD
            : inputImage.getWidth();

        for (int x = threadStartingWidth; x < threadWidth; x += tileWidth)
        {
            for (int y = 0; y < inputImage.getHeight(); y += tileHeight)
            {
                int width = x + tileWidth < inputImage.getWidth() ? tileWidth : inputImage.getWidth() - x;
                int height = y + tileHeight < inputImage.getHeight() ? tileHeight : inputImage.getHeight() - y;

                BufferedImage imageRegion = inputImage.getSubimage(x, y, width, height);
                BufferedImage tile = width == height
                    ? artist.getTileForRegion(imageRegion)
                    : backupArtist.getTileForRegion(imageRegion);
                setSubimage(resultImage, tile, x, y);
            }
        }
    }


    private BufferedImage setSubimage(BufferedImage inputImage, BufferedImage tile, int x, int y)
    {
        Graphics2D graphics2d = inputImage.createGraphics();
        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        graphics2d.drawImage(tile, x, y, null);
        graphics2d.dispose();
        return inputImage;
    }


    public void addMosaicCreationListener(MosaicCreationListener listener)
    {
        mosaicCreationListeners.add(listener);
    }


    public void removeMosaicCreationListener(MosaicCreationListener listener)
    {
        mosaicCreationListeners.remove(listener);
    }


    public void mosaicCreated(BufferedImage resultImage)
    {
        for (MosaicCreationListener mosaicCreationListener : mosaicCreationListeners)
        {
            mosaicCreationListener.mosaicCreated(resultImage);
        }
    }
}
