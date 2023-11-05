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
    private Collection<BufferedArtImage> tiles;
    private List<AbstractShape> shapes;

    /**
     * Create an artist who works with {@link RectangleShape RectangleShapes}
     *
     * @param tiles      the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public RectangleArtist(Collection<BufferedArtImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);

        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("No tiles provided");
        }

        this.tiles = tiles;
        this.shapes = new ArrayList<>();
        for (BufferedArtImage tile : tiles)
        {
            shapes.add(new RectangleShape(tile, tileWidth, tileHeight));
        }
    }


    @Override
    public Collection<BufferedArtImage> getTiles()
    {
        return tiles;
    }


    @Override
    protected void drawTileForRegion(BufferedImage region, BufferedArtImage target)
    {
        int average = RectangleCalculator.getInstance().averageColor(region);
        IMosaicShape tile = findNearest(average, shapes);
        tile.drawMe(target);
    }

}
