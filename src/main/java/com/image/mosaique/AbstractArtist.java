package com.image.mosaique;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.image.mosaique.base.BufferedArtImage;
import com.image.mosaique.base.IMosaiqueArtist;


public abstract class AbstractArtist implements IMosaiqueArtist
{
    protected final int tileWidth;
    protected final int tileHeight;

    protected AbstractArtist(int tileWidth, int tileHeight)
    {
        if (tileWidth <= 0 || tileHeight <= 0)
        {
            throw new IllegalArgumentException("tileWidth and tileHeight have to be > 0");
        }
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }


    @Override
    public final int getTileWidth()
    {
        return tileWidth;
    }


    @Override
    public final int getTileHeight()
    {
        return tileHeight;
    }


    @Override
    public final BufferedArtImage getTileForRegion(BufferedArtImage region)
    {
        if (region.getWidth() > this.tileWidth || region.getHeight() > this.tileHeight)
        {
            throw new IllegalArgumentException("Requested tiling is greater than tileWidth or tileHeight");
        }
        BufferedArtImage result = region.createBlankImage();
        drawTileForRegion(region.toBufferedImage(), result);
        return result;
    }


    /**
     * The artist works on a small region and chooses the most fitting tile for the given region.<br>
     * Iff the region is smaller than {@link #getTileWidth()} and/or {@link #getTileHeight()} the
     * artist will choose the matching tiles anyway. However, the respective dimensions are cut
     * off.<br>
     * The super caller of the method ensures that the dimensions of the region are &lt;= the size of
     * the tiles.
     *
     * @param region the region which needs a tiling
     * @param target the target image on which the tiles shall be drawn
     */
    protected abstract void drawTileForRegion(BufferedImage region, BufferedArtImage target);


    /**
     * Find the shape with the best matching color from a list of shapes.
     *
     * @param target the target color as argb.
     * @param shapes the available shapes (cannot be empty)
     * @return the best matching shape
     */
    protected final AbstractShape findNearest(int target, Collection<AbstractShape> shapes)
    {
        List<AbstractShape> nearest = new ArrayList<>();

        Iterator<AbstractShape> shapesIterator = shapes.iterator();
        AbstractShape shape = shapesIterator.next();
        nearest.add(shape);

        double dist = colorError(target, shape.getAverageColor());

        while (shapesIterator.hasNext())
        {
            AbstractShape next = shapesIterator.next();
            double nextDist = colorError(target, next.getAverageColor());

            if (Double.compare(nextDist, dist) < 0)
            {
                nearest.clear();
                nearest.add(next);
                dist = nextDist;
            }
        }
        return nearest.get((int)(Math.random() * nearest.size()));
    }


    /**
     * Calculate the difference between two argb colors as euclidean distance.<br>
     * Range: [0, sqrt(4 * pow(255, 2))]
     *
     * @param colorA the first color
     * @param colorB the second color
     * @return the difference of the colors
     */
    private static double colorError(int colorA, int colorB)
    {
        Color a = new Color(colorA, true);
        Color b = new Color(colorB, true);

        int colorError = (int)Math.pow(a.getRed() - b.getRed(), 2);
        colorError += (int)Math.pow(a.getGreen() - b.getGreen(), 2);
        colorError += (int)Math.pow(a.getBlue() - b.getBlue(), 2);
        colorError += (int)Math.pow(a.getAlpha() - b.getAlpha(), 2);

        return Math.sqrt(colorError);
    }
}
