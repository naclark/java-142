//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * ClarkNAFilter that flips the image vertically.
 */
public class ClarkNAFlipVerticalFilter implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight() / 2; row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
        ClarkNAPixel temp = data[row][col];
        data[row][col] = data[pi.getHeight() - row - 1][col];
        data[pi.getHeight() - row - 1][col] = temp;
      }
    }

    pi.setData(data);
  }
}