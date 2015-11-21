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
	
	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
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
			case "MS":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfScience>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(2).getCoreCourses().size(); i++){
							if (course.equals(degreeRequirements.get(2).getCoreCourses().get(i))){
								return true;
							}
						}
			case "MSE":	
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfSoftwareEngineering>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(1).getCoreCourses().size(); i++){
							if (course.equals(degreeRequirements.get(1).getCoreCourses().get(i))){
								return true;
							}
						}
			case "ME":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfEngineering>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(0).getCoreCourses().size(); i++){
							if (course.equals(degreeRequirements.get(0).getCoreCourses().get(i))){
								return true;
							}
						}
			case "CC":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<SecurityCertificate>>() {
						}.getType());
						for (int i = 0; i < degreeRequirements.get(4).getCoreCourses().size(); i++){
							if (course.equals(degreeRequirements.get(4).getCoreCourses().get(i))){
								return true;
							}
						}
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
