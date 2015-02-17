//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * Applies an edgy filter to the image.
 */
public class ClarkNAEdgyFilter implements ClarkNAFilter{
	public void filter(ClarkNAPixelImage pi) {
		int[][] weights = {{-1,-1,-1},{-1,9,-1},{-1,-1,-1}};
		pi.transformImage(weights, 1);
	}
}
