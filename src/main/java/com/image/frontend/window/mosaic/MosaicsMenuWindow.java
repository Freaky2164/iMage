/*
 * MosaicsMenuWindow.java
 *
 * created at 2023-10-30 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.mosaic;


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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import com.image.domain.entities.Mosaic;
import com.image.domain.service.MosaicController;
import com.image.implementation.mosaic.base.ImageUtils;


public class MosaicsMenuWindow extends JFrame implements MosaicIconViewer
{
    private static final long serialVersionUID = 1L;
    private Map<Mosaic, MosaicsIcon> mosaicsMap = new HashMap<>();
    private Mosaic selectedMosaic;
    private JPanel mainPanel;

    public MosaicsMenuWindow(MosaicController mosaicController)
    {
        this.setSize(800, 500);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("iMage - Mosaics Menu");
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.setBackground(Color.GRAY);

        List<Mosaic> mosaics = mosaicController.getMosaics();
        for (Mosaic mosaic : mosaics)
        {
            ImageIcon imageIcon = new ImageIcon(ImageUtils.scale(mosaic.getImage(), 250, 250));
            MosaicsIcon icon = new MosaicsIcon(this, mosaic, imageIcon);
            mainPanel.add(icon);
            mosaicsMap.put(mosaic, icon);
        }

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        menuPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        JButton deleteButton = new JButton("Delete Mosaic");
        deleteButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteButton.addActionListener(e ->
        {
            mosaicController.deleteMosaic(selectedMosaic);
            removeFromView(selectedMosaic);
        });

        JButton showButton = new JButton("Show Mosaic");
        showButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showButton.addActionListener(e ->
        {
            try
            {
                if (selectedMosaic != null)
                {
                    File file = new File(selectedMosaic.getPath());
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                }
            }
            catch (IOException exception)
            {
                System.err.println("Cannot open file in system explorer: " + exception.getMessage());
            }
        });

        menuPanel.add(deleteButton);
        menuPanel.add(showButton);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    public void deselectMosaic()
    {
        MosaicsIcon icon = mosaicsMap.get(selectedMosaic);
        selectedMosaic = null;
        if (icon != null)
        {
            icon.deselect();
        }
    }


    public void setSelectedMosaic(Mosaic mosaic)
    {
        selectedMosaic = mosaic;
    }


    public void removeFromView(Mosaic mosaic)
    {
        if (mosaic != null)
        {
            MosaicsIcon icon = mosaicsMap.remove(mosaic);
            mainPanel.remove(icon);
            revalidate();
            repaint();
        }
    }
}
