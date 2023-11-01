/*
 * MenuPanel.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app.window.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.app.MosaiqueController;
import com.image.app.repository.FileImage;
import com.image.app.window.mosaique.MosaiquesWindow;


public class MenuPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JButton addImageButton;
    private JButton deleteImageButton;
    private JButton showMosaiquesButton;
    private JTextField imageWidthField;
    private JTextField imageHeightField;
    private JComboBox<String> shapesComboBox;
    private JButton createMosaiqueButton;

    public MenuPanel(MosaiqueController mosaiqueController)
    {
        this.setLayout(new GridBagLayout());
        this.setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = 1;

        addImageButton = new JButton("Add new Image");
        addImageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        addImageButton.addActionListener(e ->
        {
            FileImageUploader fileUploader = new FileImageUploader();
            int returnVal = fileUploader.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileUploader.getSelectedFile();
                mosaiqueController.addImage(selectedFile);
            }
        });
        add(addImageButton, c);

        c.gridy = 2;
        deleteImageButton = new JButton("Delete Image");
        deleteImageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteImageButton.addActionListener(e ->
        {
            FileImage selectedImage = mosaiqueController.getSelectedImage();
            mosaiqueController.deleteImage(selectedImage);
        });
        add(deleteImageButton, c);

        c.gridy = 3;
        showMosaiquesButton = new JButton("Show Mosaiques");
        showMosaiquesButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showMosaiquesButton.addActionListener(e -> new MosaiquesWindow(mosaiqueController.getSelectedImage()));
        add(showMosaiquesButton, c);

        c.gridy = 4;
        c.insets = new Insets(20, 10, 20, 10);
        imageWidthField = new JTextField("5");
        imageWidthField.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        imageWidthField.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageWidthField, c);

        c.gridy = 5;
        imageHeightField = new JTextField("5");
        imageHeightField.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        imageHeightField.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageHeightField, c);

        c.gridy = 6;
        c.insets = new Insets(10, 10, 10, 10);
        String[] shapeNames = { "Rectangle", "Triangle", "Crossed" };
        shapesComboBox = new JComboBox<>(shapeNames);
        shapesComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        add(shapesComboBox, c);

        c.gridy = 7;
        createMosaiqueButton = new JButton("Create Mosaique");
        createMosaiqueButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        createMosaiqueButton.addActionListener(e ->
        {
            int widthInput = Integer.parseInt(imageWidthField.getText());
            int heightInput = Integer.parseInt(imageHeightField.getText());
            if (validateInput(widthInput, heightInput))
            {
                mosaiqueController.createMosaique(widthInput, heightInput, shapesComboBox.getSelectedItem().toString());
            }
        });
        add(createMosaiqueButton, c);
    }


    public void activateMosaiqueCreation()
    {

    }


    private boolean validateInput(int widthInput, int heightInput)
    {
        if (!(widthInput > 0 && widthInput < 10))
        {
            imageWidthField.setForeground(Color.red);
            return false;
        }
        if (!(heightInput > 0 && heightInput < 10))
        {
            imageHeightField.setForeground(Color.red);
            return false;
        }
        return true;
    }
}
