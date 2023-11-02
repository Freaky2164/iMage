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
import com.image.app.MosaicController;
import com.image.app.repository.FileImage;
import com.image.app.window.mosaic.ImageIconViewer;
import com.image.mosaic.base.ImageUtils;


public class ImagesPanel extends JPanel implements ImageIconViewer
{
    private static final long serialVersionUID = 1L;
    private Map<FileImage, Icon> imagesMap = new HashMap<>();
    private FileImage selectedImage;
    private MosaicController mosaicController;

    public ImagesPanel(List<FileImage> images, MosaicController mosaicController)
    {
        this.mosaicController = mosaicController;
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.GRAY);

        for (FileImage image : images)
        {
            ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(image.getImage(), MosaicController.IMAGE_SIZE,
                                                                 MosaicController.IMAGE_SIZE));
            Icon icon = new Icon(this, image, imageIcon, mosaicController);
            this.add(icon);
            this.imagesMap.put(image, icon);
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
        Icon icon = imagesMap.get(selectedImage);
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void addImage(FileImage image)
    {
        ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(image.getImage(), MosaicController.IMAGE_SIZE, MosaicController.IMAGE_SIZE));
        Icon icon = new Icon(this, image, imageIcon, mosaicController);
        this.add(icon);
        imagesMap.put(image, icon);
        revalidate();
        repaint();
    }


    public void deleteImage(FileImage image)
    {
        Icon icon = imagesMap.remove(image);
        this.remove(icon);
        revalidate();
        repaint();
    }
}
