/*
 * MosaiqueWinfow.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MosaiqueWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

    private MenuPanel menuPanel;
    private ImagePanel imagePanel;

    public MosaiqueWindow()
    {
        initialize();
    }


    private void initialize()
    {
        this.setSize(800, 500);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("iMage - Mosaique Maker");
        this.setLayout(new BorderLayout());

        menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.WEST);
        imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
