/*
 * MosaiqueFrame.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.image.app.MosaicController;
import com.image.app.repository.FileImage;


public class AppFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private MenuPanel menuPanel;
    private ImagesPanel imagePanel;
    private MetadataPanel metadataPanel;
    private MosaicController mosaicController;

    public AppFrame(MosaicController mosaicController)
    {
        this.mosaicController = mosaicController;
    }


    public void initialize(List<FileImage> images)
    {
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("iMage - Mosaique Maker");
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        menuPanel = new MenuPanel(mosaicController);
        getContentPane().add(menuPanel, c);

        c.gridx = 1;
        c.weightx = 1;
        JPanel dataPanel = new JPanel(new GridBagLayout());

        GridBagConstraints innerC = new GridBagConstraints();
        innerC.gridx = 0;
        innerC.gridy = 0;
        innerC.weightx = 1;
        innerC.weighty = 0.8;
        innerC.fill = GridBagConstraints.BOTH;

        imagePanel = new ImagesPanel(images, mosaicController);
        metadataPanel = new MetadataPanel();

        dataPanel.add(imagePanel, innerC);
        innerC.gridy = 1;
        innerC.weighty = 0.2;
        dataPanel.add(metadataPanel, innerC);

        getContentPane().add(dataPanel, c);

        this.setVisible(true);
    }


    public MenuPanel getMenuPanel()
    {
        return menuPanel;
    }


    public ImagesPanel getImagePanel()
    {
        return imagePanel;
    }


    public MetadataPanel getMetadataPanel()
    {
        return metadataPanel;
    }
}
