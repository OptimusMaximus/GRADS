/**
 * 
 */
package edu.sc.csce740.model;

/**
 * @author brandemr
 *
 */
public class Course {
	private String name;
	private String id;
	private double numCredits;
	
	public Course getCourse(){
		return this;
	}
	
	public double getCreditHours(){
		return numCredits;
	}
	//TODO: change float to double in course diagram
	public void setCourse(String ID, String name, double creditHours){
		this.id = ID;
		this.name = name;
		this.numCredits = creditHours;
	}
	

}
