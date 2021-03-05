/**
* Class Name: TagFormatter
* Description: The TagFormatter is to format the given data type
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com.converter.util;

/**
 * @author anil_
 *
 */
public class TagFormatter {

	public static String formatTag(String tagName, String value) {
		StringBuilder tagBuilder = new StringBuilder();
		DataTypeExtractor extractor = new DataTypeExtractor();
		String dataType = extractor.extractDataType(value);
		tagBuilder.append(ApplicationConstants.BEGIN_ELEMENT).append(dataType);

		tagBuilder.append(ApplicationConstants.NAME_TAG).append(tagName).append(ApplicationConstants.DOUBLE_QUOTES);

		tagBuilder.append(ApplicationConstants.BEGIN_END_ELEMENT);
		tagBuilder.append(value);
		tagBuilder.append(ApplicationConstants.END_ELEMENT_TAG).append(dataType).append(ApplicationConstants.BEGIN_END_ELEMENT);
		return tagBuilder.toString();

	}

}
