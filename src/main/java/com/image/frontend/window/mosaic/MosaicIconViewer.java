/*
 * MosaicIconViewer.java
 *
 * created at 2024-01-17 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.mosaic;


import com.image.domain.entities.Mosaic;


public interface MosaicIconViewer
{
    void deselectMosaic();


    void setSelectedMosaic(Mosaic mosaic);
}
