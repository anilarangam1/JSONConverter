/**
* Class Name: ConverterHelperI
* Description: The ConverterHelperI is an interface
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com.converter.helper;

import org.json.JSONException;

/**
 * 
 * @author: Anil Kumar
 *
 */
public interface ConverterHelperI {

	public String toXML(Object object) throws JSONException;
}
