package com.image.mosaic.rectangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaic.AbstractArtist;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicArtist;
import com.image.mosaic.base.IMosaicShape;


/**
 * This class represents an {@link IMosaicArtist} who uses rectangles as tiles.
 *
 */
public class RectangleArtist extends AbstractArtist
{

    private List<AbstractShape> shapes;

    /**
     * Create an artist who works with {@link RectangleShape RectangleShapes}
     *
     * @param images     the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public RectangleArtist(Collection<BufferedArtImage> images, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);

        if (images.isEmpty())
        {
            throw new IllegalArgumentException("No tiles provided");
        }

        this.shapes = new ArrayList<>();
        for (BufferedArtImage image : images)
        {
            shapes.add(new RectangleShape(image, tileWidth, tileHeight));
        }
    }


    @Override
    protected void drawTileForRegion(BufferedImage region, BufferedArtImage target)
    {
        int average = RectangleCalculator.getInstance().averageColor(region);
        IMosaicShape tile = findNearest(average, shapes);
        tile.drawMe(target);
    }

}
