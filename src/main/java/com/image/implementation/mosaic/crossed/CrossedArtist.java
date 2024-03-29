package com.image.implementation.mosaic.crossed;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.implementation.AbstractArtist;
import com.image.implementation.AbstractShape;
import com.image.implementation.mosaic.base.IMosaicArtist;
import com.image.implementation.mosaic.base.TileShape;
import com.image.implementation.mosaic.crossed.calculator.CrossedLeftCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedLowerCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedRightCalculator;
import com.image.implementation.mosaic.crossed.calculator.CrossedUpperCalculator;


/**
 * This class represents an {@link IMosaicArtist} who uses crossed shapes as tiles.
 */
public final class CrossedArtist extends AbstractArtist
{
    private Collection<BufferedImage> tiles;
    private List<AbstractShape> upper;
    private List<AbstractShape> lower;
    private List<AbstractShape> left;
    private List<AbstractShape> right;

    /**
     * Create an artist who works with {@link AbstractShape CrossedShapes}
     *
     * @param images     the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight are 0 or images is empty.
     */
    public CrossedArtist(Collection<BufferedImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (tileWidth != tileHeight || (tileWidth < 3 && tileHeight < 3))
        {
            throw new IllegalArgumentException("Width and height must be the same and greater than 2.");
        }
        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("no tiles provided");
        }

        this.tiles = tiles;
        upper = new ArrayList<>();
        lower = new ArrayList<>();
        left = new ArrayList<>();
        right = new ArrayList<>();

        for (var tile : tiles)
        {
            upper.add(new UpperCrossedShape(tile, tileWidth, tileHeight));
            lower.add(new LowerCrossedShape(tile, tileWidth, tileHeight));
            left.add(new LeftCrossedShape(tile, tileWidth, tileHeight));
            right.add(new RightCrossedShape(tile, tileWidth, tileHeight));
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
        int averageUpper = CrossedUpperCalculator.getInstance().averageColor(region);
        int averageLower = CrossedLowerCalculator.getInstance().averageColor(region);
        int averageLeft = CrossedLeftCalculator.getInstance().averageColor(region);
        int averageRight = CrossedRightCalculator.getInstance().averageColor(region);

        var upperImage = findNearest(averageUpper, upper);
        var lowerImage = findNearest(averageLower, lower);
        var leftImage = findNearest(averageLeft, left);
        var rightImage = findNearest(averageRight, right);

        upperImage.drawMe(target);
        lowerImage.drawMe(target);
        leftImage.drawMe(target);
        rightImage.drawMe(target);
    }


    @Override
    public TileShape getShape()
    {
        return TileShape.CROSSED;
    }
}
