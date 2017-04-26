package com.enquero.step.exception;

public class STEPServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param errorMessage
	 */
	public STEPServiceException(final String errorMessage)
	{
		
		super(errorMessage);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public STEPServiceException(final Throwable cause)
	{
		
		super(cause);
	}
	
	/**
	 * 
	 * @param errorMessage
	 * @param cause
	 */
	public STEPServiceException(final String errorMessage, final Throwable cause)
	{
		
		super(errorMessage, cause);
	}

}
