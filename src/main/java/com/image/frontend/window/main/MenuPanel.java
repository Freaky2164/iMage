package com.image.frontend.window.main;


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

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.ImageAggregate;
import com.image.domain.service.MosaicController;
import com.image.frontend.window.mosaic.MosaicsMenuWindow;


/**
 * Panel for specifing the details of the mosaic
 */
public class MenuPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JButton addImageButton;
    private JButton deleteImageButton;
    private JButton showMosaicsButton;
    private JTextField imageWidthField;
    private JTextField imageHeightField;
    private JComboBox<String> shapesComboBox;
    private JButton createMosaicButton;

    public MenuPanel(MosaicController mosaicController)
    {
        setLayout(new GridBagLayout());
        setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        setBackground(Color.GRAY.brighter());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);

        addImageButton = new JButton("Add new Image");
        addImageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        addImageButton.addActionListener(e ->
        {
            ImageUploader fileUploader = new ImageUploader();
            int returnVal = fileUploader.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileUploader.getSelectedFile();
                mosaicController.addImage(selectedFile);
            }
        });
        add(addImageButton, c);

        c.gridy = 1;
        deleteImageButton = new JButton("Delete Image");
        deleteImageButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteImageButton.addActionListener(e ->
        {
            ImageAggregate selectedImage = mosaicController.getSelectedImage();
            mosaicController.deleteImage(selectedImage);
        });
        add(deleteImageButton, c);

        c.gridy = 2;
        showMosaicsButton = new JButton("Show Mosaics");
        showMosaicsButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showMosaicsButton.addActionListener(e -> new MosaicsMenuWindow(mosaicController));
        add(showMosaicsButton, c);

        c.gridy = 3;
        c.insets = new Insets(20, 10, 20, 10);
        imageWidthField = new JTextField("5");
        imageWidthField.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        add(imageWidthField, c);

        c.gridy = 4;
        imageHeightField = new JTextField("5");
        imageHeightField.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        add(imageHeightField, c);

        c.gridy = 5;
        c.insets = new Insets(10, 10, 10, 10);
        String[] shapeNames = { "Rectangle", "Triangle", "Crossed" };
        shapesComboBox = new JComboBox<>(shapeNames);
        shapesComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        add(shapesComboBox, c);

        c.gridy = 7;
        createMosaicButton = new JButton("Create Mosaic");
        createMosaicButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        createMosaicButton.addActionListener(e ->
        {
            int widthInput = Integer.parseInt(imageWidthField.getText());
            int heightInput = Integer.parseInt(imageHeightField.getText());
            if (validateInput(widthInput, heightInput))
            {
                mosaicController.createMosaic(mosaicController.getSelectedImage(), shapesComboBox.getSelectedItem().toString(), heightInput,
                                              widthInput);
            }
        });
        add(createMosaicButton, c);
    }


    private boolean validateInput(int widthInput, int heightInput)
    {
        boolean isValid = true;
        if (!(widthInput > 0 && widthInput <= 10))
        {
            imageWidthField.setForeground(Color.RED);
            isValid = false;
        }
        else
        {
            imageWidthField.setForeground(Color.BLACK);
        }

        if (!(heightInput > 0 && heightInput <= 10))
        {
            imageHeightField.setForeground(Color.RED);
            isValid = false;
        }
        else
        {
            imageHeightField.setForeground(Color.BLACK);
        }
        return isValid;
    }
}
