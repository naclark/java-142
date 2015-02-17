//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class ClarkNAPixelImage
{
  private BufferedImage myImage;
  private int width;
  private int height;

  /**
   * Map this ClarkNAPixelImage to a real image
   * @param bi The image
   */
  public ClarkNAPixelImage(BufferedImage bi)
  {
    // initialise instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  /**
   * Return the width of the image
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight()
  {
    return this.height;
  }

  /**
   * Return the BufferedImage of this ClarkNAPixelImage
   */
  public BufferedImage getImage()
  {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels.  The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * @return The array of pixels
   */
  public ClarkNAPixel[][] getData()
  {
    Raster r = this.myImage.getRaster();
    ClarkNAPixel[][] data = new ClarkNAPixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++)
    {
      for (int col = 0; col < r.getWidth(); col++)
      {
        samples = r.getPixel(col, row, samples);
        ClarkNAPixel newPixel = new ClarkNAPixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array.  This array matches
   * that returned by getData().  It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * @param data The array to pull from
   */
  public void setData(ClarkNAPixel[][] data)
  {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight())
    {
      throw new IllegalArgumentException("Array size does not match");
    }
    else if (data[0].length != wr.getWidth())
    {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }
  

  /**
   * Transforms an image based on a 2d array of weights. Divides each color's data by a scale, then limits it to 0-255.
   */
  public void transformImage(int[][] weights, int scale){
	  // Use a copy of the picture so we can grab color data from the original before changing it.
	  ClarkNAPixel[][] data = this.getData();
	  ClarkNAPixel[][] data2 = this.getData();
	  // These allow us to use any size of weights array.
	  int horizBuffer = weights[0].length/2;
	  int vertBuffer = weights.length/2;
	  for (int row = vertBuffer; row < this.getHeight() - vertBuffer; row++)
	    {
	      for (int col = horizBuffer; col < this.getWidth() - horizBuffer; col++)
	      {
	    	  // Loop to create new pixels, place in data2.
	    	  // Keep running totals of the products of weights array and pixels around the pixel in question.
	    	  int tempR = 0;
	    	  int tempG = 0;
	    	  int tempB = 0;
	    	  for (int p = 0; p < weights.length; p++) {
	    		  for (int q = 0; q < weights[0].length; q++) {
	    			  tempR += weights[p][q]*data[row+p-vertBuffer][col+q-horizBuffer].red;
	    			  tempG += weights[p][q]*data[row+p-vertBuffer][col+q-horizBuffer].green;
	    			  tempB += weights[p][q]*data[row+p-vertBuffer][col+q-horizBuffer].blue;
	    		  }
	    	  }
	    	  // Apply scale.
	    	  tempR /= scale;
	    	  tempG /= scale;
	    	  tempB /= scale;
	    	  // Are any of them less than 0 or greater than 255? e.g. Laplacian transformation
	    	  tempR = Math.max(0, Math.min(tempR, 255));
	    	  tempG = Math.max(0, Math.min(tempG, 255));
	    	  tempB = Math.max(0, Math.min(tempB, 255));
	    	  // Make the new pixel.
	    	  data2[row][col] = new ClarkNAPixel(tempR, tempG, tempB);
	      }
	    }
	  this.setData(data2);
  }
  
}
