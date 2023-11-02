/*
 * App.java
 *
 * created at 2023-10-22 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;

import com.formdev.flatlaf.FlatLightLaf;

/**
 * This class parses all command line parameters and creates a mosaique.
 */
public class App
{
    public static void main(String[] args)
    {
        FlatLightLaf.setup();
        MosaicController mosaiqueController = new MosaicController();
        mosaiqueController.initialize();
    }
}
