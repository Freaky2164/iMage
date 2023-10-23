package com.image.mosaique.crossed;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaique.AbstractArtist;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.crossed.calculator.LeftCalculator;
import com.image.mosaique.crossed.calculator.LowerCalculator;
import com.image.mosaique.crossed.calculator.RightCalculator;
import com.image.mosaique.crossed.calculator.UpperCalculator;


public class CrossedRectangleArtist extends AbstractArtist
{
    private List<AbstractShape> upper;
    private List<AbstractShape> lower;
    private List<AbstractShape> left;
    private List<AbstractShape> right;

    /**
     * Create an artist who works with {@link AbstractShape CrossedShapes}
     *
     * @param images
     *                   the images for the tiles
     * @param tileWidth
     *                   the desired width of the tiles
     * @param tileHeight
     *                   the desired height of the tiles
     * @throws IllegalArgumentException
     *                                  iff tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public CrossedRectangleArtist(Collection<BufferedArtImage> images, int tileWidth,
                                  int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (images.isEmpty())
        {
            throw new IllegalArgumentException("no tiles provided");
        }

        this.upper = new ArrayList<>();
        this.lower = new ArrayList<>();
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();

        for (var image : images)
        {
            upper.add(new UpperCrossedShape(image, tileWidth, tileHeight));
            lower.add(new LowerCrossedShape(image, tileWidth, tileHeight));
            left.add(new LeftCrossedShape(image, tileWidth, tileHeight));
            right.add(new RightCrossedShape(image, tileWidth, tileHeight));
        }
    }


    @Override
    public void drawTileForRegion(BufferedImage region, BufferedArtImage target)
    {
        int averageUpper = UpperCalculator.getInstance().averageColor(region);
        int averageLower = LowerCalculator.getInstance().averageColor(region);
        int averageLeft = LeftCalculator.getInstance().averageColor(region);
        int averageRight = RightCalculator.getInstance().averageColor(region);

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
