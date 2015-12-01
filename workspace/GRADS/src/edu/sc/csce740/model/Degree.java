/**
 * 
 */
package edu.sc.csce740.model;

/**
 * Class implementing a degree that is being pursued by a student
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 */
public class Degree {

	
	/**
	 *Instance variable setting the name of the degree being pursued 
	 */
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setGraduation(Term graduation) {
		this.graduation = graduation;
	}

	/**
	 *Instance variable setting the anticipated graduation term object of the student from the degree 
	 */
	private Term graduation;
	
	
	/**
	 * Method to return the degree name of the degree being pursued by the student
	 * @return the degree name of the degree being pursued as a <code>String</code>
	 */
	public String getDegreeName(){
		return name;
	}
	
	/**
	 * Method to get the graduation from the term of the degree being pursued
	 * @return the graduation term object of the student 
	 */
	public Term getGraduation(){
		return graduation;
	}
	
}
