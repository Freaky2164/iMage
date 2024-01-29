/*
 * TestFileSystemImageRepository.java
 *
 * created at 2024-01-23 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.repository;


import com.image.implementation.repository.FileSystemImageRepository;


public class TestFileSystemImageRepository extends FileSystemImageRepository
{
    public TestFileSystemImageRepository()
    {
        this.imagePath = "src\\test\\resources\\com\\image\\mosaic\\repository\\images\\";
        this.mosaicPath = "src\\test\\resources\\com\\image\\mosaic\\repository\\mosaics\\";
    }
}
