/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;


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
	
	public RequirementCheck generateResults(String userID){
		return null;
	}
	
	public DegreeRequirements getDegreeRequirements(String degreeName){
		return null;
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
