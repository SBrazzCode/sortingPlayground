package utilities;

import java.util.Comparator;

import models.Shape;

public class VolumeComparator<E extends Shape> implements Comparator<E> {

	@Override
	public int compare(E shape1, E shape2) {
		return (int) Math.ceil(shape1.getVolume() - shape2.getVolume());
	}

}
