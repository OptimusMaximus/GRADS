/**
 * 
 */
package edu.sc.csce740.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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
	

	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
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
	      
	      //Check for variable credit hours first
	      if(secondCourse.getCreditHours().contains("-")){
	    	  int creditHours = Integer.parseInt(firstCourse.getNumCredits());
	    	  if (creditHours < 1 || creditHours > 12){
	    		  return false;
	    	  }
	      }	 else if (!firstCourse.getNumCredits().equals(secondCourse.getNumCredits())){
	    	  return false;
	      }
	      return true;
	
	}
	public boolean isCoreCourse(String degreeName, Course course) {
		List<DegreeRequirements> degreeRequirements = null;

		try{
			switch(degreeName){
			case "PHD": 
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<DoctorOfPhilosophy>>() {}.getType());
						for (int i = 0; i < degreeRequirements.get(3).getCoreCourses().size(); i++){
							if (courseIsEqual(course, degreeRequirements.get(3).getCoreCourses().get(i))){
								return true;
							}
						}
						break;
			case "MS":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfScience>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(2).getCoreCourses().size(); i++){
							if (courseIsEqual(course, degreeRequirements.get(2).getCoreCourses().get(i))){
								return true;
							}
						}
						break;
			case "MSE":	
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfSoftwareEngineering>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(1).getCoreCourses().size(); i++){
							if (courseIsEqual(course, degreeRequirements.get(1).getCoreCourses().get(i))){
								return true;
							}
						}
						break;
			case "ME":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfEngineering>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(0).getCoreCourses().size(); i++){
							if (courseIsEqual(course, degreeRequirements.get(0).getCoreCourses().get(i))){
								return true;
							}
						}
						break;
			case "CC":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<SecurityCertificate>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(4).getCoreCourses().size(); i++){
							if (courseIsEqual(course, degreeRequirements.get(4).getCoreCourses().get(i))){
								return true;
							}
						}
						break;
			default:
						return false; 
			}
			} catch (JsonIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return false;
	}
	
}
