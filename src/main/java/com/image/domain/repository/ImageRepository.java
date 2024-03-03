package com.image.domain.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.image.domain.entities.ImageAggregate;
import com.image.domain.entities.Mosaic;


/**
 * Interface of all functions needed for the repository of the application
 */
public interface ImageRepository
{
    Optional<ImageAggregate> findById(UUID imageId);


    List<ImageAggregate> findAll();


    ImageAggregate save(ImageAggregate image);


    void delete(UUID imageId);


    List<Mosaic> findAll(UUID imageId);


    Mosaic save(Mosaic mosaic);


    void delete(UUID imageId, UUID mosaicId);
}
