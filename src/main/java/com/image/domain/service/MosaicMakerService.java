/*
 * MosaicMakerService.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.service;


import com.image.domain.entities.ImageAggregate;
import com.image.implementation.mosaic.base.IMosaicArtist;


public interface MosaicMakerService
{
    void createMosaic(ImageAggregate inputImage, IMosaicArtist artist);
}
