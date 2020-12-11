package models;

public class OctagonalPrism extends PrismShape{

	public OctagonalPrism(double height, double edgeLength) {
		super.height = height;
		super.edgeLength = edgeLength; 
		
		//If you changed these to something like calculatePrismBaseArea() you could rework this to be
		//only called from the PrismShape constructor.
		calculateBaseArea();
		calculateVolume();
	}
	
	@Override
	void calculateBaseArea() {
		baseArea = 2 * ( 1 + Math.sqrt(2)) * Math.pow(edgeLength, 2);
	}

	@Override
	void calculateVolume() {
		volume = baseArea * height;
	}

}
