package com.image.mosaic.crossed;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaic.AbstractArtist;
import com.image.mosaic.AbstractShape;
import com.image.mosaic.base.BufferedArtImage;
import com.image.mosaic.crossed.calculator.CrossedLeftCalculator;
import com.image.mosaic.crossed.calculator.CrossedLowerCalculator;
import com.image.mosaic.crossed.calculator.CrossedRightCalculator;
import com.image.mosaic.crossed.calculator.CrossedUpperCalculator;


public class CrossedRectangleArtist extends AbstractArtist
{
    private Collection<BufferedArtImage> tiles;
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
    public CrossedRectangleArtist(Collection<BufferedArtImage> tiles, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (tiles.isEmpty())
        {
            throw new IllegalArgumentException("no tiles provided");
        }

        this.tiles = tiles;
        this.upper = new ArrayList<>();
        this.lower = new ArrayList<>();
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();

        for (var tile : tiles)
        {
            upper.add(new UpperCrossedShape(tile, tileWidth, tileHeight));
            lower.add(new LowerCrossedShape(tile, tileWidth, tileHeight));
            left.add(new LeftCrossedShape(tile, tileWidth, tileHeight));
            right.add(new RightCrossedShape(tile, tileWidth, tileHeight));
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
}
