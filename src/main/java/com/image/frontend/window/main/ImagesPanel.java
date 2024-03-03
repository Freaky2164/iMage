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
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.ImageAggregate;
import com.image.domain.service.MosaicController;
import com.image.implementation.mosaic.base.ImageUtils;


/**
 * Panel for displaying images on the UI
 */
public class ImagesPanel extends JPanel implements ImageIconViewer
{
    private static final long serialVersionUID = 1L;
    private Map<UUID, ImagesIcon> imagesMap = new HashMap<>();
    private UUID selectedImageId;
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
            ImagesIcon icon = new ImagesIcon(this, imageAggregate.getImageId(), imageIcon, mosaicController);
            this.add(icon);
            imagesMap.put(imageAggregate.getImageId(), icon);
        }
    }


    public void setSelectedImage(UUID imageId)
    {
        this.selectedImageId = imageId;
    }


    public UUID getSelectedImageId()
    {
        return selectedImageId;
    }


    public void deselectImage()
    {
        ImagesIcon icon = imagesMap.get(selectedImageId);
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void addImage(ImageAggregate imageAggregate)
    {
        ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(imageAggregate.getImage(), 250, 250));
        ImagesIcon icon = new ImagesIcon(this, imageAggregate.getImageId(), imageIcon, mosaicController);
        this.add(icon);
        imagesMap.put(imageAggregate.getImageId(), icon);
        revalidate();
        repaint();
    }


    public void deleteImage(UUID imageId)
    {
        ImagesIcon icon = imagesMap.remove(imageId);
        this.remove(icon);
        revalidate();
        repaint();
    }
}
