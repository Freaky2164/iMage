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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.repository.FileImage;


public class ImagesPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Map<FileImage, Icon> images = new HashMap<>();
    private FileImage selectedImage;

    public ImagesPanel(List<FileImage> images)
    {
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        for (FileImage image : images)
        {
            ImageIcon imageIcon = new ImageIcon(image.getImage());
            Icon icon = new Icon(this, image, imageIcon);
            this.add(icon);
            this.images.put(image, icon);
        }
    }


    public void setSelectedImage(FileImage fileImage)
    {
        this.selectedImage = fileImage;
    }


    public FileImage getSelectedImage()
    {
        return selectedImage;
    }


    public void deselectImage()
    {
        Icon icon = images.get(selectedImage);
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void addImage(FileImage image)
    {
        ImageIcon imageIcon = new ImageIcon(image.getImage());
        Icon icon = new Icon(this, image, imageIcon);
        this.add(icon);
        images.put(image, icon);
        repaint();
    }


    public void deleteImage(FileImage image)
    {
        Icon icon = images.remove(image);
        this.remove(icon);
        repaint();
    }
}
