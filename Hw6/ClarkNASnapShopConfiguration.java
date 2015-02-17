//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/* Nick Clark, CSC 142 Homework #6
 * 1. Additional filters implemented
 * Besides the required transformations, I also created a "tricolor filter" (which locks every pixel into just red,
 * green or blue, based on which of the three is most prevalent in the original color), a 5x5 pixel blur, a 2x4 "shear"
 * filter, a grayscale transformation, and a transformation to add contrast.
 * 2. What works? What doesn't work?
 * Essentially, any time a matrix-type filter is used, it should be symmetric and have a clear center point. My ClarkNAShearFilter,
 * being a 2x4 rectangle, doesn't have this. As a result, while it runs just fine, it also moves the image down and to the right.
 * My ClarkNAContrast filter seems to work okay, though it is rather slow. I originally multiplied the color values by 1.1 and 0.9
 * respectively, then cast the result back to an integer. I thought the recasting might have slowed it down. After rearranging it 
 * to do integer multiplication and division (that is, multiply by 11 or 9 before dividing by 10), though, performance stayed
 * about the same. Lastly, I tried substituting ternary operations for the Math.min and Math.max I have there now; this seemed
 * to slow it down even more.
 * Other than these two issues, most things worked out just fine.
 * 3. Surprises and problems
 * The first thing I was curious about was how the "scale" was determined for the matrix-type transformations. By looking at the
 * Gaussian, unsharp masking and edgy transformations, I assumed that the sum of all numbers in the matrix was the scaling 
 * factor...but this is not the case for the Laplacian, where this would result in dividing by 0. So, since I couldn't 
 * calculate it simply from the weights[][] array, I decided to have it be defined in each filter and passed into transformImage.
 * Another was adjusting for the sizes of matrices that were not 3x3. In doing so, I had to think in terms of how much of a
 * "buffer" was needed above and below each transformed pixel. For a 3x3 array, it was 1 pixel; for 5x5, I could see it was 2.
 * So, it made sense that it was the same as integer-dividing the height or width by 2. The way I implemented it, the array still
 * needs to at least be rectangular, since it uses weights[0].length for the width.
 */

/**
 * A class to configure the ClarkNASnapShop application
 *
 * @author Nick Clark
 * @version 12/08/14
 */
public class ClarkNASnapShopConfiguration
{
  /**
   * Method to configure the ClarkNASnapShop.  Call methods like addFilter
   * and setDefaultFilename here.
   * @param theShop A pointer to the application
   */
  public static void configure(ClarkNASnapShop theShop)
  {

    theShop.setDefaultFilename("billg.jpg");
    theShop.addFilter(new ClarkNAFlipHorizontalFilter(), "Flip Horizontal");
    theShop.addFilter(new ClarkNAFlipVerticalFilter(), "Flip Vertical");
    theShop.addFilter(new ClarkNANegativeFilter(), "Negative");
    theShop.addFilter(new ClarkNAGaussianFilter(), "Gaussian ClarkNAFilter");
    theShop.addFilter(new ClarkNALaplacianFilter(), "Laplacian ClarkNAFilter");
    theShop.addFilter(new ClarkNAUnsharpFilter(), "Unsharp Masking");
    theShop.addFilter(new ClarkNAEdgyFilter(), "Edgy");
    theShop.addFilter(new ClarkNATricolorFilter(), "Tricolor ClarkNAFilter");
    theShop.addFilter(new ClarkNABigFilter(), "Blur (5 pixels)");
    theShop.addFilter(new ClarkNAShearFilter(), "Rectangle Shear");
    theShop.addFilter(new ClarkNAGrayscaleFilter(), "Grayscale");
    theShop.addFilter(new ClarkNAContrast(), "Add ClarkNAContrast");
  }
}
