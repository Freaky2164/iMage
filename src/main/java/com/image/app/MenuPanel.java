/*
 * MenuPanel.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MenuPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JButton addImageButton;
    private JButton uploadImageButton;
    private JTextField imageWidthField;
    private JTextField imageHeightField;
    private JMenu shapesMenu;
    private JButton createMosaiqueButton;

    public MenuPanel()
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridLayout(6, 1));

        this.addImageButton = new JButton("Add new Image");
        addImageButton.addActionListener(e -> new ImagesWindow().open());
        add(addImageButton);

        this.uploadImageButton = new JButton("Upload an image");
        uploadImageButton.addActionListener(e -> new FileUploadWindow());
        add(uploadImageButton);

        this.imageWidthField = new JTextField("5");
        this.imageWidthField.addActionListener(e -> validateInput());
        add(imageWidthField);

        this.imageHeightField = new JTextField("5");
        this.imageHeightField.addActionListener(e -> validateInput());
        add(imageHeightField);

        this.shapesMenu = new JMenu("Shapes");
        add(shapesMenu);

        this.createMosaiqueButton = new JButton("Create Mosaique");
        createMosaiqueButton.addActionListener(e -> App.createMosaique(imageWidthField.getText(), imageHeightField.getText(), "Triangle"));
        add(createMosaiqueButton);
    }

    private Object validateInput()
    {
        return null;
    }
}
