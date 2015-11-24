package edu.sc.csce740.exception;

/**
 * Exception class that simulates data that fails to write out to the database
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class DataCanNotBeWrittenException extends Exception {

	/**
	 * 
	 */
	public DataCanNotBeWrittenException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DataCanNotBeWrittenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataCanNotBeWrittenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataCanNotBeWrittenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DataCanNotBeWrittenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
