package utilities;

import java.util.Comparator;

public class Sort {

	/**
	 * sorts the comparables using bubblesort.
	 * 
	 * @param comparables
	 * @param comparator
	 */
	public static void bubbleSort(Comparable[] comparables, Comparator comparator) {

		Comparable temp;
		Comparable left;
		Comparable right;
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
	public static void bubbleSort(Comparable[] comparables) {

		Comparable temp;
		Comparable left;
		Comparable right;
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
	public static void selectionSort(Comparable[] comparables, Comparator comp) {

		int maximumIndex = 0;
		Comparable maximum = comparables[0];
		int right = comparables.length;
		Comparable temp;

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
	public static void selectionSort(Comparable[] comparables) {

		int maximumIndex = 0;
		Comparable maximum = comparables[0];
		int right = comparables.length;
		Comparable temp;

		for (int left = 0; left < comparables.length; left++) {
			for (int j = left + 1; j < right; j++) {
				if (maximum.compareTo(comparables[j]) < 0) {
					maximumIndex = j;
					maximum = comparables[j];
				}
			}

			if (left == 20235) {
				@SuppressWarnings("unused")
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
	public static void insertionSort(Comparable[] comparables, Comparator comp) {

		Comparable current;
		Comparable temp;
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

	public static void insertionSort(Comparable[] comparables) {

		Comparable current;
		Comparable temp;
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

	public static void mergeSort(Comparable[] comparables, Comparator comp, int left, int right) {

		int median;
		int rightSize;

		if (left < right) {

			// The median will be used non-inclusively (so '+ 1' is needed for an even
			// comparables).
			median = (right - left + 1) / 2;

			// if comparables is even, normal case. Else increase size by 1.
			rightSize = (right - left + 1) % 2 == 0 ? median : median + 1;

			Comparable[] leftArray = new Comparable[median];
			Comparable[] rightArray = new Comparable[rightSize];

			System.arraycopy(comparables, left, leftArray, 0, leftArray.length);
			System.arraycopy(comparables, median, rightArray, 0, rightArray.length);

			// Thread for the left side.
			MergeSortRunnable ms1 = new MergeSortRunnable(leftArray, comp, 0, leftArray.length - 1);

			// Thread for the right side.
			MergeSortRunnable ms2 = new MergeSortRunnable(rightArray, comp, 0, rightArray.length - 1);

			Thread thread1 = new Thread(ms1);
			Thread thread2 = new Thread(ms2);

			thread1.start();
			thread2.start();

			try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Copy left back into comparables
			System.arraycopy(leftArray, 0, comparables, 0, leftArray.length);

			// Copy right back into comparables
			System.arraycopy(rightArray, 0, comparables, median, rightArray.length);

			// One last merge for the two halves.
			Comparable[] auxillary = merge(comparables, comp, left, median - 1, median, right);

			// Copy the auxillary back into the original array.
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}
	}


	public static Comparable[] merge(Comparable[] comparables, Comparator comp, int left1, int right1, int left2,
			int right2) {

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

	/**
	 * sorts the comparables using mergesort.
	 * 
	 * @param comparables
	 * @param left
	 * @param right
	 */
	public static void mergeSort(Comparable[] comparables, int left, int right) {

		if (left < right) {
			int median = (right + left) / 2;
			mergeSort(comparables, left, median);
			mergeSort(comparables, median + 1, right);

			Comparable[] auxillary = merge(comparables, left, median, median + 1, right);
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
	private static Comparable[] merge(Comparable[] comparables, int left1, int right1, int left2, int right2) {

		Comparable[] auxillary = new Comparable[right2 + 1];
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

	/**
	 * Sorts the comparables using quicksort.
	 * 
	 * @param comparables
	 * @param comp
	 * @param left
	 * @param right
	 */
	public static void quickSort(Comparable[] comparables, Comparator comp, int left, int right) {

		if (left < right) {

			Comparable pivot = comparables[0];

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
	private static int partition(Comparable[] comparables, Comparator comp, int left, int right) {
		Comparable pivot = comparables[left];
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
	public static void quickSort(Comparable[] comparables, int left, int right) {

		if (left < right) {

			Comparable pivot = comparables[0];

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
	private static int partition(Comparable[] comparables, int left, int right) {
		Comparable pivot = comparables[left];
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
	public static void heapSort(Comparable[] comparables, Comparator comp) {

		int n = comparables.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(comparables, comp, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			Comparable temp = comparables[0];
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
	private static void heapify(Comparable[] comparables, Comparator comp, int n, int i) {
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
			Comparable swap = comparables[i];
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
	public static void heapSort(Comparable[] comparables) {

		int n = comparables.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(comparables, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			Comparable temp = comparables[0];
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
	private static void heapify(Comparable[] comparables, int n, int i) {
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
			Comparable swap = comparables[i];
			comparables[i] = comparables[largest];
			comparables[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(comparables, n, largest);
		}

	}

}
