package edu.sc.csce740.exception;

/**
 * Exception class that checks for invalid courses
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class CoursesInvalidException extends Exception {

	/**
	 * 
	 */
	public CoursesInvalidException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CoursesInvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CoursesInvalidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CoursesInvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CoursesInvalidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
