package spaceshapes;

import java.awt.Color;
import java.awt.Image;

//import java.awt.Color;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Draws a filled rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */	
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * gets the current color of the shape
	 * @return the Color 
	 */
	public Color getColor();

	/**
	 * sets the color of the shape
	 * @param c the color you are setting the shape to
	 */
	public void setColor(Color c);
	
    /**
     * Translates the origin of the graphics context to the point(x, y) in the current 
     * coordinate system.Modifies this graphics context so that its new origin corresponds
     * to the point (x, y) in this graphics context'soriginal coordinate system. 
     * All coordinates used in subsequent rendering operations on this graphics context will 
     * be relative to this new origin.
     * @param x the x coordinate
     * @param y the y coordinate
     */
	public void translate(int x, int y);
	
	/**
	 * Draws a text in the center of the specified a rectangle.
	 * Parameter text specifies the wanted text that is drawn. 
	 * Parameters x and y specify the top left corner of the rectangle. 
	 * Parameters width and height specify its width and height.
	 */
	public void drawCenteredText(String text, int x, int y, int width, int height);
	
	/**
	 * Draws an image. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawImage(Image img, int x, int y, int width, int height);
	
}
