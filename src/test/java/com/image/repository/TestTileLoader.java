/*
 * TileLoader.java
 *
 * created at 2024-01-17 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.repository;

public class TestTileLoader extends TileLoader
{
    public TestTileLoader()
    {
        this.tilesPath = "src\\test\\resources\\com\\image\\mosaic\\tiles";
        this.numberOfThreads = 1;
    }
}
