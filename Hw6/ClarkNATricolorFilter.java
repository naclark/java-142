//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com


/**
 * For each pixel, sets the two color values that are not the highest to 0. In the case
 * of a tie, follows the hierarchy Red->Green->Blue.
 */
public class ClarkNATricolorFilter implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
          // Check which one is the highest of the three values.
          int q = Math.max(data[row][col].red,Math.max(data[row][col].green,data[row][col].blue));
          if (data[row][col].red == q) {
              data[row][col].green = 0;
              data[row][col].blue = 0;
          } else if (data[row][col].green == q) {
              data[row][col].red = 0;
              data[row][col].blue = 0;
          } else {
              data[row][col].red = 0;
              data[row][col].green = 0;
          }
      }
    }
    pi.setData(data);
  }
}
