package com.image.frontend.window.main;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.image.domain.entities.ImageAggregate;


/**
 * Panel displaying specific information about a selected image
 */
public class MetadataPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JLabel fileNameLabel;
    private JLabel fileSizeLabel;
    private JLabel numberOfMosaicsLabel;
    private JButton showExplorerButton;
    private ImageAggregate imageAggregate;

    public MetadataPanel()
    {
        setBorder(new FlatLineBorder(getInsets(), Color.BLACK));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);

        fileNameLabel = new JLabel();
        fileNameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        fileSizeLabel = new JLabel();
        fileSizeLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        numberOfMosaicsLabel = new JLabel();
        numberOfMosaicsLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showExplorerButton = new JButton("Open Image");
        showExplorerButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        showExplorerButton.setVisible(false);
        showExplorerButton.addActionListener(e ->
        {
            try
            {
                File file = new File(imageAggregate.getPath());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
            catch (IOException exception)
            {
                System.err.println("Cannot open file in system explorer: " + exception.getMessage());
            }
        });

        add(fileNameLabel, c);
        c.gridy = 1;
        add(fileSizeLabel, c);
        c.gridy = 2;
        add(numberOfMosaicsLabel, c);
        c.gridy = 3;
        add(showExplorerButton, c);
    }


    public void showMetadata(ImageAggregate imageAggregate)
    {
        this.imageAggregate = imageAggregate;
        fileNameLabel.setText("Name: " + imageAggregate.getName());
        fileSizeLabel.setText("Image Size: " + imageAggregate.getImage().getWidth() + " x " + imageAggregate.getImage().getHeight());
        numberOfMosaicsLabel.setText("Number of Mosaics: " + imageAggregate.getMosaics().size());
        showExplorerButton.setVisible(true);
    }


    public void resetView()
    {
        imageAggregate = null;
        fileNameLabel.setText("");
        fileSizeLabel.setText("");
        numberOfMosaicsLabel.setText("");
        showExplorerButton.setVisible(false);
    }
}
