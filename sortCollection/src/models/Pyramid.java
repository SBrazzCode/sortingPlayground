package models;

public class Pyramid extends Shape {
	private double edgeLength;

	public Pyramid(double height, double edgeLength) {
		super.height = height;
		this.edgeLength = edgeLength;
		
		//Calculate once so that they are not calculated every comparison.
		calculateBaseArea();
		calculateVolume();
	}

	@Override
	void calculateBaseArea() {
		baseArea = edgeLength * edgeLength;
		
	}

	@Override
	void calculateVolume() {
		super.volume = baseArea * height / 3;
	}

}
