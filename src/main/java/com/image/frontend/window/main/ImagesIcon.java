/*
 * ImagesIcon.java
 *
 * created at 2023-11-01 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.main;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.ImageAggregate;
import com.image.domain.service.MosaicController;


public class ImagesIcon extends JLabel
{
    private static final long serialVersionUID = 1L;

    public ImagesIcon(ImageIconViewer parent, ImageAggregate imageAggregate, ImageIcon imageIcon, MosaicController mosaicController)
    {
        setIcon(imageIcon);
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                select(parent, imageAggregate, mosaicController);
            }
        });
    }


    public void deselect()
    {
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
    }


    public void select(ImageIconViewer parent, ImageAggregate imageAggregate, MosaicController mosaicController)
    {
        parent.deselectImage();
        parent.setSelectedImage(imageAggregate);
        setBorder(new FlatLineBorder(getInsets(), Color.YELLOW, 3f, 0));
        if (mosaicController != null)
        {
            mosaicController.hasSelectedImage(imageAggregate);
        }
    }
}
