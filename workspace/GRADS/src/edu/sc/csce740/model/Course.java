/**
 * 
 */
package edu.sc.csce740.model;

/**
 * Class to define a course object for allCourses
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class Course {
	private String name;
	private String id;
	private String numCredits;
	
	/**
	 * Method to return the name of the course
	 * @return name of the course
	 */
	public String getName() {
		return name;
	}
	/**
	 * Method to set the course name
	 * @param name the name of the course to set
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Method to get the course id of the course
	 * @return the course id of the course
	 */
	public String getId() {
		return id;
	}
	/**
	 * Method to set the id of the course
	 * @param id the id of the course to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Method to get the number of credits for the course
	 * @return String of the number of credits for the course
	 */
	public String getNumCredits() {
		return numCredits;
	}
	/**
	 * Method to set the number of credits for the course
	 * @param numCredits the number of credits for the course
	 */
	public void setNumCredits(String numCredits) {
		this.numCredits = numCredits;
	}

	/**
	 * Method to get the course object
	 * @return the course object in this instance
	 */
	public Course getCourse(){
		return this;
	}
	
	/**
	 * Method to get the number of credits associated with the course	
	 * @return the number of credits for the degree as an integer
	 */
	public String getCreditHours(){
		return numCredits;
	}
	//TODO: change float to double in course diagram
	/**
	 * Method to set a course 
	 * @param ID the course id 
	 * @param name the course title
	 * @param creditHours the hours of credit for this course
	 */
	public void setCourse(String ID, String name, String creditHours){
		this.id = ID;
		this.name = name;
		this.numCredits = creditHours;
	}	
	
	/**
	 * Method to check whether two courses are equivalent in name, id, and number of credits
	 * @param firstCourse the first course to check
	 * @param secondCourse the second course to check
	 * @return true if the courses are equivalent (functionally the same) false otherwise
	 */
	public boolean courseIsEqual(Course firstCourse, Course secondCourse) {
	      if (!firstCourse.getName().equals(secondCourse.getName())){
	    	  return false;
	      }
	      if (!firstCourse.getId().equals(secondCourse.getId())){
	    	  return false;
	      }
	      if (!firstCourse.getNumCredits().equals(secondCourse.getNumCredits())){
	    	  return false;
	      }
	      return true;
	}

}
