/*
 * ImagePanel.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.repository.FileImage;


public class ImagesPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private List<FileImage> images;

    public ImagesPanel(List<FileImage> images)
    {
        this.images = images;
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        for (FileImage fileImage : images)
        {
            ImageIcon imageIcon = new ImageIcon(fileImage.getImage());
            JLabel displayField = new JLabel(imageIcon);
            displayField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
            this.add(displayField);
        }
    }

    public FileImage getSelectedImage()
    {
        return null;
    }


    public void addImage(FileImage image)
    {
        images.add(image);
        repaint();
    }


    public void deleteImage(FileImage image)
    {
        images.remove(image);
        repaint();
    }
}
