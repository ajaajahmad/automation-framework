package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

	/**
	 * Reads the PurchaseData.json file and converts it to a list of HashMaps. Each
	 * HashMap represents a JSON object with key-value pairs as Strings.
	 *
	 * @return List of HashMaps containing JSON data
	 * @throws IOException if file reading or parsing fails
	 */
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Read the entire JSON file content into a String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert JSON string to List of HashMaps using Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
	}

}
