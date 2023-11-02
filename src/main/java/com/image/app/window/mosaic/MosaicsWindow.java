/*
 * MosaicsWindow.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.mosaic;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.MosaicController;
import com.image.app.repository.FileImage;
import com.image.app.repository.RepositoryHandler;
import com.image.app.window.main.Icon;
import com.image.mosaic.base.ImageUtils;


public class MosaicsWindow extends JFrame implements ImageIconViewer
{
    private static final long serialVersionUID = 1L;
    private Map<FileImage, Icon> mosaicsMap = new HashMap<>();
    private FileImage selectedImage;
    private JPanel mainPanel;

    public MosaicsWindow(MosaicController mosaicController)
    {
        this.setSize(800, 500);
        this.setLocationRelativeTo(this.getParent());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("iMage - Mosaics");
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));

        List<FileImage> mosaics = RepositoryHandler.getMosaics(mosaicController.getSelectedImage());
        for (FileImage mosaic : mosaics)
        {
            ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(mosaic.getImage(), MosaicController.IMAGE_SIZE,
                                                                 MosaicController.IMAGE_SIZE));
            Icon icon = new Icon(this, mosaic, imageIcon, null);
            mainPanel.add(icon);
            this.mosaicsMap.put(mosaic, icon);
        }

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton deleteButton = new JButton("Delete Mosaic");
        deleteButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteButton.addActionListener(e -> {
            mosaicController.deleteMosaic(selectedImage);
            deleteMosaic(selectedImage);
        });

        JButton showButton = new JButton("Show Mosaic");
        showButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showButton.addActionListener(e ->
        {
            try
            {
                File file = new File(selectedImage.getPath());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
            catch (IOException exception)
            {
                System.err.println("Cannot open file in system explorer: " + exception.getMessage());
            }
        });

        menuPanel.add(deleteButton);
        menuPanel.add(showButton);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    public void deselectImage()
    {
        Icon icon = mosaicsMap.get(selectedImage);
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void setSelectedImage(FileImage fileImage)
    {
        this.selectedImage = fileImage;
    }


    private void deleteMosaic(FileImage image)
    {
        Icon icon = mosaicsMap.remove(image);
        mainPanel.remove(icon);
        revalidate();
        repaint();
    }
}
