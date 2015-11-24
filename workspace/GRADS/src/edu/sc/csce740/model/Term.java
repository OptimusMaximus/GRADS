/**
 * 
 */
package edu.sc.csce740.model;

/**
 * Class defining term fields for a university semester 
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class Term {
	/**
	 * Instance variable containing the semester of the term, either SPRING, FALL, or SUMMER
	 */
	private String semester;
	/**
	 * Instance variable containing the year of the term
	 */
	private int year;
	
	/**
	 * Method to get the semester of the term as a string
	 * @return the semester of the term: FALL, SPRING, or SUMMER
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * Method to set the term semester
	 * @param semester the string name of the semester to set FALL, SPRING, SUMMER
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * Method to get the year of the semester
	 * @return year the year of the term
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Method to set the year of the term
	 * @param year the year of the term 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Convenience method to set all fields of term at one time
	 * @param semester the semester of the term
	 * @param year the year of the term
	 */
	public void setTerm(String semester, int year){
		this.semester = semester;
		this.year = year;
	}
	
}
