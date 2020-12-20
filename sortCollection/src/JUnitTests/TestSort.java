package JUnitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

import drivers.ShapeSortingDriver;

class TestSort {
	final String polyfor1Location = "–fC:\\Users\\832059\\Downloads\\Data_Files_ClassName\\polyfor1.txt";
	final String polyfor3Location = "–fC:\\Users\\832059\\Downloads\\Data_Files_ClassName\\polyfor3.txt";
	final String sortByArea = "-ta";
	final String mergeSort = "-sm";
	
	//naming convention: originalFile (polyfor1.txt), sorted by (Area)
	final String polyfor1AreaCorrect = "./polyfor1AreaCorrect.txt";
	final String currentProgramOutput = "./output.txt";


	@BeforeEach
	void setUp() throws Exception {
		LinkedList<Integer> list = new LinkedList<>();
		
		Scanner correctDataScanner = new Scanner(new File("./sortedArea1.txt"));
		while(correctDataScanner.hasNextInt()) {
			list.add(correctDataScanner.nextInt());
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void mergeSortPolyfor1Test() {
		try {
			ShapeSortingDriver.main(new String[] {polyfor1Location, sortByArea, mergeSort});
			
			//The file .main output the sorted data to.
			File outPutFile = new File(currentProgramOutput);
			
			//an answer key created to test this data.
			File answerKeyFile = new File(polyfor1AreaCorrect);
			
			assertTrue(FileUtils.contentEquals(outPutFile, answerKeyFile));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
//	@Test
//	void mergeSortPolyfor3Test() {
//		try {
//			ShapeSortingDriver.main(new String[] {polyfor3Location, sortByArea, mergeSort});
//			File outPutFile = new File(currentProgramOutput);
//			File answerKeyFile = new File(polyfor1AreaCorrect);
//			boolean b = FileUtils.contentEquals(outPutFile, answerKeyFile);
//			assertTrue(b);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			fail();
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail();
//		}
//	}

}
