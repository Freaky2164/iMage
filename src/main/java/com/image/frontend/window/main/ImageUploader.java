/*
 * FileImageUploader.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.main;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Uploader for images to the application which can be used to create mosaics 
 */
public class ImageUploader extends JFileChooser
{
    private static final long serialVersionUID = 1L;

    public ImageUploader()
    {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", ".jpeg", ".png");
        setFileFilter(filter);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
}
