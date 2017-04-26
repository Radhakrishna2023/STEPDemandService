package com.enquero.step.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.enquero.step.exception.STEPServiceException;
import com.enquero.step.logger.STEPLogger;

public final class CommonUtils {
	
	
	/** The object mapper. */
	private static ObjectMapper objectMapper = new ObjectMapper();

	/** The json factory. */
	private static JsonFactory jsonFactory = new JsonFactory();
	
	/**
	 * 
	 * @param pojo
	 * @return
	 * @throws IOException
	 */
	public static String toJson(Object pojo) throws IOException {
		StringWriter sw = new StringWriter();
		// creating the jsonGenerator
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
		jsonGenerator.useDefaultPrettyPrinter();
		// write the value of pojo into json generator
		objectMapper.writeValue(jsonGenerator, pojo);
		// return the json string
		return sw.toString();
	}
	
	/**
	 * 
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws IOException
	 */
	public static <T> Object toPojo(String jsonAsString, Class<T> pojoClass)
			throws IOException {
		// configuring the columns to object mapper
		objectMapper
		.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		if(jsonAsString!=null){
			byte ptext[] = jsonAsString.getBytes("ISO-8859-1"); 
			String jsonValue = new String(ptext, "UTF-8");
			return objectMapper.readValue(jsonValue, pojoClass);
		}

		// Return the pojo for the given json string
		return objectMapper.readValue(jsonAsString, pojoClass);

	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getDBConnection() throws STEPServiceException{
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/testdb","postgres","Enquero");
		}catch(Exception e){
			STEPLogger.errorLog(CommonUtils.class, "getDBConnection", 
					 "Exception in connection...", e.getMessage());
			throw new STEPServiceException(e);
		}
		return conn;
	}
	

}
