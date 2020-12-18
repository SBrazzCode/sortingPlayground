package drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import models.*;
import utilities.AreaComparator;
import utilities.Sort;
import utilities.VolumeComparator;

public class Ass1Driver {

	// If this is wrong, check how the args gets parsed. I'm prett sure it's in a
	// weird way.
	public static void main(String[] args) throws FileNotFoundException {
		HashMap<Character, String> arguments = new HashMap<>();
		Character firstChar;
		String value;

		for (int i = 0; i < args.length; i++) {

			firstChar = Character.toLowerCase(args[i].charAt(1));

			value = args[i];
			value = (value.substring(2, value.length()));
			value.toLowerCase();

			arguments.put(firstChar, value);
		}

		String fileName = arguments.get('f');

		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		int numShapes = scanner.nextInt();
		Comparable[] shapes = new Comparable[numShapes];
		String shapeName;
		double double1;
		double double2;

		System.out.println("Reading Shapes");
		for (int i = 0; i < shapes.length; i++) {
			shapeName = scanner.next();
			double1 = scanner.nextDouble();
			double2 = scanner.nextDouble();

			switch (shapeName) {
			case "Cone":
				shapes[i] = new Cone(double1, double2);
				break;
			case "Cylinder":
				shapes[i] = new Cylinder(double1, double2);
				break;
			case "OctagonalPrism":
				shapes[i] = new OctagonalPrism(double1, double2);
				break;
			case "PentagonalPrism":
				shapes[i] = new PentagonalPrism(double1, double2);
				break;
			case "Pyramid":
				shapes[i] = new Pyramid(double1, double2);
				break;
			case "SquarePrism":
				shapes[i] = new SquarePrism(double1, double2);
				break;
			case "TriangularPrism":
				shapes[i] = new TriangularPrism(double1, double2);
				break;
			default:
				System.out.print("A shape did not match any of our cases");
			}
		}
		scanner.close();

		char compareType = arguments.get('t').charAt(0);
		char sortMethod = arguments.get('s').charAt(0);

		Comparator comp;

		System.out.println("Sorting shapes");
		switch (compareType) {
		case 'h':
			comp = null;
			break;
		case 'a':
			comp = new AreaComparator();
			break;
		case 'v':
			comp = new VolumeComparator();
			break;
		default:
			comp = null;
			break;
		}
		
		long start = System.currentTimeMillis();

		if (comp != null) {
			switch (sortMethod) {
			case 'b':
				Sort.bubbleSort(shapes, comp);
				break;
			case 's':
				Sort.selectionSort(shapes, comp);
				break;
			case 'i':
				Sort.insertionSort(shapes, comp);
				break;
			case 'm':
				Sort.mergeSort(shapes, comp, 0, shapes.length -1);
				break;
			case 'q':
				Sort.quickSort(shapes, comp, 0, shapes.length -1);
				break;
			case 'z':
				Sort.heapSort(shapes, comp);
				break;
			}
		} else {
			switch (sortMethod) {
			case 'b':
				Sort.bubbleSort(shapes);
				break;
			case 's':
				Sort.selectionSort(shapes);
				break;
			case 'i':
				Sort.insertionSort(shapes);
				break;
			case 'm':
				Sort.mergeSort(shapes, 0, shapes.length -1);
				break;
			case 'q':
				Sort.quickSort(shapes, 0, shapes.length -1);
				break;
			case 'z':
				Sort.heapSort(shapes);
				break;
			}
		}
		
		long end = System.currentTimeMillis();


		for (int i = 0; i < shapes.length - 1; i++) {
			System.out.println(shapes[i]);
		}
		System.out.println(shapes[shapes.length - 1]);
		
		System.out.printf("Time took: %6dms", (end - start));
	}

}
