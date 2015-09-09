/*
 * ____________________________________________________
 * 
 * Copyright (c) 2001, CoveComm Inc.                         
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *
 * Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following disclaimer.
 *
 * Redistributions in binary form must reproduce the above 
 * copyright notice, this list of conditions and the 
 * following disclaimer in the documentation and/or other 
 * materials provided with the distribution.
 *
 * Neither the name of CoveComm Inc. nor the names of 
 * contributors may be used to endorse or promote products 
 * derived from this software without specific prior 
 * written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS 
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE LIABLE FOR 
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS 
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY 
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * ____________________________________________________
 *
 * This class is part of the DEWEB package for 
 * database-enabled web development. For more information
 * on DEWEB see http://www.covecomm.com/java
 *
 */
 
package com.covecomm.deweb.graph;

import java.awt.*;
 
import java.util.*;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;
import java.io.*;

/** A class that creates a small bar graph.
 *
 * Used by FormSurveyBean. On Linux/Unix machines you
 * will need to have XWindows running, or you'll need
 * the Xvfb virtual frame buffer.
 */

public class GraphBean {

class Bar {
	private float value;
	private String name;
	private int height;
	private int width;
	private int maxWidth;
	private Color barColor;
	private String barName;
	private float barvalue;
	private float barValue;

public java.awt.Color getBarColor() {
	return barColor;
}
public java.lang.String getBarName() {
	return barName;
}
public float getBarValue() {
	return barValue;
}
public void setBarColor(java.awt.Color newBarColor) {
	barColor = newBarColor;
}
public void setBarName(java.lang.String newBarName) {
	barName = newBarName;
}
public void setBarValue(float newBarValue) {
	barValue = newBarValue;
}
};
	private HashMap bars;
	private Font font;
	private int inset=5;
	private int width;
	private int height;
	private float maxValue=100;
public GraphBean() {
	super();
	font = new Font("sansserif",Font.BOLD,10);
	bars = new HashMap(); 
}
public void addBar(int newID, String newName, float newValue, Color newColor) {
	Integer ID = new Integer(newID);
	Bar bar = (Bar)bars.get(ID);
	if (bar == null) {
		bar = new Bar();
		bars.put(ID,bar);
		System.out.println("created new bar");
	}
	bar.setBarName(newName);
	bar.setBarValue(newValue);
	System.out.println("set bar value to " + bar.getBarValue());
	bar.setBarColor(newColor);
}
public void draw(Graphics g) {
	if (bars.isEmpty()) return;
	// set the font
	
	g.setFont(font);
	
	// getHeight actually calculates the appropriate height
	setHeight(getHeight());
	
	// create a new graphics object of the correct size
	g = g.create(0,0,width,height);
		
	// draw the graph foreground
	g.setColor(fgColor);
	g.fillRect(0,0,width,height);

	// set the start Y position
	int yPos=inset;

	// draw the bars
	Bar bar;
	int barWidth = width - (inset*2) - 2;
	int xPos = inset;
	Iterator iterator = bars.values().iterator();
	while (iterator.hasNext()) {
		bar = (Bar)iterator.next();
		g.setColor(bar.getBarColor());
		
		// calcuate the bar width
		if (bar.getBarValue() != 0) {
			int thisBarWidth = (int)(barWidth * (bar.getBarValue() / maxValue));
			g.fill3DRect(xPos,yPos,thisBarWidth,barHeight,true);
		}
		yPos += (barHeight + inset);
		
	}

	// draw a box around the bars
	g.setColor(Color.black);
	//g.drawRect(inset,inset,barWidth,(bars.size() * (barHeight+inset)) - inset);
	g.drawRect(inset,inset-3,barWidth,(bars.size() * (barHeight+inset)) - inset + 6);

	// draw the legend
	yPos += inset;
	iterator = bars.values().iterator();
	while (iterator.hasNext()) {
		bar = (Bar)iterator.next();
		g.setColor(bar.getBarColor());
		g.fill3DRect(xPos,yPos+3,legendHeight-6,legendHeight-6,true);
		g.setColor(Color.black);
		g.drawString(bar.getBarName(),xPos+legendHeight,yPos+legendHeight-3);
		yPos += (legendHeight + inset);
	}

	// draw the footer
	g.drawString(getFooter(),xPos+legendHeight,yPos+legendHeight-3);
	
}
public float getMaxValue() {
	return maxValue;
}
public void setMaxValue(float newMaxValue) {
	maxValue = newMaxValue;
}
	private int barHeight=16;	private java.awt.Color bgColor=Color.gray;	private java.awt.Color fgColor=Color.white;	private HashMap fontHeights;	private String footer=null;	private int legendHeight=12;	private int tics;public String createJpg(String dirName) {
	File tempJpg=null;
	FileOutputStream outJpg=null;
	Frame frame = null;
	Graphics g = null;
	try {
		// create the jpg file
		File tempDir = new File(dirName);
		tempJpg = File.createTempFile("survey-", ".jpg", tempDir);
		
		// prepare to draw the jpg
		frame = new Frame();
		frame.addNotify();
		
		// Get a graphics region, using the Frame
		Image image = frame.createImage(getWidth(),getHeight());
		g = image.getGraphics();
		
		// draw the graph
		draw(g);
		
		// create an output stream
		outJpg = new FileOutputStream(tempJpg);
		JPEGImageEncoder jpg = JPEGCodec.createJPEGEncoder(outJpg);
		
		// set the image quality to max
		JPEGEncodeParam param = jpg.getDefaultJPEGEncodeParam((BufferedImage) image);
		param.setQuality(0.95f, false);
		jpg.setJPEGEncodeParam(param);
		
		// encode the image
		jpg.encode((BufferedImage) image);
		outJpg.close();

	} catch (IOException e) {
		System.out.print("IO Exception creating temp htm file " + e.toString());
	} catch (Exception e) {
		System.out.println("exception " + e.toString());
	} finally {
		if (g != null)
			g.dispose();
		if (frame != null)
			frame.removeNotify();
	}
	if (tempJpg != null) return (tempJpg.getName());
	else return "";
}
public int getBarHeight() {
	return barHeight;
}
public java.util.HashMap getBars() {
	return bars;
}
public java.awt.Color getBgColor() {
	return bgColor;
}
public java.awt.Color getFgColor() {
	return fgColor;
}
public java.awt.Font getFont() {
	return font;
}
public java.lang.String getFooter() {
	return footer;
}
public int getHeight() {
	height = (bars.size() * (barHeight + (inset * 2) + legendHeight))
		+ (inset * 3);
	if ((footer != null) && (!footer.equals("")))
		height += (legendHeight + inset);
	return height;
}
public int getInset() {
	return inset;
}
public int getLegendHeight() {
	return legendHeight;
}
public int getTics() {
	return tics;
}
public int getWidth() {
	return width;
}
public void setBarHeight(int newBarHeight) {
	barHeight = newBarHeight;
}
public void setBars(java.util.HashMap newBars) {
	bars = newBars;
}
public void setBgColor(java.awt.Color newBgColor) {
	bgColor = newBgColor;
}
public void setFgColor(java.awt.Color newFgColor) {
	fgColor = newFgColor;
}
public void setFont(java.awt.Font newFont) {
	font = newFont;
}
public void setFooter(java.lang.String newFooter) {
	footer = newFooter;
}
public void setHeight(int newHeight) {
	height = newHeight;
}
public void setInset(int newInset) {
	inset = newInset;
}
public void setLegendHeight(int newLegendHeight) {
	legendHeight = newLegendHeight;
}
public void setTics(int newTics) {
	tics = newTics;
}
public void setWidth(int newWidth) {
	width = newWidth;
}
}