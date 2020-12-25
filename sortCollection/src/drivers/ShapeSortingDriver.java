package drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import models.*;
import utilities.AreaComparator;
import utilities.Sort;
import utilities.VolumeComparator;

/**
 * Can be used to test the sorting methods found in utilities/Sort.java .
 * 
 * @author Shaun Brazzoni
 *
 */
public class ShapeSortingDriver {

	/**
	 * @param args to specify how the program should be run (see readMe.txt)
	 * @throws FileNotFoundException when the file with the shapes to sort is not
	 *                               found.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		HashMap<Character, String> arguments = parseArgs(args);

		System.out.println("Reading Shapes");

		String fileName = arguments.get('f');
		Shape[] shapes = readShapes(fileName);

		char compareType = arguments.get('t').charAt(0);

		// Comparator is used to make comparisons based on BaseArea or Volume.
		Comparator<Shape> comp = getComparator(compareType);

		System.out.println("Sorting shapes");

		long start = System.currentTimeMillis();
		char sortMethod = arguments.get('s').charAt(0);
		sortShapes(shapes, comp, sortMethod);
		long end = System.currentTimeMillis();

		writeOutPutFile(shapes);

		writeConsoleOutput(shapes, start, end);
	}

	/**
	 * Just print the toString for every 1000th shape in shapes
	 * 
	 * @param shapes the array of shapes to print
	 * @param start  the time when the program started sorting
	 * @param end    the time when the program finished sorting
	 */
	private static void writeConsoleOutput(Shape[] shapes, long start, long end) {
		for (int i = 0; i < shapes.length - 1; i += 1000) {
			System.out.println(shapes[i]);
		}
		System.out.println(shapes[shapes.length - 1]);

		System.out.printf("Time took: %6dms", (end - start));
	}

	/**
	 * Write the output file that will be used in JUnit. The output file is compared
	 * with a control file in the JUnit tests (polyfor1AreaCorrect.txt)
	 * 
	 * @param shapes the array of shapes to print
	 * @throws FileNotFoundException
	 */
	private static void writeOutPutFile(Shape[] shapes) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("./output.txt"));

		for (int i = 0; i < shapes.length - 1; i++) {

			// implicitly drop decimal points
			pw.write((int) ((Shape) shapes[i]).getBaseArea());
			pw.write(",");
		}
		pw.close();
	}

	/**
	 * Decides and calls the correct sorting method. The sorting method is
	 * determined by the argument -sx. With x being one of b, s, i, m, q, z.
	 * 
	 * @param shapes     the shapes to be sorted
	 * @param comp       the type of comparison to be made
	 * @param sortMethod the sorting method specified in args.
	 */
	private static void sortShapes(Shape[] shapes, Comparator<Shape> comp, char sortMethod) {

		// There is a comparator if the comparison is to be based on Volume or BaseArea.
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
				Sort.mergeSort(shapes, comp, 0, shapes.length - 1);
				break;
			case 'q':
				Sort.quickSort(shapes, comp, 0, shapes.length - 1);
				break;
			case 'z':
				Sort.heapSort(shapes, comp);
				break;
			}

			// The comparison will just be based on the shapes compareTo method.
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
				Sort.mergeSort(shapes, 0, shapes.length - 1);
				break;
			case 'q':
				Sort.quickSort(shapes, 0, shapes.length - 1);
				break;
			case 'z':
				Sort.heapSort(shapes);
				break;
			}
		}
	}

	/**
	 * Return the correct comparator.
	 * 
	 * @param compareType the char found from args that specifies what comparison to
	 *                    use: h, a, v.
	 * @return the comparator that matches the compareType.
	 */
	private static Comparator<Shape> getComparator(char compareType) {
		switch (compareType) {
		case 'h':
			return null;
		case 'a':
			return new AreaComparator<Shape>();
		case 'v':
			return new VolumeComparator<Shape>();
		default:
			return null;
		}
	}

	/**
	 * Scan through the file specified by fileName and create the respective shape.
	 * 
	 * @param fileName the file name that refers to the file to be sorted.
	 * @return All the shapes in the file specified by fileName
	 * @throws FileNotFoundException
	 */
	private static Shape[] readShapes(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		// The first int is the number of shapes the file contains.
		int numShapes = scanner.nextInt();
		Shape[] shapes = new Shape[numShapes];
		String shapeName;

		// What the doubles are is dependent on what type of shape is being scanned
		// (this isn't known at compile time).
		double double1;
		double double2;

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

		return shapes;
	}

	/**
	 * Retrieve the key-value pairs from the args array.
	 * @param args the args passed to main
	 * @return The key to an argument paired with its value.
	 */
	private static HashMap<Character, String> parseArgs(String[] args) {
		HashMap<Character, String> arguments = new HashMap<>();
		Character firstChar;
		String value;

		for (int i = 0; i < args.length; i++) {

			
			//i.e. -yx: the 1th char is the key (y).
			firstChar = Character.toLowerCase(args[i].charAt(1));

			value = args[i];
			
			//A little dirty, but it just gets the x... from -yx.
			value = (value.substring(2, value.length()));
			value.toLowerCase();

			arguments.put(firstChar, value);
		}

		return arguments;
	}

}
