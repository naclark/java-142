import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class  

/**
 * <p>
 * A HalloweenScene displays pumpkins, candles, spiders and a tombstone
 * in a graphics window
 * </p>
 * 
 * @author Nick Clark
 */

public class HalloweenScene extends GWindowEventAdapter {
	/** The graphics window that displays the picture */
	private GWindow window;

	/** The elements in the picture */
	// the spider that moves up and down
	private Spider spider;

	// 2 pumpkins: one flashes, the other increases or decreases in size
	private Pumpkin flashingPumpkin, changingPumpkin;

	// A candle that flickers
	private Candle candle;

	// A tombstone that flips up and down, changing its text.
	private Tombstone myElement;

	// To keep track of the duration of the animation
	private int animationCounter;

	/**
	 * Create a picture
	 */
	public HalloweenScene() {
		// The graphics window
		this.window = new GWindow("Halloween scene");
		this.window.setExitOnClose();

		// paint the background
		Rectangle bgnd = new Rectangle(0, 0, this.window.getWindowWidth(),
				this.window.getWindowHeight(), Color.black, true);
		this.window.add(bgnd);
		this.window.doRepaint();

		// Add the graphics elements
		this.addGraphicsElements();

		// Code to do the animation
		this.window.addEventHandler(this);
		this.window.startTimerEvents(150);
	}

	// To do the animation
	public void timerExpired(GWindowEvent we) {
		this.window.suspendRepaints();
		this.spider.move();
		this.flashingPumpkin.flipEyeNoseMouthColor();
		this.changingPumpkin.changeSize();
		this.candle.flicker();
		this.myElement.doAction();
		this.window.resumeRepaints();

		// Run the animation 100 times (about 15 s)
		this.animationCounter++;
		if (this.animationCounter >= 100)
			this.window.stopTimerEvents();
	}

	/**
	 * Instantiates the elements of the scene.
	 */
	private void addGraphicsElements() {
		// You can change the coordinates and scales that appear
		// in the constructors (but don't change the names of the variables)
		this.spider = new Spider(100, 100, 1, this.window);
		this.flashingPumpkin = new Pumpkin(200, 70, 0.8, this.window);
		this.changingPumpkin = new Pumpkin(250, 250, 1.2, this.window);
		this.candle = new Candle(350, 100, 1, this.window);
		this.myElement = new Tombstone(425,200,1,this.window);
	}
	
	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new HalloweenScene();
	}

}