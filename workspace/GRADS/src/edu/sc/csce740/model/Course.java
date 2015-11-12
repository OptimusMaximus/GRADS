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
	private String numCredits;
	
	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumCredits() {
		return numCredits;
	}
	public void setNumCredits(String numCredits) {
		this.numCredits = numCredits;
	}

	public Course getCourse(){
		return this;
	}
	
	public String getCreditHours(){
		return numCredits;
	}
	//TODO: change float to double in course diagram
	public void setCourse(String ID, String name, String creditHours){
		this.id = ID;
		this.name = name;
		this.numCredits = creditHours;
	}
	

}
