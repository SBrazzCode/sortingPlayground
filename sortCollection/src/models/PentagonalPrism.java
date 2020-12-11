package models;

public class PentagonalPrism extends PrismShape{
	
	public PentagonalPrism(double height, double edgeLength) {
		super.height = height;
		super.edgeLength = edgeLength;
		
		calculateBaseArea();
		calculateVolume();
	}
	
	@Override
	void calculateBaseArea() {
		baseArea = 5 * Math.pow(edgeLength, 2) * Math.tan(Math.toRadians(54)) / 4;
	}

	@Override
	void calculateVolume() {
		volume = baseArea * height;		
	}

}
