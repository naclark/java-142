import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>
 * Create a candle in a graphics window
 * </p>
 * 
 * @author Nick Clark
 */

public class Candle {

	// Instance fields
	// The graphics window this Candle belongs to
	private GWindow window;

	// The center of this Candle
	private int x;

	private int y;

	// The scale used to draw this Candle
	private double scale;

	// Default size of a generic Candle.
	// A generic candle appears as a rectangle 25 (=size/2) wide
	// and 50 (=size) high.
	private final int SIZE = 50;
	
	// The flame's gotta move. Turn it into instance fields.
	private Oval flame, outFlame;
	// Direction of the flame, values -1 to 1.
	private int flameDirection;
	

	/**
	 * Creates a candle centered at location (x,y) in the GWindow window. The
	 * size of the candle is the default size times scale.
	 */
	public Candle(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields (the use of this is required
		// since the instance fields have the same name as the
		// parameters of the constructor)
		this.window = window;
		this.scale = scale;
		this.x = x;
		this.y = y;
		this.draw();
		
	}
	
	/**
	 * Makes the flame move into a few different positions based
	 * on int flameDirection.
	 */
	public void flicker() {
		// Remove the flame
		window.remove(outFlame);
		window.remove(flame);
		
		// Change flameDirection
		if (flameDirection == 1) {
			flameDirection = -1;
		} else {
			flameDirection++;
		}
		// Redraw it
		drawFlame();
	}
	
	public void drawFlame() {
		int s = (int) (scale * SIZE);		
		// Outer (red) part of it
		outFlame = new Oval(x-s/(8+2*flameDirection), y-s,s/4,s/2,Color.RED, true);
		// Inner (yellow) part of it
		flame = new Oval(x-s/(16+4*flameDirection),y-(int)(s*0.75),s/8,s/4,Color.YELLOW,true);
		window.add(outFlame);
		window.add(flame);
	}

	/**
	 * Draws in the graphics window a candle at location (x,y) with size =
	 * default size * scale
	 */
	private void draw() {
		// Size of this Candle
		// (int) converts to an int scale * this.size which is a double, e.g.
		// (int)23.8 is 23
		// This is necessary since the Rectangle constructor where we use
		// s takes integers.
		int s = (int) (scale * SIZE);

		// Select a random color for the candle
		// (int)(Math.random()*256) generates a random number between 1 and 256
		// excluded.
		// Check the documentation of the color class to see what this Color
		// constructor does.
		Color color = new Color((int) (Math.random() * 256), (int) (Math
				.random() * 256), (int) (Math.random() * 256));
		Rectangle candle = new Rectangle(x - s / 4, y - s / 2, s / 2, s, color,
				true);
		// Add a flame ...
		drawFlame();
		// Put the candle in the window
		window.add(candle);
	}
}







