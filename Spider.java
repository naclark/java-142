import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>
 * Create a spider in a graphics window
 * </p>
 * 
 * @author Nick Clark
 */

public class Spider {

	// The graphics window this spider belongs to
	private GWindow window;
	// The center of this Spider's lower body.
	private int x;
	private int y;
	// The scale used to draw this Spider
	private double scale;
	// Default radius of a generic Spider's lower body.
	private final int SIZE = 50;
	
	// Color of the spider's body. Can be brown or gray.
	private Color bodyColor;
	
	// Is the spider moving up or down? Up by default.
	private boolean isMovingUp = true;
	
	// Shapes making up the spider: two Ovals and 9 Lines.
	private Oval head, body;
	private Line strand, leg1, leg2, leg3, leg4, leg5, leg6, leg7, leg8;

	/**
	 * Creates a spider, with its lower body centered at location (x,y) in 
	 * the GWindow window. The size of the spider is the default size times scale.
	 */
	public Spider(int x, int y, double scale, GWindow window) {
		// Initialize instance fields.
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		
		// Randomly choose brown or gray.
		this.bodyColor = (Math.random() < 0.5) ? new Color(150,90,70) : Color.GRAY;
		
		// Draw it.
		draw();
	}
	
	/**
	 * Moves the spider up or down by 3 pixels. If it hits the top or
	 * bottom of the window, it reverses direction. Moves up by default. 
	 */
	public void move() {
		// update the location of the spider
		if (isMovingUp) {
			y -= 3;
		} else { // The spider last hit the top of the screen.
			y += 3;
		}
		// Is the spider above the screen or below the screen?
		// Then change isMovingUp.
		if (y < 0) {
			isMovingUp = false;
		}
		if (y > window.getWindowHeight()) {
			isMovingUp = true;
		}
		
		// erase the spider
		removeSpider();
		// draw the spider at the new location
		draw();
	}
	
	/**
	 * Removes the shapes making up the spider from the window.
	 */
	private void removeSpider() {
		window.remove(head);
		window.remove(body);
		window.remove(leg1);
		window.remove(leg2);
		window.remove(leg3);
		window.remove(leg4);
		window.remove(leg5);
		window.remove(leg6);
		window.remove(leg7);
		window.remove(leg8);
		window.remove(strand);	
	}

	/**
	 * Draws in the graphics window a spider at location (x,y).
	 */
	private void draw() {
		// Draw legs and silk strand.
		legsAndStrand();
		// Draw body and head.
		drawBody();
	}
	
	/**
	 * Draws the two sections of the spider's body. These are two gray
	 * circles; the head is smaller than the body.
	 */
	private void drawBody() {
		// Create and draw the body
		int r = (int) (scale * SIZE);
		body = new Oval(x-r, y-r, 2*r, 2*r, bodyColor, true);
		window.add(body);
		// Create and draw the head. Account for difference in radius.
		r = (int)(r/2);
		head = new Oval(x-r, y-3*r, 2*r, 2*r, bodyColor, true);
		window.add(head);
		}
	
	/**
	 * Draws eight lines representing spider legs.
	 * These have a length of about 100 by default.
	 * Also draws a line to the top of the window representing the
	 * silk strand.
	 */
	private void legsAndStrand() {
		// Create and draw the silk strand to the top of the window.
		strand = new Line(x,y,x,0,Color.WHITE);
		window.add(strand);
		
		// There are two offsets for x and y, and they can be added or
		// subtracted to produce 4 unique values. These are based on
		// sine and cosine of 36 and 72 degrees multiplied by radius.
		int offsetX1 = (int)(59*scale);
		int offsetX2 = (int)(95*scale);
		int offsetY1 = (int)(81*scale);
		int offsetY2 = (int)(31*scale);
		
		// Create and draw these legs.
		leg1 = new Line(x,y,x+offsetX1,y+offsetY1,bodyColor);
		window.add(leg1);
		leg2 = new Line(x,y,x-offsetX1,y+offsetY1,bodyColor);
		window.add(leg2);
		leg3 = new Line(x,y,x+offsetX1,y-offsetY1,bodyColor);
		window.add(leg3);
		leg4 = new Line(x,y,x-offsetX1,y-offsetY1,bodyColor);
		window.add(leg4);
		leg5 = new Line(x,y,x+offsetX2,y+offsetY2,bodyColor);
		window.add(leg5);
		leg6 = new Line(x,y,x-offsetX2,y+offsetY2,bodyColor);
		window.add(leg6);
		leg7 = new Line(x,y,x+offsetX2,y-offsetY2,bodyColor);
		window.add(leg7);
		leg8 = new Line(x,y,x-offsetX2,y-offsetY2,bodyColor);
		window.add(leg8);
	}
	
	}