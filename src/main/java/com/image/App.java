package com.image;


import com.formdev.flatlaf.FlatLightLaf;
import com.image.domain.service.MosaicController;


/**
 * This class is the entry point for the iMage application
 */
public class App
{
    public static void main(String[] args)
    {
        FlatLightLaf.setup();
        new MosaicController();
    }
}
