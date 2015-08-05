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
 
package com.covecomm.deweb.util;


import java.io.*;
import java.awt.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;
import java.awt.font.*;
import java.util.HashMap;
import com.covecomm.deweb.graph.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** A servlet for testing graphing */
				
public class GraphTest extends javax.servlet.http.HttpServlet {
	private HashMap fontHeights;

public GraphTest() {
	super();
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	Calendar cal = new GregorianCalendar();
	cal.set(2000, 11, 1,0,0,0);
	System.out.println("\nNew time:" + cal.getTime());
	cal.set(Calendar.DAY_OF_WEEK,1);
	System.out.println("adjusted time:" + cal.getTime());

/*		

	Calendar cal1 = new GregorianCalendar();
	Calendar cal2 = new GregorianCalendar();
	cal1.set(2000, 11, 27,0,0,0);
//	System.out.println("\nmonth " + cal.get(Calendar.MONTH));
//	cal.set(Calendar.DAY_OF_WEEK,1);
//	System.out.println("month " + cal.get(Calendar.MONTH));
	Date firstDate = cal1.getTime();
	System.out.println("\n\nfirstDate " + firstDate);
	System.out.println("firstDate " + cal1.getTime());
	System.out.println("year " + cal1.get(Calendar.YEAR));
	System.out.println("month " + cal1.get(Calendar.MONTH));
	System.out.println("day " + cal1.get(Calendar.DAY_OF_MONTH));
	System.out.println("day of week " + cal1.get(Calendar.DAY_OF_WEEK));
	System.out.println("day of week in month" + cal1.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	
	cal2.set(2000,11,15,0,0,0);
	System.out.println("\nfirstDate " + firstDate);
	System.out.println("firstDate " + cal2.getTime());
	System.out.println("year " + cal2.get(Calendar.YEAR));
	System.out.println("month " + cal2.get(Calendar.MONTH));
	System.out.println("day " + cal2.get(Calendar.DAY_OF_MONTH));
	System.out.println("day of week " + cal2.get(Calendar.DAY_OF_WEEK));
	System.out.println("day of week in month" + cal2.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	Date secondDate = cal2.getTime();
	System.out.println("secondDate " + cal2.getTime());
	if (firstDate.compareTo(secondDate) >= 0) {
		System.out.println("secondDate is before first - adjusting secondDate to first of week");
		// adjust time to the beginning of the week
		cal2.set(Calendar.DAY_OF_WEEK,1);
	} else {
		System.out.println("secondDate is after first - adjusting secondDate to first of month");
		// adjust time to the beginning of the month
		cal2.set(Calendar.DAY_OF_MONTH, 1);
	}
	System.out.println("adjusted date " + cal2.getTime());

	return;

	Frame frame = null;
		Graphics g = null;
		try {
			GraphBean graph = new GraphBean();
			graph.setWidth(100);
			graph.setHeight(200);
			graph.setFgColor(new Color(230,225,247));
			graph.addBar(1,"One",34,new Color(224,221,242));
			graph.addBar(2,"Two",44,Color.blue);
			graph.addBar(3,"Three",14,Color.green);
			graph.setLegendHeight(getLineHeight(graph.getFont()));
			System.out.println("calling draw");
			graph.createJpg(getServletContext().getRealPath("/"));
		} catch (Exception e) {
			System.out.println("exception " + e.toString());
		} finally {
			if (g != null)
				g.dispose();
			if (frame != null)
				frame.removeNotify();
		}
		*/

}
public void doGet2(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	ServletOutputStream out = response.getOutputStream(); 
	Frame frame = null;
	Graphics g = null;
	try {
		// Create an unshown frame
		frame = new Frame();
		frame.addNotify();

		// Get a graphics region, using the Frame
		Image image = frame.createImage(400, 60);
		g = image.getGraphics();
		Color color = new Color(1235);
		g.setColor(Color.blue);
		g.fill3DRect(10,10,300,40,true);
		 
		g.setColor(Color.white);

		// Draw "Hello World!" to the off screen graphics context
		g.setFont(new Font("Serif", Font.ITALIC, 36));
		g.drawString("Bam! It works!", 23, 40);
		// Encode the off screen image into a GIF and send it to the client
		response.setContentType("image/jpeg");

		JPEGImageEncoder jpg = JPEGCodec.createJPEGEncoder(out);
		System.out.println("created encoder " + jpg);
		jpg.encode((BufferedImage) image);
		System.out.println("done!");
	} catch (Exception e) {
		System.out.println("exception " + e.toString());
	} finally {
		if (g != null)
			g.dispose();
		if (frame != null)
			frame.removeNotify();
	}

}
public void doGet3(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	ServletOutputStream out = response.getOutputStream(); 
	Frame frame = null;
	Graphics g = null;
	try {
		// Create an unshown frame
		frame = new Frame();
		frame.addNotify();
		// Get a graphics region, using the Frame
		Image image = frame.createImage(400, 600);
		g = image.getGraphics();
		GraphBean graph = new GraphBean();
		graph.setWidth(100);
		graph.setHeight(200);
		//graph.setFgColor(new Color(165,177,177));
		graph.setFgColor(new Color(230,225,247));
		graph.addBar(1,"One",34,new Color(224,221,242));
		graph.addBar(2,"Two",44,Color.blue);
		graph.addBar(3,"Three",14,Color.green);
		System.out.println("calling draw");
		graph.draw(g);
		response.setContentType("image/jpeg");
		JPEGImageEncoder jpg = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = jpg.getDefaultJPEGEncodeParam(
	        (BufferedImage) image);
		param.setQuality(1.0f, false);
		jpg.setJPEGEncodeParam(param);


		
		jpg.encode((BufferedImage) image);
	} catch (Exception e) {
		System.out.println("exception " + e.toString());
	} finally {
		if (g != null)
			g.dispose();
		if (frame != null)
			frame.removeNotify();
	}

}
public void doGet4(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	ServletOutputStream out = response.getOutputStream(); 
	Frame frame = null;
	Graphics g = null;
	try {
		GraphBean graph = new GraphBean();
		graph.setWidth(100);
		graph.setHeight(200);
		//graph.setFgColor(new Color(165,177,177));
		graph.setFgColor(new Color(230,225,247));
		graph.addBar(1,"One",34,new Color(224,221,242));
		graph.addBar(2,"Two",44,Color.blue);
		graph.addBar(3,"Three",14,Color.green);
		graph.setLegendHeight(getLineHeight(graph.getFont()));
		System.out.println("calling draw");
		// Create an unshown frame
		frame = new Frame();
		frame.addNotify();
		// Get a graphics region, using the Frame
		Image image = frame.createImage(400, 600);
		g = image.getGraphics();
		graph.draw(g);
		response.setContentType("image/jpeg");
		JPEGImageEncoder jpg = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = jpg.getDefaultJPEGEncodeParam(
	        (BufferedImage) image);
		param.setQuality(1.0f, false);
		jpg.setJPEGEncodeParam(param);


		
		jpg.encode((BufferedImage) image);
	} catch (Exception e) {
		System.out.println("exception " + e.toString());
	} finally {
		if (g != null)
			g.dispose();
		if (frame != null)
			frame.removeNotify();
	}

}
public int getLineHeight(Font font) {
	if (fontHeights == null)
		fontHeights = new HashMap();
	Float fontHeight = (Float) fontHeights.get(font.toString());
	if (fontHeight == null) {
		Frame frame = null;
		Graphics g = null;
		try {
			// Create an unshown frame
			frame = new Frame();
			frame.addNotify();
			// Get a graphics region, using the Frame
			Image image = frame.createImage(300, 300);
			g = image.getGraphics();

			g.setFont(font);
			FontMetrics fm = g.getFontMetrics(font);
			LineMetrics lm = fm.getLineMetrics("Gg", 0, 1, g);
			fontHeight = new Float(lm.getHeight());
		} catch (Exception e) {
			System.out.println("exception " + e.toString());
		} finally {
			if (g != null)
				g.dispose();
			if (frame != null)
				frame.removeNotify();
		}
		fontHeights.put(font.toString(),fontHeight);
	} 
	return fontHeight.intValue();
}
}