package models;

public class SquarePrism extends PrismShape{
	
	public SquarePrism(double height, double edgeLength) {
		super.height = height;
		super.edgeLength = edgeLength;
		
		calculateBaseArea();
		calculateVolume();
	}

	@Override
	void calculateBaseArea() {
		baseArea = Math.pow(edgeLength, 2);	
	}

	@Override
	void calculateVolume() {
		volume = baseArea * height;
	}

}
