package models;

public class TriangularPrism extends PrismShape{

	public TriangularPrism(double height, double edgeLength) {
		super.height = height;
		super.edgeLength = edgeLength;
		
		calculateBaseArea();
		calculateVolume();
	}
	
	@Override
	void calculateBaseArea() {
		baseArea = Math.pow(edgeLength, 2) * Math.sqrt(3) / 4;
		
	}

	@Override
	void calculateVolume() {
		volume = baseArea * height;
	}

}
