package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Manage reading and writing file symptoms
 * 
 * @author Ryan R.
 * @version 1.0.0
 * 
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * Constructor of ReadSymptomDataFromFile
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it,
	 *                 one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Read file from a filepath attribute and create a list of non unique symptoms
	 * 
	 * @return list of symptoms
	 */
	@Override
	public List<String> getSymptoms() {

		List<String> result = new ArrayList<String>();

		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Loop on listOfSymptoms to create a map of symptoms with occurrences
	 * 
	 * @param listOfSymptoms : list of symptoms
	 * @return Map of symptoms with their occurrences order by alphabetical value
	 */
	public static TreeMap<String, Integer> countSymptoms(List<String> listOfSymptoms) {

		TreeMap<String, Integer> mapSymptoms = new TreeMap<String, Integer>();

		for (String lsymptom : listOfSymptoms) {
			mapSymptoms.put(lsymptom, mapSymptoms.containsKey(lsymptom) ? mapSymptoms.get(lsymptom) + 1 : 1);
		}

		return mapSymptoms;
	}

	/**
	 * Read map of symptoms and write a new file with symptoms and occurrences
	 * 
	 * @param mapSymptoms Map of symptoms with their occurrences
	 */
	public static void exportFile(TreeMap<String, Integer> mapSymptoms) {
		try {
			FileWriter writer = new FileWriter("results.out");

			for (String s : mapSymptoms.keySet()) {
				writer.write(s + " : " + mapSymptoms.get(s) + "\r\n");
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
