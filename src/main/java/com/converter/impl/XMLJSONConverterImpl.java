/**
* Class Name: XMLJSONConverterImpl
* Description: The XMLJSONConverterImpl implements XMLJSONConverterI interface
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com.converter.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.converter.XMLJSONConverterI;
import com.converter.helper.ConverterHelperI;
import com.converter.helper.impl.ConverterHelperImpl;

/**
 * @author: Anil Kumar
 */
public class XMLJSONConverterImpl implements XMLJSONConverterI {

	private ConverterHelperI converterHelperI = new ConverterHelperImpl();
	final static Logger logger = Logger
			.getLogger(XMLJSONConverterImpl.class);

	@Override
	/**
	 * Method: convertJSONtoXML
	 * Description: This method invokes converting JSON to XML
	 */
	public String convertJSONtoXML(JSONObject jsonObject, String outputXML) throws IOException {
	
		logger.debug("Inside convertJSONtoXML() - Begin");

		return converterHelperI.toXML(jsonObject);
		
	}

}

