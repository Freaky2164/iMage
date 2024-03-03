package com.image.frontend.listener;


import com.image.domain.entities.Mosaic;


/**
 * Interface used to inform about a successful mosaic creation
 */
public interface MosaicCreationListener
{
    void mosaicCreated(Mosaic mosaic);
}
