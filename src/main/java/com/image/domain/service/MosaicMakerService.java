/*
 * MosaicMakerService.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.domain.service;


import java.awt.image.BufferedImage;

import com.image.implementation.mosaic.base.IMosaicArtist;


public interface MosaicMakerService
{
    BufferedImage createMosaic(BufferedImage input, IMosaicArtist artist);
}
