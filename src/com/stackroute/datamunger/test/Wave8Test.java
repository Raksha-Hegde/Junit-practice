package com.stackroute.datamunger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.stackroute.datamunger.Wave8;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Wave8Test extends TestCase {
	Wave8 wave;
	String fileName;

	@Before
	public void setup() {
		// setup methods runs, before every test case runs
		// This method is used to initialize the required variables
		wave = new Wave8();
	}

	@After
	public void teardown() {
		// teardown method runs, after every test case run
		// This method is used to clear the initialized variables
		wave = null;
	}

	/*
	 * The following test cases are used to check whether the contents of the
	 * file is properly or not
	 */
	@Test
	public void testGetFile() {
		fileName = "data/employees.csv";
		String lines = wave.getFile(fileName);
		assertNotNull("testGetReadFile(): File content extraction failed. Check getFile() method.", lines);
		assertTrue("testGetFile() : File is empty, no data is written", (new File(fileName).length() > 0));
	}

	@Test
	public void testGetFileFailure() {
		fileName = "data/employee.csv";
		String lines = wave.getFile(fileName);
		assertNull("testGetFileFailure(): File content extraction failed. Check getFile() method.", lines);
	}

	@Test
	public void testGetWordCount() {
		fileName = "data/employees.csv";
		int wordCount, counter = 0;
		List<String> lines = wave.getFileContents(fileName);
		int rowCount = lines.size();
		for (String line : lines) {
			wordCount = wave.getWordCount(line);
			if (wordCount == 6) {
				counter++;
			}
		}
		assertTrue("testGetWordCount() : File is empty, no data is written", (new File(fileName).length() > 0));
		assertTrue("testGetWordCount() : File contains each row with words more than /less than 6",
				(counter == rowCount));
	}

	@Test
	public void testfileRows() {
		fileName = "data/employees.csv";
		int wordCount, counter = 0;
		List<String> lines = wave.getFileContents(fileName);
		int rowCount = lines.size();
		for (String line : lines) {
			wordCount = wave.getWordCount(line);
			if (wordCount == 6) {
				counter++;
			}
		}
		assertTrue("testfileRows() : File is empty, no data is written", (new File(fileName).length() > 0));
		assertTrue("testfileRows() : File contains each row with words more than /less than 6", (counter == rowCount));
		assertEquals("testfileRows() : File contains incorrect number of records. Check the file content", 10,
				wave.getRowCount());
	}

	@Test
	public void testfileRecords() {
		fileName = "data/employees.csv";
		int wordCount, counter = 0;
		List<String> lines = wave.getFileContents(fileName);
		int rowCount = lines.size();
		for (String line : lines) {
			if (line.startsWith("1,")) {
				assertEquals("", "1,Sheryl,John,23,Test Engineer,2", line);
			} else if (line.startsWith("5,")) {
				assertEquals("", "5,Ravi,Varma, 24, Test Enginner,4", line);
			} else if (line.startsWith("10,")) {
				assertEquals("", "10,Vivek,Narayan,32,Tech Lead,10", line);
			}
			wordCount = wave.getWordCount(line);
			if (wordCount == 6) {
				counter++;
			}
		}
		assertTrue("testfileRecords() : File is empty, no data is written", (new File(fileName).length() > 0));
		assertTrue("testfileRecords() : File contains each row with words more than /less than 6",
				(counter == rowCount));
		assertEquals("testfileRecords() : File contains incorrect number of records. Check the file content", 10,
				wave.getRowCount());
	}

	@Test
	public void testgetFirstName() {
		fileName = "data/employees.csv";
		int wordCount, counter = 0;
		List<String> headerValues = new ArrayList<String>();
		List<String> expectedHeaderValues = new ArrayList<String>();
		expectedHeaderValues.add("Sheryl");
		expectedHeaderValues.add("Ashok");
		expectedHeaderValues.add("Sindhu");
		List<String> lines = wave.getFileContents(fileName);
		int rowCount = lines.size();
		for (String line : lines) {
			if (line.contains("Test Engineer")) {
				headerValues.add(wave.getHeaderData(line, "Firstname", fileName));
			}
		}
		assertTrue("testgetFirstName() : File is empty, no data is written", (new File(fileName).length() > 0));
		assertEquals("testgetFirstName() : File contains incorrect number of records. Check the file content", 10,
				wave.getRowCount());
		assertEquals("testgetFirstName() : File contains incorrect records. Check getHeaderData() method", 3,
				headerValues.size());
		assertEquals("testgetFirstName() : File contains incorrect records. Check getHeaderData() method",
				expectedHeaderValues, headerValues);

	}

	@Test
	public void testgetFirstNameFailure() {
		fileName = "data/employees.csv";
		List<String> lines = wave.getFileContents(fileName);

		for (String line : lines) {
			assertNull("testgetFirstNameFailure(): File content extraction failed. Check getHeaderData() method.",
					wave.getHeaderData(line, "First", fileName));
		}

	}
}