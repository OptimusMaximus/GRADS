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
	
	/**
	 * Method to get the gpa
	 * @returns the gpa 
	 */
	public Double getGpa() {
		return gpa;
	}

	/**
	 * Method to set the gpa
	 * @param gpa - the gpa to set
	 */
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Method to get a list of all taken courses
	 * @return coursesTaken - list of all taken courses
	 */
	public List<CourseTaken> getCoursesTaken() {
		return coursesTaken;
	}

	/**
	 * Method to add a course to the list of taken course
	 * @param courseTaken - the course to be added to the list of taken course
	 */
	public void setCoursesTaken(List<CourseTaken> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}

	/**
	 * Method to get a list of all milestones
	 * @return milestonesSet - list of all milestones
	 */
	public List<Milestone> getMilestones() {
		return milestones;
	}

	/**
	 * Method to add a milestone to the list of milestones
	 * @param milestones - the mileston to set
	 */
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	/**
	 * Method to get a list of all notes
	 * @return notes - list of all notes
	 */
	public List<String> getNotes() {
		return notes;
	}

	/**
	 * Method to add a note to the list of note
	 * @param notes - the note to be added to the list of note
	 */
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
}
