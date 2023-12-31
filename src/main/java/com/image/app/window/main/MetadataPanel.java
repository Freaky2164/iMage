/*
 * MetadataPanel.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.repository.FileImage;
import com.image.app.repository.RepositoryHandler;


public class MetadataPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JLabel fileNameLabel;
    private JLabel fileSizeLabel;
    private JLabel numberOfMosaicsLabel;
    private JButton showExplorerButton;
    private FileImage image;

    public MetadataPanel()
    {
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);

        fileNameLabel = new JLabel();
        fileNameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        fileSizeLabel = new JLabel();
        fileSizeLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        numberOfMosaicsLabel = new JLabel();
        numberOfMosaicsLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showExplorerButton = new JButton("Open Image");
        showExplorerButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showExplorerButton.setVisible(false);
        showExplorerButton.addActionListener(e ->
        {
            try
            {
                File file = new File(image.getPath());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
            catch (IOException exception)
            {
                System.err.println("Cannot open file in system explorer: " + exception.getMessage());
            }
        });

        add(fileNameLabel, c);
        c.gridy = 1;
        add(fileSizeLabel, c);
        c.gridy = 2;
        add(numberOfMosaicsLabel, c);
        c.gridy = 3;
        add(showExplorerButton, c);
    }


    public void showMetadata(FileImage image)
    {
        this.image = image;
        fileNameLabel.setText("Name: " + image.getName());
        fileSizeLabel.setText("Image Size: " + image.getWidth() + " x " + image.getHeight());
        numberOfMosaicsLabel.setText("Number of Mosaics: " + RepositoryHandler.getMosaics(image).size());
        showExplorerButton.setVisible(true);
    }


    public void resetView()
    {
        this.image = null;
        fileNameLabel.setText("");
        fileSizeLabel.setText("");
        numberOfMosaicsLabel.setText("");
        showExplorerButton.setVisible(false);
    }
}
