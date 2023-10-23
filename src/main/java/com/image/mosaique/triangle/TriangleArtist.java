package com.image.mosaique.triangle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.image.mosaique.AbstractArtist;
import com.image.mosaique.AbstractShape;
import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;


/**
 * This class represents an {@link IMosaiqueArtist} who uses triangles as tiles.
 */
public class TriangleArtist extends AbstractArtist
{

    private List<AbstractShape> upper;
    private List<AbstractShape> lower;

    /**
     * Create an artist who works with TriangleShapes
     *
     * @param images     the images for the tiles
     * @param tileWidth  the desired width of the tiles
     * @param tileHeight the desired height of the tiles
     * @throws IllegalArgumentException if tileWidth or tileHeight &lt;= 0, or images is empty.
     */
    public TriangleArtist(Collection<BufferedArtImage> images, int tileWidth, int tileHeight)
    {
        super(tileWidth, tileHeight);
        if (images.isEmpty())
        {
            throw new IllegalArgumentException("no tiles provided");
        }

        this.upper = new ArrayList<>();
        this.lower = new ArrayList<>();

        for (var image : images)
        {
            upper.add(new UpperTriangleShape(image, tileWidth, tileHeight));
            lower.add(new LowerTriangleShape(image, tileWidth, tileHeight));
        }
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
