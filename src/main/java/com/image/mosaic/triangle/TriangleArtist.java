package com.image.mosaic.triangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaic.AbstractArtist;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.base.IMosaicArtist;


/**
 * This class represents an {@link IMosaicArtist} who uses triangles as tiles.
 */
public class TriangleArtist extends AbstractArtist
{
    private Collection<BufferedArtImage> tiles;
    private List<AbstractShape> upper;
    private List<AbstractShape> lower;

    /**
     * Create an artist who works with TriangleShapes
     *
     * @param tiles     the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight are 0, or images is empty.
     */
    public TriangleArtist(Collection<BufferedArtImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("no tiles provided");
        }

        this.tiles = tiles;
        this.upper = new ArrayList<>();
        this.lower = new ArrayList<>();

        for (var image : tiles)
        {
            upper.add(new UpperTriangleShape(image, tileWidth, tileHeight));
            lower.add(new LowerTriangleShape(image, tileWidth, tileHeight));
        }
    }


    @Override
    public Collection<BufferedArtImage> getTiles()
    {
        return tiles;
    }


    @Override
    public void drawTileForRegion(BufferedImage region, BufferedArtImage target)
    {
        int averageUpper = UpperTriangleCalculator.getInstance().averageColor(region);
        int averageLower = LowerTriangleCalculator.getInstance().averageColor(region);

        var upperImage = findNearest(averageUpper, upper);
        var lowerImage = findNearest(averageLower, lower);

        upperImage.drawMe(target);
        lowerImage.drawMe(target);
    }
}
