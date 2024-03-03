package com.image.frontend.window.main;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.service.MosaicController;


/**
 * This class is used to display images in the UI which can be selected for mosaic creation
 */
public class ImagesIcon extends JLabel
{
    private static final long serialVersionUID = 1L;

    public ImagesIcon(ImageIconViewer parent, UUID imageId, ImageIcon imageIcon, MosaicController mosaicController)
    {
        setIcon(imageIcon);
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                select(parent, imageId, mosaicController);
            }
        });
    }


    public void deselect()
    {
        setBorder(new FlatLineBorder(getInsets(), Color.LIGHT_GRAY, 3f, 0));
    }


    public void select(ImageIconViewer parent, UUID imageId, MosaicController mosaicController)
    {
        parent.deselectImage();
        parent.setSelectedImage(imageId);
        setBorder(new FlatLineBorder(getInsets(), Color.YELLOW, 3f, 0));
        if (mosaicController != null)
        {
            mosaicController.hasSelectedImage(imageId);
        }
    }
}
