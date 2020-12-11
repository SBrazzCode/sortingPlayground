package utilities;

import java.util.Comparator;

public class Sort {

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

//	public static void mergeSort(Comparable[] comparables, Comparator comp, int left, int right) {
//		// Split array in half
//		// Thread each splitting (maybe only start new threads if the splitting isn't
////too deep
//		if (left < right) {
//			int median = (right + left) / 2;
//			mergeSort(comparables, comp, left, median);
//			mergeSort(comparables, comp, median + 1, right);
//
//			Comparable[] auxillary = merge(comparables, comp, left, median, median + 1, right);
//			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
//		}
//
//	}

//	private static Comparable[] merge(Comparable[] comparables, Comparator comp, int left1, int right1, int left2,
//			int right2) {
//
//		Comparable[] auxillary = new Comparable[right2 + 1];
//		int i = left1;
//		int j = left2;
//		int k = 0;
//		while (i <= right1 && j <= right2) {
//			auxillary[k++] = comp.compare(comparables[i], comparables[j]) > 0 ? comparables[i++] : comparables[j++];
//		}
//		
//		while(i <= right1) {
//			auxillary[k++] = comparables[i++];
//		}
//		while(j <= right2) {
//			auxillary[k++] = comparables[j++];
//		}
//		
//		return auxillary;
//	}

	public static void mergeSort(Comparable[] comparables, Comparator comp, int left, int right) {

		if (left < right) {

			int median = (right + left) / 2;

			MergeSortRunnable ms1 = new MergeSortRunnable(comparables, comp, left, right);
			MergeSortRunnable ms2 = new MergeSortRunnable(comparables, comp, median + 1, right);

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

			Comparable[] auxillary = merge(comparables, comp, left, median, median + 1, right);
			System.arraycopy(auxillary, 0, comparables, left, right - left + 1);
		}
	}

//	static void mergeSortUnRunnable(Comparable[] comparables, Comparator comp, int left, int right) {
//
//		if (left < right) {
//			int median = (right + left) / 2;
//			mergeSortUnRunnable(comparables, comp, left, median);
//			mergeSortUnRunnable(comparables, comp, median + 1, right);
//
//			Comparable[] auxillary = merge(comparables, comp, left, median, median + 1, right);
//			arraycopy(auxillary, 0, comparables, left, right - left + 1);
//		}
//
//	}

	public static Comparable[] merge(Comparable[] comparables, Comparator comp, int left1, int right1, int left2, int right2) {

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

	public static void arraycopy(Comparable[] auxillary, int i, Comparable[] comparables, int left, int j) {
		System.arraycopy(auxillary, 0, comparables, left, j);
	}

	public static void quickSort(Comparable[] shapes, Comparator comp) {
		
		
	}

}
