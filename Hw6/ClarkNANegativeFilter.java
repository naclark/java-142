//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * ClarkNAFilter that returns the negative of the image.
 */
public class ClarkNANegativeFilter implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
        data[row][col].red = 255 - data[row][col].red;
        data[row][col].green = 255 - data[row][col].green;
        data[row][col].blue = 255 - data[row][col].blue;
      }
    }

    pi.setData(data);
  }
}