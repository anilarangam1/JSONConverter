/**
* Class Name: DataTypeExtractor
* Description: The DataTypeExtractor is to get the data type for the given elements value
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com.converter.util;

import java.util.Scanner;

/**
 * @author anil_
 *
 */
public class DataTypeExtractor {
	
	/**
	 * Method: extractDataType
	 * Description: This method returns data type for the given JSON Tag Element
	 * @param strInput - Element Input
	 * @return - String - Data Type
	 */

	@SuppressWarnings("resource")
	public String extractDataType(String strInput) {
		Scanner input = new Scanner(strInput);
		
		if(strInput == null)
			return null;

		if (strInput.matches(ApplicationConstants.NUMBER_REGEX))
			return ApplicationConstants.NUMBER_DATATYPE;

		else if (strInput.matches(ApplicationConstants.FLOAT_REGEX))
			return ApplicationConstants.FLOAT_DATATYPE;

	   else if (input.hasNextBoolean())
			return ApplicationConstants.BOOLEAN_DATATYPE;

		else if (input.hasNextLong())
			return ApplicationConstants.LONG_DATATYPE;
		else if (strInput.matches(ApplicationConstants.DATE_REGEX))
			return ApplicationConstants.DATE_DATATYPE;
		else if (input.hasNextLine())
			return ApplicationConstants.STRING_DATATYPE;

		return ApplicationConstants.NULL_DATATYPE;
	}

}
