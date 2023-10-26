package com.image.mosaique;


import java.awt.image.BufferedImage;

import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;
import com.image.mosaique.base.IMosaiqueMaker;


/**
 * This class defines a {@link IMosaiqueMaker} which operates on {@link BufferedArtImage
 * BufferedArtImages}.
 */
public class MosaiqueMaker implements IMosaiqueMaker
{
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
        return result.toBufferedImage();
    }
}
