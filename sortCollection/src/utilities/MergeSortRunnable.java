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

//	private void mergeSortUnRunnable(Comparable[] comparables, Comparator comp, int left, int right) {
//
//		if (left < right) {
//			
//			//have rewrite code a bit to handle uneven numbers
//			int median = (right + left) / 2;
//			
//			
//			//Sort the left half
//			mergeSortUnRunnable(comparables, comp, 0, median);
//			
//			//Sort the right half
//			mergeSortUnRunnable(rightArray, comp, 0, median);
//
//			//Merge the two halves together
//			Comparable[] auxillary = merge(leftArray, rightArray, comp);
//			
//			arraycopy(auxillary, 0, comparables, left, right - left + 1);
////			arraycopy(leftArray, 0, comparables, 0, median);
////			arraycopy(rightArray, median, comparables, median + 1, right + 1);
//		}
//
//	}
//	
//	static void arraycopy(Comparable[] auxillary, int i, Comparable[] comparables, int left, int j) {
//		System.arraycopy(auxillary, 0, comparables, left, j);
//	}
//	
//	private Comparable[] merge(Comparable[] leftArray, Comparable[] rightArray, Comparator comp) {
//
//		//i.e. if we're merging left side 0 to 5 and right side 6 to 11, the size would need to be 11 + 1 (right2 + 1).
//		Comparable[] auxillary = new Comparable[leftArray.length * 2];
//		
//		int i = 0;
//		int j = 0;
//		int k = 0;
//		while (i <= leftArray.length && j <= rightArray.length) {
//			
//			//if the element at the left index is greater than the element in the right index, assign
//			//the element at the left index to auxillary, increment i and k. Else, the same but for the right element.
//			auxillary[k++] = comp.compare(leftArray[i], rightArray[j]) > 0 ? leftArray[i++] : rightArray[j++];
//		}
//
//		while (i <= leftArray.length) {
//			
//			//If we're here, only the left side has elements to be added to auxillary. "flush" the left elements into auxillary.
//			auxillary[k++] = leftArray[i++];
//		}
//		while (j < rightArray.length) {
//			
//			//If we're here, only the right side has elements to be added to auxillary. "flush" the right elements into auxillary.
//			auxillary[k++] = rightArray[j++];
//		}
//
//		return auxillary;
//	}

	/**
	 * sorts the comparables using merge sort.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left
	 * @param right
	 */
	public static void mergeSortUnRunnable(Comparable[] comparables, Comparator comp, int left, int right) {

		if (left < right) {
			int median = (right + left) / 2;
			
			//mergesort the left side
			mergeSortUnRunnable(comparables, comp, left, median);
			
			//mergesort the right side
			mergeSortUnRunnable(comparables, comp, median + 1, right);

			//merge the two halves together
			Comparable[] auxillary = merge(comparables, comp, left, median, median + 1, right);
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}

	}

	/**
	 * merges two arrays into an auxillary.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left1
	 * @param right1
	 * @param left2
	 * @param right2
	 * @return
	 */
	private static Comparable[] merge(Comparable[] comparables, Comparator comp, int left1, int right1, int left2,
			int right2) {

		Comparable[] auxillary = new Comparable[right2 + 1];
		int i = left1;
		int j = left2;
		int k = 0;
		
		//While both arrays still have elements to be merged, do the typical merge behaviour.
		while (i <= right1 && j <= right2) {

			//if the element at the left index is greater than the element in the right index, assign
			//the element at the left index to auxillary, increment i and k. Else, the same but for the right element.
			auxillary[k++] = comp.compare(comparables[i], comparables[j]) > 0 ? comparables[i++] : comparables[j++];
		}

		while (i <= right1) {
			// If we're here, only the left side has elements to be added to auxillary.
			// "flush" the left side elements into auxillary.
			auxillary[k++] = comparables[i++];
		}
		while (j <= right2) {

			// If we're here, only the right side has elements to be added to auxillary.
			// "flush" the right side elements into auxillary.
			auxillary[k++] = comparables[j++];
		}

		return auxillary;
	}
}
