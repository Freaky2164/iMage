/*
 * FileUploadWindow.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileImageUploader extends JFileChooser
{
    private static final long serialVersionUID = 1L;

    public FileImageUploader()
    {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", ".jpeg", ".png");
        this.setFileFilter(filter);
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
}
