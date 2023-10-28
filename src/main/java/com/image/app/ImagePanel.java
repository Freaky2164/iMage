/*
 * ImagePanel.java
 *
 * created at 2023-10-27 by <p.faller@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.image.app;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.image.mosaique.MosaiqueMaker;


public class ImagePanel extends JPanel implements MosaiqueCreationListener
{
	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public ImagePanel() 
	{
		new MosaiqueMaker().addMosaiqueCreationListener(this);
	}
	

	@Override
	public void mosaiqueCreated(BufferedImage resultImage) 
	{
		paintImage(resultImage);
	}
	
	
	private void paintImage(BufferedImage image)
	{
		this.image = image;
		paintComponent(getGraphics());
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}
}
