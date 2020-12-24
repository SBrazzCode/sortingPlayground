package utilities;

import java.util.Comparator;

import models.Shape;

//Untyped comparators, for this program was made before we were taught generics.
public class AreaComparator<E extends Shape> implements Comparator<E> {

	@Override
	public int compare(E shape1, E shape2) {
		return (int) Math.ceil((shape1.getBaseArea() - shape2.getBaseArea()));
	}

}
