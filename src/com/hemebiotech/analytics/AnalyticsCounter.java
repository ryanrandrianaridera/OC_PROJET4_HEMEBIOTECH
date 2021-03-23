package com.hemebiotech.analytics;

import java.util.List;
import java.util.TreeMap;

/**
 * Entry point of the application (Main)
 * 
 * @author Ryan R.
 * @version 1.0.0
 * 
 */
public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {

		// 1/ function recuperer les symptomes
		ReadSymptomDataFromFile read = new ReadSymptomDataFromFile("symptoms.txt");

		List<String> mySymptoms = read.getSymptoms();

		// 2/ function pour compter tous les symptomes
		TreeMap<String, Integer> mapSymptoms = ReadSymptomDataFromFile.countSymptoms(mySymptoms);

		// 3/ Export fichier
		ReadSymptomDataFromFile.exportFile(mapSymptoms);

	}
}
