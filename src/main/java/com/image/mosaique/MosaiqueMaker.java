package com.image.mosaique;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.image.app.listener.MosaiqueCreationListener;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;
import com.image.mosaique.base.IMosaiqueMaker;


/**
 * This class defines a {@link IMosaiqueMaker} which operates on {@link BufferedArtImage
 * BufferedArtImages}.
 */
public class MosaiqueMaker implements IMosaiqueMaker
{
    private List<MosaiqueCreationListener> mosaiqueCreationListeners = new ArrayList<>();

    @Override
    public BufferedImage createMosaique(BufferedImage inputImage, IMosaiqueArtist artist)
    {
        int tileWidth = artist.getTileWidth();
        int tileHeight = artist.getTileHeight();

        BufferedArtImage input = new BufferedArtImage(inputImage);
        BufferedArtImage result = input.createBlankImage();

        for (int x = 0; x < input.getWidth(); x += tileWidth)
        {
            for (int y = 0; y < input.getHeight(); y += tileHeight)
            {
                int width = x + tileWidth < input.getWidth() ? tileWidth : input.getWidth() - x;
                int height = y + tileHeight < input.getHeight() ? tileHeight : input.getHeight() - y;

                BufferedArtImage imageRegion = input.getSubimage(x, y, width, height);
                BufferedArtImage tile = artist.getTileForRegion(imageRegion);
                result.setSubimage(x, y, tile);
            }
        }
        mosaiqueCreated(result);
        return result.toBufferedImage();
    }


    public void addMosaiqueCreationListener(MosaiqueCreationListener listener)
    {
        mosaiqueCreationListeners.add(listener);
    }


    public void removeMosaiqueCreationListener(MosaiqueCreationListener listener)
    {
        mosaiqueCreationListeners.remove(listener);
    }


    public void mosaiqueCreated(BufferedArtImage resultImage)
    {
        for (MosaiqueCreationListener mosaiqueCreationListener : mosaiqueCreationListeners)
        {
            mosaiqueCreationListener.mosaiqueCreated(resultImage);
        }
    }
}
