/*
 * TileLoaderService.java
 *
 * created at 2024-01-17 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.service;


import java.awt.image.BufferedImage;
import java.util.List;


public interface TileLoaderService
{
    List<BufferedImage> loadTiles();
}
