package utilities;

import java.util.Comparator;

import models.Shape;

public class AreaComparator implements Comparator {

	@Override
	public int compare(Object shape1, Object shape2) {
		Shape s1 = (Shape) shape1;
		Shape s2 = (Shape) shape2;
		return (int) Math.ceil((s1.getBaseArea() - s2.getBaseArea()));
	}

}
