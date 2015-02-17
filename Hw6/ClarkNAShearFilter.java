//                                                              
// On time 
// Name: CLARK NICHOLAS A   date: Monday, December 8, 2014 at 5:26:22 pm   Receipt: 6496
// Email: gomenze@hotmail.com

/**
 * Class that shears across an 8-pixel rectangle. Interestingly, the "center pixel" is at weights[1][2].
 * Because of this, the weights are automatically asymmetric, so applying this filter multiple times 
 * causes the image to move.
 */
public class ClarkNAShearFilter implements ClarkNAFilter{
	public void filter(ClarkNAPixelImage pi) {
		int[][] weights = {{10,5,2,1},{1,2,5,10}};
		pi.transformImage(weights, 36);
	}
}