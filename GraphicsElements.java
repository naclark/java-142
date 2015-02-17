import java.awt.Color;
import java.util.*;
import java.awt.Point;
import uwcse.io.*;
import uwcse.graphics.*;
import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 */

public class GraphicsElements {

	/** Maximum number of wedges in a pie */
	public static final int MAXIMUM_NUMBER_OF_PIE_WEDGES = 100;

	/** Maximum number of stripes of one color in the pattern of stripes */
	public static final int MAXIMUM_NUMBER_OF_STRIPES = 15;

	/** Maximum number of divisions in a Koch snow flake */
	public static final int MAXIMUM_NUMBER_OF_DIVISIONS = 5;

	// The window is 400 pixels wide and 300 pixels high

	/**
	 * Create the view of a pie Use filled arcs. The color of each arc is
	 * random. The pie should fill the window. Store the arcs in an ArrayList
	 * and return that ArrayList. The number of pie wedges (= arcs) is given by
	 * the user (use a dialog box). If that number is less than or equal to 0 or
	 * greater than MAXIMUM_NUMBER_OF_PIE_WEDGES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again. Make sure that no gap
	 * appears in the pie when drawing it.
	 */
	public ArrayList<Arc> createAPie() {
		// Add your code here
		Random rand = new Random();
		// Grab number of wedges.
		int wedgeNum, extraDegrees;
		Input input = new Input();
		do {
			wedgeNum = input.readIntDialog("Number of wedges (between 1 and " + MAXIMUM_NUMBER_OF_PIE_WEDGES +")");
			if (wedgeNum <= 0 || wedgeNum > MAXIMUM_NUMBER_OF_PIE_WEDGES) {
				JOptionPane.showMessageDialog(null,"Invalid value","Error",JOptionPane.ERROR_MESSAGE);
			}
		} while (wedgeNum <= 0 || wedgeNum > MAXIMUM_NUMBER_OF_PIE_WEDGES);
		
		// Make list of wedges.
		ArrayList<Arc> graphicsList = new ArrayList<Arc>();
		// How many wedges will need to be one degree larger?
		extraDegrees = 360 % wedgeNum;
		// Keep track of what degree we're at.
		int atDegree = 0;
		for (int i = 0; i < wedgeNum; i++) {
			Color c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
			// If there are still "extra degrees"...
			if (extraDegrees > 0) {
				Arc p = new Arc(0,0,400,300,atDegree, (360/wedgeNum)+1, c, true);
				extraDegrees--;
				atDegree += (360/wedgeNum)+1;
				graphicsList.add(p);
			} else {
				Arc p = new Arc(0,0,400,300,atDegree,360/wedgeNum,c,true);
				atDegree += 360/wedgeNum;
				graphicsList.add(p);
			}
		}
		return graphicsList;
	}

