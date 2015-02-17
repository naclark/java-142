//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * Class that applies a Gaussian blur.
 */
public class ClarkNAGaussianFilter implements ClarkNAFilter{
	public void filter(ClarkNAPixelImage pi) {
		int[][] weights = {{1,2,1},{2,4,2},{1,2,1}};
		pi.transformImage(weights, 16);
	}
}
