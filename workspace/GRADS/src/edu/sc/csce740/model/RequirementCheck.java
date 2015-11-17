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
public class RequirementCheck {
	
	private String name;
	private boolean passed;
	private double gpa;
	private List<Milestone> milestonesRemaining;
	//TODO: remove double milestoneProgress
	private List<String> notes;
	private List<Course> coreCoursesRemaining;
	private List<Course> coursesTaken;
	
	
	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	public RequirementCheck generateResults(String userID){
		return null;
	}
	
	
	public DegreeRequirements getDegreeRequirements(String degreeName){
		List<DegreeRequirements> degreeRequirements = null;
		try {
			degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<DoctorOfPhilosophy>>() {
						}.getType());
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
		//System.out.println(degreeRequirements.get(3).getDegreeName());
		return degreeRequirements.get(3);
	}
	
	private double calculateGPA(StudentRecord record){
		return 0.0;
	}
	
	private List<Milestone> getRemainingMilestones(StudentRecord record){
		return null;
	}
	
	private double getTimeRemaining(StudentRecord record){
		return 0.0;
	}
	
	private boolean calcPassed(RequirementCheck check){
		return false;
	}
	
	private List<Course> getRemainingCourses(StudentRecord record){
		return null;
	}
	
	private boolean clearProgress(String userID){
		return false;
	}
}
