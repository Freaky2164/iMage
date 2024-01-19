/*
 * ImagesPanel.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.main;


import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.ImageAggregate;
import com.image.domain.service.MosaicController;
import com.image.implementation.mosaic.base.ImageUtils;


public class ImagesPanel extends JPanel implements ImageIconViewer
{
    private static final long serialVersionUID = 1L;
    private Map<ImageAggregate, ImagesIcon> imagesMap = new HashMap<>();
    private ImageAggregate selectedImage;
    private MosaicController mosaicController;

    public ImagesPanel(List<ImageAggregate> images, MosaicController mosaicController)
    {
        this.mosaicController = mosaicController;
        setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.GRAY);

        for (ImageAggregate imageAggregate : images)
        {
            ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(imageAggregate.getImage(), 400, 400));
            ImagesIcon icon = new ImagesIcon(this, imageAggregate, imageIcon, mosaicController);
            this.add(icon);
            imagesMap.put(imageAggregate, icon);
        }
    }


    public void setSelectedImage(ImageAggregate imageAggregate)
    {
        selectedImage = imageAggregate;
    }


    public ImageAggregate getSelectedImage()
    {
        return selectedImage;
    }


    public void deselectImage()
    {
        ImagesIcon icon = imagesMap.get(selectedImage);
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void addImage(ImageAggregate imageAggregate)
    {
        ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(imageAggregate.getImage(), 250, 250));
        ImagesIcon icon = new ImagesIcon(this, imageAggregate, imageIcon, mosaicController);
        this.add(icon);
        imagesMap.put(imageAggregate, icon);
        revalidate();
        repaint();
    }


    public void deleteImage(ImageAggregate imageAggregate)
    {
        ImagesIcon icon = imagesMap.remove(imageAggregate);
        this.remove(icon);
        revalidate();
        repaint();
    }
}
