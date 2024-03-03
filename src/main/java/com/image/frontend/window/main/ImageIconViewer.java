package com.image.frontend.window.main;


import java.util.UUID;


/**
 * Interface for selecting and deselecting images on the UI
 */
public interface ImageIconViewer
{
    void deselectImage();


    void setSelectedImage(UUID imageId);
}
