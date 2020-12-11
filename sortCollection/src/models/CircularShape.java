package models;

public abstract class CircularShape extends Shape {
	protected double radius;
	
	/**
	 * calculate the baseArea and set the super variable.
	 * @param radius
	 */
	protected void calculateBaseArea() {
		super.baseArea = Math.PI * radius * radius;
	}
	
	protected abstract void calculateVolume();
}
