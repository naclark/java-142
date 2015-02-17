import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>
 * Create a pumpkin in a graphics window
 * </p>
 * 
 * @author Nick Clark
 */

public class Pumpkin {
	// Instance fields.
	// The graphics window this Pumpkin belongs to
	private GWindow window;

	// The center of this Pumpkin
	private int x;
	private int y;
	// The scale used to draw this Pumpkin
	private double scale;
	// Default size of a generic Pumpkin.
	// It appears as a face in an oval a little wider(1.25x) than it is tall.
	// This constant refers to the height.
	private final int SIZE = 50;
	
	// Shapes that make up the Pumpkin.
	private Oval pumpkin, yellowCircle, orangeCircle, nose;
	private Triangle leftEye, rightEye;
	private Arc stem;
	
	// Is the pumpkin getting larger or smaller?
	private boolean growing = true;
		
	/**
	 * Creates a pumpkin centered at location (x,y) in the GWindow window. The
	 * size of the pumpkin is the default size times scale.
	 */
	public Pumpkin(int x, int y, double scale, GWindow window) {
		// Initialize instance fields.
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		draw();
		
	}
	
	/**
	 * Erases the current pumpkin.
	 */
	private void erasePumpkin() {
		window.remove(pumpkin);
		window.remove(orangeCircle);
		window.remove(yellowCircle);
		window.remove(nose);
		window.remove(leftEye);
		window.remove(rightEye);
		window.remove(stem);
	}
	
	/**
	 * Changes the color of the eyes, nose and mouth between
	 * yellow and red.
	 */
	public void flipEyeNoseMouthColor() {
		// It should only be necessary to check one element's
		// color.
		if (yellowCircle.getColor() == Color.YELLOW) {
			// It's already yellow.
			yellowCircle.setColor(Color.RED);
			nose.setColor(Color.RED);
			leftEye.setColor(Color.RED);
			rightEye.setColor(Color.RED);
		} else { // It's red.
			yellowCircle.setColor(Color.YELLOW);
			nose.setColor(Color.YELLOW);
			leftEye.setColor(Color.YELLOW);
			rightEye.setColor(Color.YELLOW);
		}
	}

	/**
	 * Increases or decreases the size of the pumpkin.
	 * The scale begins increasing when it goes below 0.5,
	 * and decreasing when it goes above 2.0.
	 */
	public void changeSize() {
		// Adjust the scale.
		if (growing) { // It's growing.
			scale *= 1.1;
		} else { // It's getting smaller. 
			scale /= 1.1;
		}
		// If needed, switch the "growing" boolean.
		if (scale > 2.0) { // It's getting too big.
			growing = false;
		}
		if (scale < 0.5) { // It's getting too small.
			growing = true;
		}
		erasePumpkin(); // Remove old pumpkin.
		draw(); // Draw new one.
	}
	
	/**
	 * Draws in the graphics window a pumpkin at location (x,y) with size =
	 * default size * scale
	 */
	private void draw() {
		// The pumpkin itself: oval, 100 tall, 125 wide by default.
		int r = (int) (scale * SIZE);
		int r2 = (int) (scale * SIZE * 1.25);
		pumpkin = new Oval(x-r2,y-r,2*r2,2*r,Color.ORANGE,true);
		// Add to window
		window.add(pumpkin);
		
		// Draw the mouth:
		drawMouth(x,y+3*r/4);
		
		// The eyes, left:
		drawEye(x-r2/2,y-r/2);
		// and right:
		drawEye(x+r2/2,y-r/2);
		
		// The nose:
		drawNose();
		
		// A stem:
		drawStem();
	}
	
	/**
	 * Draws a mouth.
	 * 
	 * @param mouthx
	 *            the x coordinate of the middle bottom point of the mouth
	 * @param mouthy
	 *            the y coordinate of the middle bottom point of the mouth
	 */
	private void drawMouth(int mouthx, int mouthy) {
		// Draw two circles (one yellow and one orange)
		// The orange circle is on top of the yellow circle and slightly shifted
		// up
		int r = (int) (scale * this.SIZE * 0.8);
		yellowCircle = new Oval(mouthx - r, mouthy - 2 * r, 2 * r, 2 * r,
				Color.YELLOW, true);
		window.add(yellowCircle);
		orangeCircle = new Oval(mouthx - r, mouthy - 2 * r, 2 * r, 2 * r,
				Color.ORANGE, true);
		orangeCircle.moveBy(0, -(int) (0.2 * r));
		window.add(orangeCircle);

	}

	/**
	 * Draws an eye.
	 * 
	 * @param eyex
	 * 		X value of the top point of the eye.
	 * @param eyey
	 * 		Y value of the top point of the eye.
	 */
	private void drawEye(int eyex, int eyey) {
		// Yellow triangle.
		int s = (int) (scale * this.SIZE);
		Triangle eye = new Triangle(eyex, eyey, eyex - s / 4, eyey + s / 4,
				eyex+s/4, eyey+s/4, Color.YELLOW, true);
		window.add(eye);
		// Assign this new eye to one of the instance fields.
		if (eyex < x) { // left eye
			leftEye = eye;
		} else {
			rightEye = eye;
		}
	}
	
	/**
	 * Draws a nose (yellow circle).
	 */
	private void drawNose() {
		int r = (int) (scale * SIZE / 6);
		nose = new Oval(x - r, y-r, 2*r, 2*r, Color.YELLOW, true);
		window.add(nose);
	}
	
	/**
	 * Draws a stem (green arc at the top of the pumpkin).
	 */
	private void drawStem() {
		int s = (int)(SIZE*scale);
		stem = new Arc(x-s/2, y-2*s, s,2*s,70,40,new Color(128,128,0),true);
		window.add(stem);
	}
	
}