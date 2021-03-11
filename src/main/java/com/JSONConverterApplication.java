
/**
* Class Name: JSONConverterApplication
* Description: The JSONConverterApplication is begining of the JSON to XML API
* @author  Anil Kumar A.B.
* @version 1.0
* @since   2020-03-02 
*/
package com;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.JSONConverterService;  

@SpringBootApplication  
public class JSONConverterApplication implements ApplicationRunner {


	final static Logger logger = Logger.getLogger(JSONConverterApplication.class);
	@Autowired
	JSONConverterService service;
	@Value("${json.application.name}")
    public String applicationName;
	@Value("${server.port}")
	int port;

	/**
	 * Method: main Description: This is the controller or, entry point for this
	 * application
	 * 
	 * @param args - Takes run time arguments
	 */
	
	public static void main(String args[]) {

		//Starting Point for Spring Boot Application....
		SpringApplication.run(JSONConverterApplication.class, args);  
		
	}

	
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("port="+port);
		System.out.println(applicationName);
		service.invokeJSONService();
		
	}

	
}
