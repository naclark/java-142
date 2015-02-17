//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * Class that applies a blur across a 25-pixel square.
 */
public class ClarkNABigFilter implements ClarkNAFilter{
	public void filter(ClarkNAPixelImage pi) {
		int[][] weights = {{1,1,2,1,1},{1,2,3,2,1},{2,3,4,3,2},{1,2,3,2,1},{1,1,2,1,1}};
		pi.transformImage(weights, 38);
	}
}
