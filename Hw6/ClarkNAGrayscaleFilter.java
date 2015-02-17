//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com


/**
 * For each pixel, sets all three colors to the same averaged value.
 */
public class ClarkNAGrayscaleFilter implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
          // Average out the three colors' values.
          int q = (data[row][col].red + data[row][col].green + data[row][col].blue)/3;
          data[row][col].red = q;
          data[row][col].green = q;
          data[row][col].blue = q;
      }
    }
    pi.setData(data);
  }
}
