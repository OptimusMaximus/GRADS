/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * @author brandemr
 *
 */
public class Details {

	/**
	 * 
	 */
	
	public Double gpa;
	public List<CourseTaken> coursesTaken;
	public List<Milestone> milestones;
	public List<String> notes;
	
	
	public Details() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the gpa
	 */
	public Double getGpa() {
		return gpa;
	}


	/**
	 * @param gpa the gpa to set
	 */
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}


	/**
	 * @return the coursesTaken
	 */
	public List<CourseTaken> getCoursesTaken() {
		return coursesTaken;
	}


	/**
	 * @param coursesTaken the coursesTaken to set
	 */
	public void setCoursesTaken(List<CourseTaken> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}


	/**
	 * @return the milestones
	 */
	public List<Milestone> getMilestones() {
		return milestones;
	}


	/**
	 * @param milestones the milestones to set
	 */
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}


	/**
	 * @return the notes
	 */
	public List<String> getNotes() {
		return notes;
	}


	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

}
