import java.text.DecimalFormat; // to format numbers (check the documentation)

/**
 * Compute the amount of paint needed to paint a room
 */

public class PaintShopCalculator {

	// Constants
	// Prices of the paint containers in dollars
	public final double FIVEGALLONS = 116.00;

	public final double ONEGALLON = 23.20;

	public final double HALFGALLON = 11.60;

	public final double QUART = 5.80;

	public final double PINT = 2.90;

	public final double HALFPINT = 1.45;

	// Area that can be painted with one gallon of paint (in square inches)
	public final double AREA_PER_GALLON = 25000.0;
	
	// Instance variables for the measurements.
	private int heightFeet,heightInches,widthFeet,widthInches,lengthFeet,lengthInches;

	/**
	 * Initialize this PaintShopCalculator with the room measurements. For
	 * example, if the height is 10'2'', heightFeet is 10 and heightInches is 2.
	 * 
	 * @param heightFeet
	 *            the number of feet of the height measurement
	 * @param heightInches
	 *            the number of inches of the height measurement
	 * @param widthFeet
	 *            the number of feet of the width measurement
	 * @param widthInches
	 *            the number of inches of the width measurement
	 * @param lengthFeet
	 *            the number of feet of the length measurement
	 * @param lengthInches
	 *            the number of inches of the length measurement
	 */
	public PaintShopCalculator(int heightFeet, int heightInches,
			int lengthFeet, int lengthInches, int widthFeet, int widthInches) {
		this.heightFeet = heightFeet;
		this.lengthFeet = lengthFeet;
		this.widthFeet = widthFeet;
		this.heightInches = heightInches;
		this.lengthInches = lengthInches;
		this.widthInches = widthInches;
	}

	/**
	 * Return as a string the result of the computation. The string should list
	 * the exact amount of paint needed (with 3 digits after the decimal point),
	 * the number and type of paint containers needed, and the price (with 2
	 * digits after the decimal point). Pay attention to the spelling (container
	 * versus containers) and the quality of the output (no 0 one gallon
	 * container).
	 * 
	 * Here is an example with height=4'3'', length=5'4'' and width=6'5'':
	 * 
	 * For this job, you need 0.772 gallons of paint. You will need to purchase
	 * 1 one half gallon container 1 one quart container 1 one half pint
	 * container
	 * 
	 * The total price is $18.85
	 * 
	 * Thank you for your business!
	 * 
	 * Another test case is height=5'8", length=5'0", width=7'0". It requires
	 * 0.985 gallons, and should only need a single gallon container.
	 * 
	 */
	public String toString() {
		// Declare ints that we'll need.
		int sqinches, fiveGal, oneGal;
		// Define DecimalFormats we need.
		DecimalFormat gals = new DecimalFormat("0.000");
		DecimalFormat price = new DecimalFormat("0.00");
		// Get the number of inches in each measurement.
		int totalHeight = 12*heightFeet+heightInches;
		int totalLength = 12*lengthFeet+lengthInches;
		int totalWidth = 12*widthFeet+widthInches;
		// Total square inches is (2*height*length)+(2*height*width)+(length*width).
		// Two walls, 2*height*length:
		sqinches = (2*totalHeight*totalLength);
		// The other two walls, 2*height*width:
		sqinches += (2*totalHeight*totalWidth);
		// And the ceiling, length*width:
		sqinches += totalLength*totalWidth;
		// How many gallons of paint do we need for all these square inches?
		double needGals = sqinches / AREA_PER_GALLON;
		
		// Total price:
		double totalPrice = 0;
		
		// Add this to the string.
		String s = "For this job, you need " + gals.format(needGals) + " gallons of paint.\n";
		s += "You will need to purchase:\n";
		// Number of five-gallon buckets:
		fiveGal = (int)(needGals / 5);
		// How many gallons do we need now? And so forth for other types of buckets.
		needGals %= 5;
		// If a 5-gallon container would get you just as close or closer as a series of
		// smaller containers, then add one more.
		if (needGals > 5-0.0625) {
			fiveGal++;
			needGals = 0;
		}
		if (fiveGal > 0) {
			totalPrice += fiveGal * FIVEGALLONS;
			s += "\t" + fiveGal + " five-gallon container" + ((fiveGal > 1) ? "s\n" : "\n");
			// We don't want to put "1 five-gallon containers".
		}
		oneGal = (int)(needGals / 1);
		needGals %= 1;
		if (needGals > 1-0.0625) {
			oneGal++;
			needGals = 0;
		}

		if (oneGal > 0) {
			s += "\t"+ oneGal + " gallon container"+((oneGal > 1) ? "s\n" : "\n");
			totalPrice += oneGal * ONEGALLON;
		}
		if (needGals > 0.5-0.0625) {
			// You can only ever have one or zero of every other kind of container.
			s += "\t1 half gallon container\n";
			needGals -= 0.5;
			totalPrice += HALFGALLON;
		}
		if (needGals > 0.25-0.0625) {
			s += "\t1 quart container\n";
			needGals -= 0.25;
			totalPrice += QUART;
		}
		if (needGals > 0.0625) {
			s += "\t1 pint container\n";
			needGals -= 0.125;
			totalPrice += PINT;
		}
		if (needGals > 0) {
			s += "\t1 half pint container\n";
			totalPrice += HALFPINT;
		}
		
		s += "\n\tThe total price is $"+price.format(totalPrice)+"\n\n\tThank you for your business!";
		
		return s;
	}
}
