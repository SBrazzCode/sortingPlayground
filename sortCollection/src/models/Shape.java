package models;

public abstract class Shape implements Comparable<Shape> {
	protected double height;
	protected double baseArea;
	protected double volume;

	abstract void calculateBaseArea();

	abstract void calculateVolume();

	/**
	 * Compares shapes based on their height. Returns the difference between their
	 * heights. this - comparedTo shape.
	 * 
	 * @param shape
	 * @return the difference between the two shapes heights.
	 */
	@Override
	public int compareTo(Shape shape) {
		return (int) Math.ceil(height - shape.getHeight());
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the baseArea
	 */
	public double getBaseArea() {
		return baseArea;
	}

	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}

	public String toString() {
		return String.format("Height: %-16.3fbaseArea: %-16.3fVolume: %-16.3f", height, baseArea, volume);
	}
}
