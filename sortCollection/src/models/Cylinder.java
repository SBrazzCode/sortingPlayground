package models;

public class Cylinder extends CircularShape{

	public Cylinder(double height, double radius) {
		super.height = height;
		this.radius = radius;
		
		//Calculate once so that they are not calculated every comparison.
		calculateBaseArea();
		calculateVolume();
		
	}
		
	@Override
	protected void calculateVolume() {
		volume = baseArea * height;
	}
	
}
