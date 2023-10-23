package com.image.mosaique.rectangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaique.AbstractArtist;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;
import com.image.mosaique.base.IMosaiqueShape;


/**
 * This class represents an {@link IMosaiqueArtist} who uses rectangles as tiles.
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
        IMosaiqueShape tile = findNearest(average, shapes);
        tile.drawMe(target);
    }

}
