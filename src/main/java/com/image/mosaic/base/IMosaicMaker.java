/*
 * IMosaicMaker.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaic.base;

import java.awt.image.BufferedImage;

public interface IMosaicMaker
{
    BufferedImage createMosaic(BufferedImage input, IMosaicArtist artist);
}
