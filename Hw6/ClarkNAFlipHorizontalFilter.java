//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * ClarkNAFilter that flips the image horizontally.
 * This class is COMPLETE. Don't change it. But model your other classes (such
 * as ClarkNAFlipVerticalFilter) after it.
 */
public class ClarkNAFlipHorizontalFilter implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth() / 2; col++)
      {
        ClarkNAPixel temp = data[row][col];
        data[row][col] = data[row][pi.getWidth() - col - 1];
        data[row][pi.getWidth() - col - 1] = temp;
      }
    }

    pi.setData(data);
  }
}
