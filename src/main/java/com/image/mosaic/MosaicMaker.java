package com.image.mosaic;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.image.app.listener.MosaicCreationListener;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicArtist;
import com.image.mosaic.base.IMosaicMaker;
import com.image.mosaic.rectangle.RectangleArtist;


/**
 * This class defines a {@link IMosaicMaker} which operates on {@link BufferedArtImage
 * BufferedArtImages}.
 */
public class MosaicMaker implements IMosaicMaker
{
    private List<MosaicCreationListener> mosaicCreationListeners = new ArrayList<>();

    @Override
    public BufferedImage createMosaic(BufferedImage inputImage, IMosaicArtist artist)
    {
        IMosaicArtist backupArtist = new RectangleArtist(artist.getTiles(), artist.getTileWidth(), artist.getTileHeight());

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
                BufferedArtImage tile = width == height
                    ? artist.getTileForRegion(imageRegion)
                    : backupArtist.getTileForRegion(imageRegion);
                result.setSubimage(x, y, tile);
            }
        }
        mosaicCreated(result);
        return result.toBufferedImage();
    }


    public void addMosaicCreationListener(MosaicCreationListener listener)
    {
        mosaicCreationListeners.add(listener);
    }


    public void removeMosaicCreationListener(MosaicCreationListener listener)
    {
        mosaicCreationListeners.remove(listener);
    }


    public void mosaicCreated(BufferedArtImage resultImage)
    {
        for (MosaicCreationListener mosaicCreationListener : mosaicCreationListeners)
        {
            mosaicCreationListener.mosaicCreated(resultImage);
        }
    }
}
