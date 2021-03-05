/**
* Class Name: ConverterHelperImpl
* Description: The ConverterHelperImpl is an implementation of ConverterHelperI
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com.converter.helper.impl;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XMLParserConfiguration;

import com.converter.helper.ConverterHelperI;
import com.converter.util.ApplicationConstants;
import com.converter.util.TagFormatter;

public class ConverterHelperImpl implements ConverterHelperI {

	final static Logger logger = Logger
			.getLogger(ConverterHelperImpl.class);
	/**
	 * Method: toXML
	 * Description: This method takes an object and returns the xml
	 */
	@Override
	public String toXML(Object object) throws JSONException {
		logger.debug("Inside toXML()"); 
        return toString(object, null, XMLParserConfiguration.ORIGINAL);
	}

	/**
	 * Method: toString
	 * Description: This is the core method which extracts the json elements and figure out the data type for each element
	 * @param object - It represents JSON object
	 * @param tagName - Element Name
	 * @param config - XMLParserConfiguration
	 * @return - Output - Converted XML from JSON
	 */
	private String toString(final Object object, final String tagName, final XMLParserConfiguration config) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
        JSONArray ja;
        JSONObject jo;
        String string;

		logger.debug("Inside toString()");
		
        if (object instanceof JSONObject) {
        	//Emit Tags
            if (tagName != null) {
            	sb.append(ApplicationConstants.BEGIN_ELEMENT);
            	sb.append(ApplicationConstants.ROOT_ELEMENT_OBJECT).append("\"");
                sb.append(tagName);
                sb.append(ApplicationConstants.END_ELEMENT_QUOTES);
            }

            // Loop thru the keys.
            
            jo = (JSONObject) object;
            for (final String key : jo.keySet()) {
                Object value = jo.opt(key);
                if (value == null) {
                    value = ApplicationConstants.EMPTY;
                } else if (value.getClass().isArray()) {
                    value = new JSONArray(value);
                }

               if (value instanceof JSONArray) {
                    ja = (JSONArray) value;
                    int jaLength = ja.length();
                    sb.append(ApplicationConstants.BEGIN_ELEMENT).append(ApplicationConstants.ROOT_ELEMENT_ARRAY).append("\"");
                    sb.append(key).append(ApplicationConstants.END_ELEMENT_QUOTES);
					for (int i = 0; i < ja.length(); i++) {
                        Object val = ja.opt(i);
                        if (val instanceof JSONArray) {
                        	sb.append(ApplicationConstants.ROOT_ELEMENT);
                            sb.append(toString(val, null, config));
                            sb.append(ApplicationConstants.ROOT_ELEMENT_END);
                        } else {
                        	sb.append(toString(val, key, config));
                        }
                        if((i+1) == jaLength)
                        	sb.append(ApplicationConstants.ROOT_ELEMENT_ARRAY_END);
                    }
                } else if (ApplicationConstants.EMPTY.equals(value)) {
                    sb.append(ApplicationConstants.BEGIN_ELEMENT);
                    sb.append(key);
                    sb.append(ApplicationConstants.END_ELEMENT);
                } else {
                    sb.append(toString(value, key, config));
                }
            }
            if (tagName != null) {

            	sb.append(ApplicationConstants.ROOT_ELEMENT_END);
          }
            return sb.toString();

        }
       
        if (object != null && (object instanceof JSONArray ||  object.getClass().isArray())) {
            if(object.getClass().isArray()) {
                ja = new JSONArray(object);
            } else {
                ja = (JSONArray) object;
            }
            for (int i = 0; i < ja.length(); i++) {
                Object val = ja.opt(i);
                sb.append(toString(val, tagName == null ? ApplicationConstants.ARRAY_ELEMENT : tagName, config));
            }
            return sb.toString();
        }
        
        string = (object == null) ? ApplicationConstants.NULL_DATATYPE : object.toString();
        logger.debug("Before Inovking the TagFormatter");        
        return (tagName == null) ? ApplicationConstants.DOUBLE_QUOTES + string + ApplicationConstants.DOUBLE_QUOTES
                : (string.length() == 0) ? ApplicationConstants.BEGIN_ELEMENT + tagName + ApplicationConstants.END_ELEMENT : TagFormatter.formatTag(tagName, string);
	}
	
	
}
