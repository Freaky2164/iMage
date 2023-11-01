/*
 * MetadataPanel.java
 *
 * created at 2023-10-29 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.Color;

import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.repository.FileImage;


public class MetadataPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    public MetadataPanel()
    {
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
    }


    public void showMetadata(FileImage image)
    {

    }
}
