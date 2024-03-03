/*
 * MosaicIcon.java
 *
 * created at 2024-01-17 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.mosaic;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.Mosaic;


/**
 * This class is used to display mosaic images in the UI 
 */
public class MosaicsIcon extends JLabel
{
    private static final long serialVersionUID = 1L;

    public MosaicsIcon(MosaicIconViewer parent, Mosaic mosaic, ImageIcon imageIcon)
    {
        setIcon(imageIcon);
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                select(parent, mosaic);
            }
        });
    }


    public void deselect()
    {
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
    }


    public void select(MosaicIconViewer parent, Mosaic mosaic)
    {
        parent.deselectMosaic();
        parent.setSelectedMosaic(mosaic);
        setBorder(new FlatLineBorder(getInsets(), Color.YELLOW, 3f, 0));
    }
}
