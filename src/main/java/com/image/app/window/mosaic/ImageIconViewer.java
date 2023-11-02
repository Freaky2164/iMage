/*
 * ImageIconViewer.java
 *
 * created at 2023-11-02 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.mosaic;


import com.image.app.repository.FileImage;


public interface ImageIconViewer
{
    void deselectImage();


    void setSelectedImage(FileImage fileImage);
}
