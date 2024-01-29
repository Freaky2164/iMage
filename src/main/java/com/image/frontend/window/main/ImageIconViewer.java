/*
 * ImageIconViewer.java
 *
 * created at 2023-11-02 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.frontend.window.main;


import java.util.UUID;


public interface ImageIconViewer
{
    void deselectImage();


    void setSelectedImage(UUID imageId);
}
