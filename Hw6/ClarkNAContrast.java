//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * Increases contrast.
 */
public class ClarkNAContrast implements ClarkNAFilter
{
  public void filter(ClarkNAPixelImage pi)
  {
    ClarkNAPixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
          // Is the average color value above 127? Then multiply by 1.1. If not, multiply by 0.9. Watch out for 
          // values above 255 and less than 0.
          if ((data[row][col].red + data[row][col].green + data[row][col].blue)/3 > 127) { 
              data[row][col].red = Math.min(255, (11 * data[row][col].red)/10);
              data[row][col].green = Math.min(255, (11 * data[row][col].green)/10);
              data[row][col].blue = Math.min(255, (11 * data[row][col].blue)/10);
          } else {
              data[row][col].red = Math.max(0, (9 * data[row][col].red)/10);
              data[row][col].green = Math.max(0, (9 * data[row][col].green)/10);
              data[row][col].blue = Math.max(0, (9 * data[row][col].blue)/10);
          }
      }
    pi.setData(data);
    }
  }
}