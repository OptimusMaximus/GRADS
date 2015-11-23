package edu.sc.csce740.model;

import java.util.List;


/**
 * Class to ...
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
 
public class Details {

	public Double gpa;
	public List<CourseTaken> coursesTaken;
	public List<Milestone> milestones;
	public List<String> notes;
	
	
	public Details() {
	}

	/**
	 * @return the gpa
	 */
	public Double getGpa() {
		return gpa;
	}

	/**
	 * @param gpa - the gpa to set
	 */
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @return a list of courses taken
	 */
	public List<CourseTaken> getCoursesTaken() {
		return coursesTaken;
	}

	/**
	 * @param coursesTaken - the list to set as a list of coursesTaken 
	 */
	public void setCoursesTaken(List<CourseTaken> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}

	/**
	 * @return - a list of milestones
	 */
	public List<Milestone> getMilestones() {
		return milestones;
	}

	/**
	 * @param milestones - the list of milestones to set
	 */
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	/**
	 * @return the list of notes on a requirment
	 */
	public List<String> getNotes() {
		return notes;
	}

	/**
	 * @param notes - the list of notes to set
	 */
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
}
