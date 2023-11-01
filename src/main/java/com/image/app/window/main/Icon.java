/*
 * ImageIcon.java
 *
 * created at 2023-11-01 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.repository.FileImage;


public class Icon extends JLabel
{
    private static final long serialVersionUID = 1L;

    public Icon(ImagesPanel imagesPanel, FileImage fileImage, ImageIcon imageIcon)
    {
        super(imageIcon);
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                select(imagesPanel, fileImage);
            }
        });
    }


    public void deselect()
    {
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
    }


    public void select(ImagesPanel imagesPanel, FileImage fileImage)
    {
        imagesPanel.deselectImage();
        imagesPanel.setSelectedImage(fileImage);
        setBorder(new FlatLineBorder(getInsets(), Color.YELLOW, 3f, 0));
    }
}
