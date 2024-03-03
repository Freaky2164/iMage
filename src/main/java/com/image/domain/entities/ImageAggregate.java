package com.image.domain.entities;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.Validate;


public class ImageAggregate
{
    private UUID imageId;
    private BufferedImage image;
    private String name;
    private String path;
    private List<Mosaic> mosaics;

    public ImageAggregate(UUID imageId, BufferedImage image, String name, String path)
    {
        Objects.requireNonNull(imageId);
        Objects.requireNonNull(image);
        Validate.notBlank(name);
        Validate.notBlank(path);
        this.imageId = imageId;
        this.image = image;
        this.name = name;
        this.path = path;
        this.mosaics = new ArrayList<>();
    }


    public UUID getImageId()
    {
        return imageId;
    }


    public BufferedImage getImage()
    {
        return image;
    }


    public String getName()
    {
        return name;
    }


    public String getPath()
    {
        return path;
    }


    public List<Mosaic> getMosaics()
    {
        return mosaics;
    }


    public void addMosaic(Mosaic mosaic)
    {
        mosaics.add(mosaic);
    }


    public void deleteMosaic(UUID mosaicId)
    {
        boolean removedMosaic = mosaics.removeIf(mosaic -> mosaic.getMosaicId().equals(mosaicId));
        if (!removedMosaic)
        {
            throw new IllegalStateException("There is no mosaic created for this image");
        }
    }
}
