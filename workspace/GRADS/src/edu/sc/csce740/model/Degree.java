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

	private String name;
	private Term graduation;
	
	public String getDegreeName(){
		return name;
	}
	
	public Term getGraduation(){
		return graduation;
	}
	
}
