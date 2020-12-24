package utilities;

import java.util.Comparator;

public class MergeSortRunnable<E> implements Runnable {

	private E[] comparables;
	private Comparator<E> comp;
	private int left;
	private int right;
	private int numProcessorsAvailable;

	public MergeSortRunnable(E[] comparables, Comparator<E> comp, int left, int right, int numProcessorsAvailable) {
		this.comparables = comparables;
		this.comp = comp;
		this.left = left;
		this.right = right;
		this.numProcessorsAvailable = numProcessorsAvailable;
	}

	@Override
	public void run() {
		Sort.multiThreadMergeSort(comparables, comp, left, right, numProcessorsAvailable);
	}
}
