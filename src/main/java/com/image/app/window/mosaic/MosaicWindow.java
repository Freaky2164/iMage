/*
 * MosaicWindow.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.mosaic;


import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.image.mosaic.base.ImageUtils;


public class MosaicWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

    public MosaicWindow(BufferedImage mosaic)
    {
        this.setSize(mosaic.getWidth(), mosaic.getHeight());
        this.setMaximumSize(new Dimension(1920, 1080));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("iMage - Mosaic");

        ImageIcon imageIcon = null;
        if (getMaximumSize().getWidth() < mosaic.getWidth() && getMaximumSize().getHeight() < mosaic.getHeight())
        {
            imageIcon = new ImageIcon(ImageUtils.scale(mosaic, (int)getMaximumSize().getWidth(), (int)getMaximumSize().getHeight()));
        }
        else
        {
            imageIcon = new ImageIcon(mosaic);
        }
        JLabel displayedImage = new JLabel(imageIcon);
        this.add(displayedImage);

        this.setVisible(true);
    }
}
