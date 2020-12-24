package utilities;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Sort {

	/**
	 * sorts the comparables using bubblesort.
	 * @param <E>
	 * 
	 * @param comparables
	 * @param comparator
	 */
	public static <E> void bubbleSort(E[] comparables, Comparator<E> comparator) {

		E temp;
		E left;
		E right;
		for (int i = 1; i < comparables.length; i++) {
			for (int j = 0; j < comparables.length - i; j++) {
				left = comparables[j];
				right = comparables[j + 1];
				if (comparator.compare(left, right) < 0) {
					temp = left;
					comparables[j] = right;
					comparables[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * sorts the comparables using bubblesort.
	 * 
	 * @param comparables
	 */
	public static <E extends Comparable<E>> void bubbleSort(E[] comparables) {

		E temp;
		E left;
		E right;
		for (int i = 1; i < comparables.length; i++) {
			for (int j = 0; j < comparables.length - i; j++) {
				left = comparables[j];
				right = comparables[j + 1];
				if (left.compareTo(right) < 0) {
					temp = left;
					comparables[j] = right;
					comparables[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * sorts the comparables using selection sort.
	 * 
	 * @param comparables
	 * @param comp
	 */
	public static <E> void selectionSort(E[] comparables, Comparator<E> comp) {

		int maximumIndex = 0;
		E maximum = comparables[0];
		int right = comparables.length;
		E temp;

		for (int left = 0; left < comparables.length; left++) {
			for (int j = left + 1; j < right; j++) {
				if (comp.compare(maximum, comparables[j]) < 0) {
					maximumIndex = j;
					maximum = comparables[j];
				}
			}

			if (left + 1 < right) {
				temp = comparables[left];
				comparables[left] = maximum;
				comparables[maximumIndex] = temp;
				maximum = comparables[left + 1];
			}
		}
	}

	/**
	 * sorts the comparables using selection sort.
	 * 
	 * @param comparables
	 */
	public static <E extends Comparable<E>> void selectionSort(E[] comparables) {

		int maximumIndex = 0;
		E maximum = comparables[0];
		int right = comparables.length;
		E temp;

		for (int left = 0; left < comparables.length; left++) {
			for (int j = left + 1; j < right; j++) {
				if (maximum.compareTo(comparables[j]) < 0) {
					maximumIndex = j;
					maximum = comparables[j];
				}
			}

			//?
			if (left == 20235) {
				int f = 1;
				int s = f + 2;
			}

			if (left + 1 < right) {
				temp = comparables[left];
				comparables[left] = maximum;
				comparables[maximumIndex] = temp;
				maximum = comparables[left + 1];
			}
		}
	}

	/**
	 * sorts the comparables usign insertion sort.
	 * 
	 * @param comparables
	 * @param comp
	 */
	public static <E> void insertionSort(E[] comparables, Comparator<E> comp) {

		E current;
		E temp;
		int j;
		for (int i = 1; i < comparables.length; i++) {
			current = comparables[i];
			j = i - 1;
			while (j >= 0 && comp.compare(current, comparables[j]) > 0) {

				temp = comparables[j];
				comparables[j] = current;
				comparables[j + 1] = temp;

				j--;
			}
		}

	}

	public static <E extends Comparable<E>> void insertionSort(E[] comparables) {

		E current;
		E temp;
		int j;
		for (int i = 1; i < comparables.length; i++) {
			current = comparables[i];
			j = i - 1;
			while (j >= 0 && current.compareTo(comparables[j]) > 0) {

				temp = comparables[j];
				comparables[j] = current;
				comparables[j + 1] = temp;

				j--;
			}
		}

	}

	public static <E> void mergeSort(E[] comparables, Comparator<E> comp, int left, int right) {

		
		int numProcessors = Runtime.getRuntime().availableProcessors();
		multiThreadMergeSort(comparables, comp, left, right, numProcessors);

	}

	/**
	 * sorts the comparables using merge sort recursively.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left
	 * @param right
	 */
	public static <E> void mergeSortUnRunnable(E[] comparables, Comparator<E> comp, int left, int right) {

		
		if (left < right) {
			int median = (right + left) / 2;

			// mergesort the left side
			mergeSortUnRunnable(comparables, comp, left, median);

			// mergesort the right side
			mergeSortUnRunnable(comparables, comp, median + 1, right);

			// merge the two halves together
			E[] auxillary = merge(comparables, comp, left, median, median + 1, right);
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}

	}
	
	static <E> void multiThreadMergeSort(E[] comparables, Comparator<E> comp, int left, int right,
			int numProcessors) {
		
		
		int median;
		int rightSize;

		if (left < right) {

			// The median will be used non-inclusively (so '+ 1' is needed for an even
			// comparables).
			median = (right - left + 1) / 2;

			// right - median or might just work.
			// if comparables is even, normal case. Else increase size by 1. (this just handles the extra unit for an odd number size)
			rightSize = (right - left + 1) % 2 == 0 ? median : median + 1;

			if (numProcessors >= 2) {
				
				E[] leftArray = createGenericArray(comparables, median);
				E[] rightArray = createGenericArray(comparables, rightSize);

				System.arraycopy(comparables, left, leftArray, 0, leftArray.length);
				System.arraycopy(comparables, median, rightArray, 0, rightArray.length);

				// Thread for the left side.
				MergeSortRunnable<E> leftRunnable = new MergeSortRunnable<>(leftArray, comp, 0, leftArray.length - 1,
						numProcessors / 2);

				// Thread for the right side.
				MergeSortRunnable<E> rightRunnable = new MergeSortRunnable<>(rightArray, comp, 0, rightArray.length - 1,
						numProcessors / 2);

				Thread leftThread = new Thread(leftRunnable);
				Thread rightThread = new Thread(rightRunnable);

				leftThread.start();
				rightThread.start();

				try {
					leftThread.join();
					rightThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Copy left back into comparables
				System.arraycopy(leftArray, 0, comparables, 0, leftArray.length);

				// Copy right back into comparables
				System.arraycopy(rightArray, 0, comparables, median, rightArray.length);

			} else {
				
				//Merge sort the rest on this thread (call the non-multithreaded merge sort).
				mergeSortUnRunnable(comparables, comp, left, right);
			}

			// One last merge for the two halves.
			E[] auxillary = merge(comparables, comp, left, median - 1, median, right);

			// Copy the auxillary back into the original array.
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);

		}
	}

	public static <E>  E[] merge(E[] comparables, Comparator<E> comp, int left1, int right1, int left2,
			int right2) {

		//This is an inefficient use of space, fix this after you get the sort working (might just be right2 - left1)
		E[] auxillary = createGenericArray(comparables, right2 + 1);
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
	

	/**
	 * sorts the comparables using mergesort.
	 * 
	 * @param comparables
	 * @param left
	 * @param right
	 */
	public static <E extends Comparable<E>> void mergeSort(E[] comparables, int left, int right) {

		if (left < right) {
			int median = (right + left) / 2;
			mergeSort(comparables, left, median);
			mergeSort(comparables, median + 1, right);

			E[] auxillary = merge(comparables, left, median, median + 1, right);
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}

	}

	/**
	 * merges two arrays into an auxillary.
	 * 
	 * @param comparables
	 * @param left1
	 * @param right1
	 * @param left2
	 * @param right2
	 * @return
	 */
	private static <E extends Comparable<E>> E[] merge(E[] comparables, int left1, int right1, int left2, int right2) {

		E[] auxillary = createGenericArray(comparables, right2 + 1);
		int i = left1;
		int j = left2;
		int k = 0;
		while (i <= right1 && j <= right2) {
			auxillary[k++] = comparables[i].compareTo(comparables[j]) > 0 ? comparables[i++] : comparables[j++];
		}

		while (i <= right1) {
			auxillary[k++] = comparables[i++];
		}
		while (j <= right2) {
			auxillary[k++] = comparables[j++];
		}

		return auxillary;
	}
	
	@SuppressWarnings("unchecked")
	private static <E> E[] createGenericArray(E[] arrayWithType, int size) {
		return (E[]) Array.newInstance(arrayWithType.getClass().getComponentType(), size);
	}

	/**
	 * Sorts the comparables using quicksort.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left
	 * @param right
	 */
	public static <E> void quickSort(E[] comparables, Comparator<E> comp, int left, int right) {

		if (left < right) {

			E pivot = comparables[0];

			int p = partition(comparables, comp, left, right);
			// parition

			quickSort(comparables, comp, left, p - 1);
			quickSort(comparables, comp, p + 1, right);
		}

	}

	/**
	 * divides the array into three sections based on the pivot.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left
	 * @param right
	 * @return
	 */
	private static <E> int partition(E[] comparables, Comparator<E> comp, int left, int right) {
		E pivot = comparables[left];
		int p = left;

		for (int r = left + 1; r <= right; r++) {
			int comparison = comp.compare(comparables[r], pivot);

			if (comparison > 0) {
				comparables[p] = comparables[r];
				comparables[r] = comparables[p + 1];
				comparables[p + 1] = pivot;
				p++;

			}
		}

		return p;
	}

	/**
	 * sorts the comparables using quick sort.
	 * 
	 * @param comparables
	 * @param left
	 * @param right
	 */
	public static <E extends Comparable<E>> void quickSort(E[] comparables, int left, int right) {

		if (left < right) {

			E pivot = comparables[0];

			int p = partition(comparables, left, right);
			// parition

			quickSort(comparables, left, p - 1);
			quickSort(comparables, p + 1, right);
		}

	}

	/**
	 * Divides the arrray into 3 sections based on the pivot.
	 * 
	 * @param comparables
	 * @param left
	 * @param right
	 * @return
	 */
	private static <E extends Comparable<E>> int partition(E[] comparables, int left, int right) {
		E pivot = comparables[left];
		int p = left;

		for (int r = left + 1; r <= right; r++) {
			int comparison = comparables[r].compareTo(pivot);

			if (comparison > 0) {
				comparables[p] = comparables[r];
				comparables[r] = comparables[p + 1];
				comparables[p + 1] = pivot;
				p++;

			}
		}

		return p;
	}

	/**
	 * A modified implementation of heap sort.
	 * https://www.geeksforgeeks.org/heap-sort/
	 * 
	 * @param comparables
	 * @param comp
	 */
	public static <E> void heapSort(E[] comparables, Comparator<E> comp) {

		int n = comparables.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(comparables, comp, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			E temp = comparables[0];
			comparables[0] = comparables[i];
			comparables[i] = temp;

			// call max heapify on the reduced heap
			heapify(comparables, comp, i, 0);
		}
	}

	/**
	 * Creates the heap. Recursively create a heap with the largest value on the
	 * top.
	 * 
	 * @param comparables
	 * @param comp
	 * @param n
	 * @param i
	 */
	private static <E> void heapify(E[] comparables, Comparator<E> comp, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && comp.compare(comparables[l], comparables[largest]) < 0)
			largest = l;

		// If right child is larger than largest so far
		if (r < n && comp.compare(comparables[r], comparables[largest]) < 0)
			largest = r;

		// If largest is not root
		if (largest != i) {
			E swap = comparables[i];
			comparables[i] = comparables[largest];
			comparables[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(comparables, comp, n, largest);
		}

	}

	/**
	 * A modified implementation of heap sort.
	 * https://www.geeksforgeeks.org/heap-sort/
	 * 
	 * @param comparables
	 */
	public static <E extends Comparable<E>> void heapSort(E[] comparables) {

		int n = comparables.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(comparables, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			E temp = comparables[0];
			comparables[0] = comparables[i];
			comparables[i] = temp;

			// call max heapify on the reduced heap
			heapify(comparables, i, 0);
		}
	}

	/**
	 * Creates the heap. Recursively create the heap with the largest value on top.
	 * 
	 * @param comparables
	 * @param n
	 * @param i
	 */
	private static <E extends Comparable<E>> void heapify(E[] comparables, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && comparables[l].compareTo(comparables[largest]) < 0)
			largest = l;

		// If right child is larger than largest so far
		if (r < n && comparables[r].compareTo(comparables[largest]) < 0)
			largest = r;

		// If largest is not root
		if (largest != i) {
			E swap = comparables[i];
			comparables[i] = comparables[largest];
			comparables[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(comparables, n, largest);
		}

	}

}
