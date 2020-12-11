//package utilities;
//
//import java.util.Comparator;
//
//public class MergeSortRunnable implements Runnable {
//
//	private Comparable[] comparables;
//	private Comparator comp;
//	private int left;
//	private int right;
//
//	public MergeSortRunnable(Comparable[] comparables, Comparator comp, int left, int right) {
//		this.comparables = comparables;
//		this.comp = comp;
//		this.left = left;
//		this.right = right;
//	}
//
//	@Override
//	public void run() {
//		if (left < right) {
//			int median = (right + left) / 2;
//			MergeSortRunnable ms1 = new MergeSortRunnable(comparables, comp, left, median);
//			MergeSortRunnable ms2 = new MergeSortRunnable(comparables, comp, median + 1, right);
//
//			Thread thread1 = new Thread(ms1);
//			Thread thread2 = new Thread(ms2);
//			thread1.start();
//			thread2.start();
//
//			try {
//
//				thread1.join();
//				thread2.join();
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			Comparable[] auxillary = Sort.merge(comparables, comp, left, median, median + 1, right);
//
//			// Might have to synchronize to work with threads (just wrapper method).
//			Sort.arraycopy(auxillary, 0, comparables, left, right - left + 1);
//		}
//
//		// merge auxillary back into comparables.
//
//	}
//
//}

package utilities;

import java.util.Comparator;

public class MergeSortRunnable implements Runnable {

	private Comparable[] comparables;
	private Comparator comp;
	private int left;
	private int right;

	public MergeSortRunnable(Comparable[] comparables, Comparator comp, int left, int right) {
		this.comparables = comparables;
		this.comp = comp;
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		mergeSortUnRunnable(comparables, comp, left, right);
	}
	
	private void mergeSortUnRunnable(Comparable[] comparables, Comparator comp, int left, int right) {

		if (left < right) {
			int median = (right + left) / 2;
			mergeSortUnRunnable(comparables, comp, left, median);
			mergeSortUnRunnable(comparables, comp, median + 1, right);

			Comparable[] auxillary = merge(comparables, comp, left, median, median + 1, right);
			arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}

	}
	
	static void arraycopy(Comparable[] auxillary, int i, Comparable[] comparables, int left, int j) {
		System.arraycopy(auxillary, 0, comparables, left, j);
	}
	
	private Comparable[] merge(Comparable[] comparables, Comparator comp, int left1, int right1, int left2, int right2) {

		Comparable[] auxillary = new Comparable[right2 + 1];
		int i = left1;
		int j = left2;
		int k = 0;
		while (i <= right1 && j <= right2) {
			auxillary[k++] = comp.compare(comparables[i], comparables[j]) > 0 ? comparables[i++] : comparables[j++];
		}

		while (i <= right1) {
			auxillary[k++] = comparables[i++];
		}
		while (j <= right2) {
			auxillary[k++] = comparables[j++];
		}

		return auxillary;
	}
}
