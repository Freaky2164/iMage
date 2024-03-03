package com.image.domain.service;


import com.image.domain.entities.ImageAggregate;
import com.image.implementation.mosaic.base.IMosaicArtist;


/**
 * Interface for creating mosaics
 */
public interface MosaicMakerService
{
    void createMosaic(ImageAggregate inputImage, IMosaicArtist artist);
}
