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
	private String ID;
	private double creditHours;
	
	public Course getCourse(){
		return this;
	}
	
	public double getCreditHours(){
		return creditHours;
	}
	//TODO: change float to double in course diagram
	public void setCourse(String ID, String name, double creditHours){
		this.ID = ID;
		this.name = name;
		this.creditHours = creditHours;
	}
	

}
