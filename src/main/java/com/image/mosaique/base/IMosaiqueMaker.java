/*
 * IMosaiqueMaker.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.mosaique.base;

import java.awt.image.BufferedImage;

public interface IMosaiqueMaker
{
    BufferedImage createMosaique(BufferedImage input, IMosaiqueArtist artist);
}
