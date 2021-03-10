
/**
* Class Name: JSONConverterApplication
* Description: The JSONConverterApplication is begining of the JSON to XML API
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.converter.XMLJSONConverterI;
import com.converter.factory.ConverterFactory;
import com.converter.util.ApplicationConstants;

public class JSONConverterApplication {

	private static XMLJSONConverterI xMLJSONConverterI = ConverterFactory.getInstance();
	final static Logger logger = Logger.getLogger(JSONConverterApplication.class);

	/**
	 * Method: main Description: This is the controller or, entry point for this
	 * application
	 * 
	 * @param args - Takes run time arguments
	 */
	public static void main(String args[]) {

		StringBuilder xml = null;
		String json = null;
		try {
			logger.debug("Before Processing JSON Record");
			json = readJSON();
			if (json == null) {
				logger.error(ApplicationConstants.APPLICATION_FILE_NOT_FOUND_MESSAGE);
			} else {
				xml = convert(readJSON());
				generateOuput(xml);
				logger.info(xml);
			}

		} catch (JSONException | IOException e) {
			logger.error("Exception while parsing JSON" + e.getMessage());
		}
	}

	private static void generateOuput(StringBuilder xml) {

		try {
			Files.write(Paths.get(ApplicationConstants.XML_OUTPUT_FILE_PATH), xml.toString().getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * Method: convert Description: This method is to convert the given JSON to xml
	 * 
	 * @param json - Input data in string format read from file system
	 * @return - It represents xml formed from JSON
	 * @throws JSONException
	 * @throws IOException
	 */

	public static StringBuilder convert(String json) throws JSONException, IOException {
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder xmlBuilder = new StringBuilder();

		logger.debug("Inside convert() method for JSON to XML Conversion");

		// Enclose with Begin - Root Tag
		xmlBuilder.append(ApplicationConstants.ROOT_ELEMENT);

		// Enclose with Processed XML Tag

		xmlBuilder.append(xMLJSONConverterI.convertJSONtoXML(jsonObject, ""));

		// Enclose with Close - Root Tag
		xmlBuilder.append(ApplicationConstants.ROOT_ELEMENT_END);

		return xmlBuilder;
	}

	/**
	 * Method:readJSON() Description: This method is to read JSON from the given
	 * input file
	 * 
	 * @return - return the JSON object
	 */
	public static String readJSON() {
		JSONParser jsonParser = new JSONParser();
		File file = new File(ApplicationConstants.JSON_INPUT_FILE_PATH);
		try {
			if (!file.exists())
				return null;
			FileReader reader = new FileReader(ApplicationConstants.JSON_INPUT_FILE_PATH);

			// Read JSON file
			Object obj = jsonParser.parse(reader);
			logger.debug("Parse JSON :: " + obj);
			return obj.toString();

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (org.json.simple.parser.ParseException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}
