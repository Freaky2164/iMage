package com.image.frontend.window.mosaic;


import com.image.domain.entities.Mosaic;


/**
 * Interface for selecting and deselecting mosaics
 */
public interface MosaicIconViewer
{
    void deselectMosaic();


    void setSelectedMosaic(Mosaic mosaic);
}
