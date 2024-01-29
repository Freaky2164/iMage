package com.image.implementation.mosaic.triangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.implementation.mosaic.AbstractArtist;
import com.image.implementation.mosaic.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.base.TileShape;


/**
 * This class represents an {@link IMosaicArtist} who uses triangles as tiles.
 */
public class TriangleArtist extends AbstractArtist
{
    private Collection<BufferedImage> tiles;
    private List<AbstractShape> upper;
    private List<AbstractShape> lower;

    /**
     * Create an artist who works with TriangleShapes
     *
     * @param tiles      the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight are 0, or images is empty.
     */
    public TriangleArtist(Collection<BufferedImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (tileWidth != tileHeight || (tileWidth < 2 && tileHeight < 2))
        {
            throw new IllegalArgumentException("Width and height must be the same and greater than 1");
        }
        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("No tiles provided");
        }

        this.tiles = tiles;
        upper = new ArrayList<>();
        lower = new ArrayList<>();

        for (var image : tiles)
        {
            upper.add(new UpperTriangleShape(image, tileWidth, tileHeight));
            lower.add(new LowerTriangleShape(image, tileWidth, tileHeight));
        }
    }


    @Override
    public Collection<BufferedImage> getTiles()
    {
        return tiles;
    }


    @Override
    public void drawTileForRegion(BufferedImage region, BufferedImage target)
    {
        int averageUpper = UpperTriangleCalculator.getInstance().averageColor(region);
        int averageLower = LowerTriangleCalculator.getInstance().averageColor(region);

        var upperImage = findNearest(averageUpper, upper);
        var lowerImage = findNearest(averageLower, lower);

        upperImage.drawMe(target);
        lowerImage.drawMe(target);
    }


    @Override
    public TileShape getShape()
    {
        return TileShape.TRIANGLE;
    }
}
