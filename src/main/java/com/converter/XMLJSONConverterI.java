/**
 * Class Name: XMLJSONConverterI
 * Description: The XMLJSONConverterI is interface to convert JSON to XML
 * @author  Anil Kumar A.B.
 * @version 1.0
 * @since   2020-03-02 
*/
package com.converter;

import java.io.IOException;

import org.json.JSONObject;

/**
 * 
 * @author Anil Kumar
 *
 */
public interface XMLJSONConverterI {
	
	public String convertJSONtoXML(JSONObject jsonObject, String outputXML) throws IOException;

}
