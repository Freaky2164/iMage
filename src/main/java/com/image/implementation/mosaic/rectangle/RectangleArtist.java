package com.image.implementation.mosaic.rectangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.implementation.AbstractArtist;
import com.image.implementation.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.base.IMosaicShape;
import com.image.implementation.mosaic.base.TileShape;


/**
 * This class represents an {@link IMosaicArtist} who uses rectangles as tiles.
 */
public final class RectangleArtist extends AbstractArtist
{
    private Collection<BufferedImage> tiles;
    private List<AbstractShape> shapes;

    /**
     * Create an artist who works with {@link RectangleShape RectangleShapes}
     *
     * @param tiles      the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public RectangleArtist(Collection<BufferedImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);

        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("No tiles provided.");
        }

        this.tiles = tiles;
        shapes = new ArrayList<>();
        for (BufferedImage tile : tiles)
        {
            shapes.add(new RectangleShape(tile, tileWidth, tileHeight));
        }
    }


    @Override
    public Collection<BufferedImage> getTiles()
    {
        return tiles;
    }


    @Override
    protected void drawTileForRegion(BufferedImage region, BufferedImage target)
    {
        int average = RectangleCalculator.getInstance().averageColor(region);
        IMosaicShape tile = findNearest(average, shapes);
        tile.drawMe(target);
    }


    @Override
    public TileShape getShape()
    {
        return TileShape.RECTANGLE;
    }
}
