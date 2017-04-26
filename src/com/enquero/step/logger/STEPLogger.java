package com.enquero.step.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class STEPLogger {
	
	/** The Constant SPLIT_DOT. */
	private static final String SPLIT_DOT = "\\.";
	
	/** The Constant STR_METHOD_PREFIX. */
	private static final String STR_METHOD_PREFIX = " [";

	/** The Constant STR_METHOD_SUFFIX. */
	private static final String STR_METHOD_SUFFIX = "] ";
	
	/** The Constant DEFAULT. */
	private static final String DEFAULT = "";
	
	static Logger logger = null;
	
	private STEPLogger(){
		
	}
	
	public static void infoLog(String format, Object org){
		logger.info(format, org);
	}
	
	public static void infoLog(String format){
		logger.info(format);
	}
	
	@SuppressWarnings("rawtypes")
	public static void infoLog(Class objClass, String methodName,
			Object message){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message);
		logger.info(formattedMsg);
	}
	
	@SuppressWarnings("rawtypes")
	public static void infoLog(Class objClass, String methodName,
			Object message, Object value){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message, value);
		logger.info(formattedMsg);
	}
	
	public static void debugLog(String format, Object org){
		logger.debug(format, org);
	}
	
	public static void debugLog(String format){
		logger.debug(format);
	}
	
	@SuppressWarnings("rawtypes")
	public static void debugLog(Class objClass, String methodName,
			Object message){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message);
		logger.debug(formattedMsg);
	}
	
	@SuppressWarnings("rawtypes")
	public static void debugLog(Class objClass, String methodName,
			Object message, Object value){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message, value);
		logger.debug(formattedMsg);
	}
	
	public static void errorLog(String format, Object org){
		logger.error(format, org);
	}
	
	public static void errorLog(String format){
		logger.error(format);
	}
	
	@SuppressWarnings("rawtypes")
	public static void errorLog(Class objClass, String methodName,
			Object message){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message);
		logger.error(formattedMsg);
	}
	
	@SuppressWarnings("rawtypes")
	public static void errorLog(Class objClass, String methodName,
			Object message, Object value){
		getLogger(objClass);
		String formattedMsg = formatMessage(objClass, methodName, message, value);
		logger.error(formattedMsg);
	}
	
	@SuppressWarnings("rawtypes")
	protected static String formatMessage(Class objClass, String methodName,
			Object message) {
		StringBuilder sb = new StringBuilder();
		if (objClass != null) {
			sb.append(objClass.getName().substring(
					objClass.getName().indexOf(
							objClass.getName().split(SPLIT_DOT)[0]),
					objClass.getName().length()));
		} else {
			sb.append(objClass);
		}

		sb.append(STR_METHOD_PREFIX);
		sb.append(methodName);
		sb.append(STR_METHOD_SUFFIX);

		sb.append(message);

		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	protected static String formatMessage(Class objClass, String methodName,
			Object message, Object value) {
		StringBuilder sb = new StringBuilder();
		if (objClass != null) {
			sb.append(objClass.getName().substring(
					objClass.getName().indexOf(
							objClass.getName().split(SPLIT_DOT)[0]),
					objClass.getName().length()));
		} else {
			sb.append(objClass);
		}

		sb.append(STR_METHOD_PREFIX);
		sb.append(methodName);
		sb.append(STR_METHOD_SUFFIX);

		sb.append(message);
		sb.append(" ");
		sb.append(value);

		return sb.toString();
	}
	
	/**
	 * 
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected static Logger getLogger(Class objClass) {
		String logFileName;
		if(objClass != null){
			logFileName = objClass.getName().trim();
		} else {
			logFileName = DEFAULT;
		}
		if (logFileName != null) {
			logger = LoggerFactory.getLogger(DEFAULT);
		}
		if (logger == null) {
			logger = LoggerFactory.getLogger(DEFAULT);
		}
		return logger;
	}

}
