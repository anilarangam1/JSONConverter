/**
 * Class Name: ConverterFactory
 * Description: This is an factory class which always creates single instance of XMLJSONConverterI
 * @author  Anil Kumar A.B.
 * @version 1.0
 * @since   2020-03-02 
*/
package com.converter.factory;

import com.converter.XMLJSONConverterI;
import com.converter.impl.XMLJSONConverterImpl;

public class ConverterFactory {
	
   //It represents instance variable for XMLJSONConverterI
	private static XMLJSONConverterI xMLJSONConverterI = null;

	/**
	 * Description: This is an private constructor used to avoid creating the objects using new operator
	 * Design Pattern: Singleton (Factory Pattern)
	 */
	private ConverterFactory() {
        //Empty Implementation for private constructor
	}
	
	/**
	 * Method: getInstance()
	 * Description: This method is to return single/same instance of XMLJSONConverterI 
	 * @return - XMLJSONConverterI
	 */
	public static XMLJSONConverterI getInstance()
	{
		if (xMLJSONConverterI ==null)
		{
			xMLJSONConverterI = new XMLJSONConverterImpl();
		}
		
		return xMLJSONConverterI;
		
	}

}