	/**
	 * Create a pattern of stripes as shown in the homework instructions. Store
	 * the triangles in an ArrayList and return that ArrayList. Use two colors
	 * only to paint the triangles. The pattern should cover most of the window.
	 * The number of stripes (of one color) is given by the user (use a dialog
	 * box). If that number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_STRIPES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Triangle> createStripes() {
		ArrayList<Triangle> graphicsList = new ArrayList<Triangle>();
		// Total number of triangles: number of stripes squared, times two
		// Get number of stripes
		int stripesNum;
		Input input = new Input();
		do {
			stripesNum = input.readIntDialog("Number of stripes (between 1 and " + MAXIMUM_NUMBER_OF_STRIPES +")");
			if (stripesNum <= 0 || stripesNum > MAXIMUM_NUMBER_OF_STRIPES) {
				JOptionPane.showMessageDialog(null,"Invalid value","Error",JOptionPane.ERROR_MESSAGE);
			}
		} while (stripesNum <= 0 || stripesNum > MAXIMUM_NUMBER_OF_STRIPES);
		// The length of each square's side will be nice to have.
		int sideLen = 300 / stripesNum;
		for (int i = 0; i < stripesNum; i++) { // Build it square-by-square.
			for (int j = 0; j < stripesNum; j++) { // Move right then down.
				if(j % 2 == i % 2) { // I had to think about this one for a bit.
					Triangle bluTri = new Triangle(50+(j*sideLen),i*sideLen, 50+(j*sideLen),(i+1)*sideLen,50+((1+j)*sideLen),(i+1)*sideLen,Color.BLUE, true);
					Triangle yelTri = new Triangle(50+(j*sideLen),i*sideLen, 50+((j+1)*sideLen),i*sideLen,50+((1+j)*sideLen),(i+1)*sideLen,Color.YELLOW, true);
					graphicsList.add(bluTri);
					graphicsList.add(yelTri);
				} else {					
					Triangle bluTri = new Triangle(50+(j*sideLen),i*sideLen, 50+((j+1)*sideLen),i*sideLen,50+((1+j)*sideLen),(i+1)*sideLen,Color.BLUE, true);
					Triangle yelTri = new Triangle(50+(j*sideLen),i*sideLen, 50+(j*sideLen),(i+1)*sideLen,50+((1+j)*sideLen),(i+1)*sideLen,Color.YELLOW, true);
					graphicsList.add(bluTri);
					graphicsList.add(yelTri);					
				}
					
			}
		}
		return graphicsList;
	}

	/**
	 * Create a Koch snow flake. Use the class java.awt.Point. Store the Points
	 * in an ArrayList and return that ArrayList. Note that you can't set the
	 * color of a point. The initial color of the lines making up the snow flake
	 * is black. But, you can change the color in the method
	 * changeColorOfSnowFlake below. The snow flake should cover most of the
	 * window, and be always visible. The number of divisions of the initial
	 * triangle is given by the user (use a dialog box). If that number is less
	 * than 0 or greater than MAXIMUM_NUMBER_OF_DIVISIONS, display an error
	 * message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Point> createASnowFlake() {
		ArrayList<Point> graphicsList = new ArrayList<Point>();
		// First triangle.
		Point p1 = new Point(100,20);
		Point p2 = new Point(350,150);
		Point p3 = new Point(100,250);
		graphicsList.add(p1);
		graphicsList.add(p2);
		graphicsList.add(p3);
		
		// Get number of divisions
		int divisions;
		Input input = new Input();
		do {
			divisions = input.readIntDialog("Number of divisions (between 1 and " + MAXIMUM_NUMBER_OF_DIVISIONS +")");
			if (divisions <= 0 || divisions > MAXIMUM_NUMBER_OF_DIVISIONS) {
				JOptionPane.showMessageDialog(null,"Invalid value","Error",JOptionPane.ERROR_MESSAGE);
			}
		} while (divisions <= 0 || divisions > MAXIMUM_NUMBER_OF_DIVISIONS);
		
		// Do some divisions.
		for (int i = 0; i < divisions; i++) {
			// There are as many sides as there are in the list.
			int sides = graphicsList.size(); // Grab this before we add any points to the list.
			for (int j = 0; j < sides; j++) {
				Point p = graphicsList.get((j*4)); // This accounts for the points we add as we go along.
				Point q = null;
				if (j*4+1 >= graphicsList.size()){ // This is needed to split up the last side.
					q = graphicsList.get(0);
				} else {
					q = graphicsList.get((j*4)+1);
				}
				Point a = new Point((int) (p.x + (q.x - p.x) / 3.0),
						(int) (p.y + (q.y - p.y) / 3.0));
				Point c = new Point((int) (p.x + 2 * (q.x - p.x) / 3.0),
						(int) (p.y + 2 * (q.y - p.y) / 3.0));
				Point b = new Point();
				b.x = (int) (a.x + (c.x - a.x) * Math.cos(Math.PI / 3.0) + (c.y - a.y)
						* Math.sin(Math.PI / 3.0));
				b.y = (int) (a.y - (c.x - a.x) * Math.sin(Math.PI / 3.0) + (c.y - a.y)
						* Math.cos(Math.PI / 3.0));
				graphicsList.add((j*4)+1,c);
				graphicsList.add(j*4+1,b);
				graphicsList.add(j*4+1,a);
			}
		}
		
		return graphicsList;
	}

	/**
	 * Rotate the colors in the pie, in a clockwise direction. Precondition:
	 * graphicsList describes a pie Return the updated ArrayList
	 */
	public ArrayList<Arc> rotateColorsInPie(ArrayList<Arc> graphicsList) {
		// Save the first wedge's color for later.
		Color firstWedge = graphicsList.get(0).getColor();
		// Go through the wedges and set their colors.
		for (int i = 0; i < graphicsList.size()-1; i++) {
			graphicsList.get(i).setColor(graphicsList.get(i+1).getColor());
		}
		// Set the last wedge's color to what the first used to have.
		graphicsList.get(graphicsList.size()-1).setColor(firstWedge);
		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the pattern of stripes Precondition: graphicsList
	 * describes a pattern of stripes Return the updated ArrayList
	 */
	public ArrayList<Triangle> flipColorsInStripes(ArrayList<Triangle> graphicsList) {
		// Add your code here
		for (int i = 0; i < graphicsList.size(); i++) {
			if (graphicsList.get(i).getColor() == Color.YELLOW) { // It's yellow.
				graphicsList.get(i).setColor(Color.BLUE);
			} else {
				graphicsList.get(i).setColor(Color.YELLOW); // It's blue.
			}
		}
		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake (select a new random color) Use
	 * the Random class (in java.util) to select the new random color. The color
	 * that you create and return in this method will automatically be assigned
	 * to the snow flake.
	 */
	public Color changeColorOfSnowFlake() {
		Random rand = new Random();
		Color c = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
		return c;
	}
}
