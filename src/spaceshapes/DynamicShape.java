package spaceshapes;

import java.awt.Color;

public class DynamicShape extends Shape {
	private Color _color = new Color(212, 212, 212);
	
	//initially the shape is not filled
	private boolean _isFilled = false;

	/**
	 * Default constructor that creates a DynamicShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicShape() {
		super();
	}
	
	/**
	 * Default constructor that creates a DynamicShape instance whose instance
	 * variables are set to default values and color set
	 * @param color the color of the filled shape.
	 */
	public DynamicShape(Color color) {
		super();
		_color = color;
	}

	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables and color set.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 * @param color the color of the filled shape.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, Color color) {
		super(x,y,deltaX,deltaY);
		_color = color;
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}


	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables and color set.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color the color of the filled shape.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables and text and color set.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text the text assigned with the shape
	 * @param color the color of the filled shape.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height,text);
		_color = color;
	}
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. Also keeps track of when the Shape should be filled (left or right bounce)
	 * or when it shouldn't (initial or top and bottom bounce)
	 * 
	 * Note when shape bounces of a corner i.e. left and bottom at the same time, 
	 * the shape will be unfilled
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		super.move(width, height);
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;
		if (nextX <= 0 || nextX + _width >= width) {
			_isFilled = true;
		}
		
		if (nextY <= 0 || nextY + _height >= height) {
			_isFilled = false;
		}
	}


	/**
	 * Paints this DynamicShape object using the supplied Painter object.
	 * text is also painted if present
	 */
	protected void doPaint(Painter painter) {
		Color defaultColor = painter.getColor();		
		if(_isFilled) {
			painter.setColor(_color);
			painter.drawRect(_x,_y,_width,_height);
			painter.fillRect(_x, _y, _width, _height);
			painter.setColor(defaultColor);
		}
		else {
			painter.drawRect(_x,_y,_width,_height);
		}
	}
}
