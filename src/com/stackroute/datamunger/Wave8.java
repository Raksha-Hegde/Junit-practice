package com.stackroute.datamunger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Wave8 {
	private static int rowCount;
	private static BufferedReader bufferedReader;

	public static String getFile(String fileName) {
		String line, readLine = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			if ((line = bufferedReader.readLine()) != null) {
				readLine = line;
			}
			while ((line = bufferedReader.readLine()) != null) {
				readLine = readLine + line;
			}
		} catch (FileNotFoundException e) {
			readLine = null;
		} catch (IOException e) {
			readLine = null;
		}
		return readLine;
	}

	public static int getWordCount(String line) {
		int counter;

		if (line.isEmpty()) {
			counter = 0;
		} else {
			counter = line.split(",").length;
		}
		return counter;
	}

	public static List<String> getFileContents(String fileName) {
		List<String> fileContent = null;
		String line;
		int counter = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			line = bufferedReader.readLine();
			if (line != null) {
				fileContent = new ArrayList<String>();
				while ((line = bufferedReader.readLine()) != null) {
					fileContent.add(line);
					counter++;
				}
			}
			rowCount = counter;
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return fileContent;
	}

	public static int getRowCount() {
		return rowCount;
	}

	public static String getHeaderData(String line, String headerName, String fileName) {
		String columnValue = null;
		Map<String, Integer> head = new LinkedHashMap<String, Integer>();
		String lineArray[] = line.split(",");
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String header = bufferedReader.readLine();
			if (header != null) {
				String headerArray[] = header.split(",");
				for (int i = 0; i < headerArray.length; i++) {
					head.put(headerArray[i], i);
				}
			}
			if (head.get(headerName) != null) {
				columnValue = lineArray[head.get(headerName)];
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		return columnValue;
	}
}
