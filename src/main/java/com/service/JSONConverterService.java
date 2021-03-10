/**
 * 
 */
package com.service;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.converter.XMLJSONConverterI;
import com.converter.factory.ConverterFactory;
import com.converter.util.ApplicationConstants;

/**
* Class Name: JSONConverterService
* Description: The JSONConverterService is to invoke conversion
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
@Service
public class JSONConverterService {
	
	private static XMLJSONConverterI xMLJSONConverterI = ConverterFactory.getInstance();
	final static Logger logger = Logger.getLogger(JSONConverterService.class);
	
	@Value("${json.application.input.filename}")
    public String filepath;
	
	@Value("${json.application.output.filename}")
    public String outputfilepath;
	
	@Value("${json.application.filenotfound.msg}")
    public String errormessage;
	
	
    public StringBuilder invokeJSONService()
    {
    	StringBuilder xml = new StringBuilder();
		String json = null;
		try {
			logger.debug("Before Processing JSON Record");
			json = readJSON();
			if (json == null) {
				logger.error(errormessage);
			} else {
				xml = convert(readJSON());
				generateOuput(xml);
				logger.info(xml);
			}

		} catch (JSONException | IOException e) {
			logger.error("Exception while parsing JSON" + e.getMessage());
		}
		return xml;
    }
	private void generateOuput(StringBuilder xml) {

		try {
			Files.write(Paths.get(outputfilepath), xml.toString().getBytes(),
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

	public StringBuilder convert(String json) throws JSONException, IOException {
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
	public String readJSON() {
		JSONParser jsonParser = new JSONParser();
		File file = new File(filepath);
		try {
			if (!file.exists())
				return null;
			FileReader reader = new FileReader(filepath);

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
