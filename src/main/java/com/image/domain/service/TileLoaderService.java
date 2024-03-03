package com.image.domain.service;


import java.awt.image.BufferedImage;
import java.util.List;


/**
 * Interface for loading tiles
 */
public interface TileLoaderService
{
    List<BufferedImage> loadTiles();
}
