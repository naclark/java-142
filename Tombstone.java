import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Font; // Access the Font class so I can scale the text
import java.awt.Color; // access the Color class

/**
 * Creates a Tombstone object in a graphics window.
 * @author Nick Clark
 *
 */
public class Tombstone {
	// Instance fields.
	// The graphics window this Tombstone belongs to
	private GWindow window;

	// The center of this Tombstone
	private int x;
	private int y;

	// The scale used to draw this Tombstone
	private double scale;

	// Default size of a generic Tombstone.
	// This refers to the half-oval's height (100).
	// The width is 3/4 of the height (75).
	private final int SIZE = 100;
	
	// Shapes making up the tombstone.
	private Arc tombstone;
	private TextShape rip;

	// Scaled font size.
	private int fontSize;
	private Font scaledFont;
	
	// Is the tombstone right-side up?
	private boolean isUp = true;

	/**
	 * Creates a tombstone centered at location (x,y) in the GWindow window. The
	 * size of the tombstone is the default size times scale.
	 * @param x X coordinate of the tombstone.
	 * @param y Y coordinate of the tombstone.
	 * @param scale Scale (compared to 100 pixels of height) of the tombstone.
	 * @param window Window the tombstone is drawn in.
	 */
	public Tombstone(int x, int y, double scale, GWindow window) {
		// Initialize instance fields.
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		fontSize = (int) (scale * 12);
		scaledFont = new Font("Monospaced", Font.BOLD, fontSize);
		draw();
	}
	
	/**
	 * Flips the tombstone upside down.
	 * The text changes because the UW graphics library can only rotate the text
	 * position, not the text itself.
	 */
	public void doAction() {
		window.remove(tombstone); // Remove the elements of the tombstone.
		window.remove(rip);
		isUp = !isUp; // Switch between true and false
		draw(); // Redraw the tombstone.
	}
	
	/**
	 * Draws the tombstone in the graphics window at x and y and
	 * at the given scale.
	 */
	private void draw() {
		// Convert size of tombstone to integer.
		int s = (int) (scale * SIZE);
		// Construct the arc of the tombstone.
		if (isUp) { // Right side up.
			tombstone = new Arc(x - (int)(3*s / 8), y - s, (int)(s * 0.75), 2*s, 0, 180, Color.WHITE,
				true);
			rip = new TextShape("R.I.P.",x-(int)(s/6), y-(int)(s/2), Color.GRAY, scaledFont);
		} else { // It's flipped. 
			tombstone = new Arc(x - (int)(3*s / 8), y - s, (int)(s * 0.75), 2*s, 180, 180, Color.WHITE,
					true);
			rip = new TextShape("Too bad",x-(int)(s/4), y+(int)(s/4), Color.GRAY, 
					scaledFont);
		}
		// Put the tombstone and text in the window
		window.add(tombstone);
		window.add(rip);
	}
}
