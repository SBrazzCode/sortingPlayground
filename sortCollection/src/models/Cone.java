package models;

public class Cone extends CircularShape{

	public Cone(double height, double radius) {
		this.height = height;
		this.radius = radius;
		
		calculateBaseArea();
		calculateVolume();
	}
	
	@Override
	protected void calculateVolume() {
		volume = baseArea * height / 3;
	}

}
