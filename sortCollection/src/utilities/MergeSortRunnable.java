package utilities;

import java.util.Comparator;

public class MergeSortRunnable implements Runnable {

	private Comparable[] comparables;
	private Comparator comp;
	private int left;
	private int right;
	private int numProcessorsAvailable;

	public MergeSortRunnable(Comparable[] comparables, Comparator comp, int left, int right, int numProcessorsAvailable) {
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
