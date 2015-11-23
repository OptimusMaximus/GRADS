package edu.sc.csce740.model;


import java.util.ArrayList;
import java.util.List;


/**
 * Class generating a progress summary for a given student at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */

public class ProgressSummary {

	
	private User student;
	/**
	 * Instance variable containing the department of the user 
	 */
	private String department;
	private Term termBegan;
	/**
	 * Instance variable containing the degree a student is pursuing
	 */
	private Degree degreeSought;
	/**
	 * Instance variable containing the student's list of advisors
	 */
	private List<User> advisors;
	private List<User> committee;
	private List<RequirementCheck> requirementCheckResults;
	//private StudentRecord record;

//	/**
//	 * @return the record
//	 */
//	public StudentRecord getRecord() {
//		return record;
//	}
//
//	/**
//	 * @param record the record to set
//	 */
//	public void setRecord(StudentRecord record) {
//		this.record = record;
//	}

	/**
	 * @return the student
	 */
	public User getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(User student) {
		this.student = student;
	}

	/**
	 * Method to get the academic department of the user
	 * @return department the Department of the user as a string
	 */
	public String getDepartment() {
		return department;
	}

	
	/**
	 * Method to set the department associated with the user
	 * @param department - the sring to be set as the department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Method to get the term at which the stusdent started his degree
	 * @return the term at which the stusdent started his degree
	 */
	public Term getTermBegan() {
		return termBegan;
	}

	/**
	 * Method to set the term at which the stusdent started his degree
	 * @param termBegan - the term at which the stusdent started his degree
	 */
	public void setTermBegan(Term termBegan) {
		this.termBegan = termBegan;
	}

	/**
	 * Method to get the degree the student is pursuing
	 * @return the degree the student is pursuing
	 */
	public Degree getDegreeSought() {
		return degreeSought;
	}

	/**
	 * Method to set the degree the student is pursuing
	 * @param degreeSought - the degree the student is pursuing
	 */
	public void setDegreeSought(Degree degreeSought) {
		this.degreeSought = degreeSought;
	}
	
	/**
	 * Method to get a list of student's advisors
	 * @return a list of student's advisors
	 */
	public List<User> getAdvisors() {
		return advisors;
	}
	
	/**
	 * Method to set a list of student's advisors
	 * @param advisors - a list of student's advisors 
	 */
	public void setAdvisors(List<User> advisors) {
		this.advisors = advisors;
	}

	/**
	 * Method to get a list of student's committee members
	 * @return a list of student's committee members
	 */
	public List<User> getCommittee() {
		return committee;
	}

	/**
	 * Method to set a list of student's committee members
	 * @param advisors - a list of student's committee members 
	 */
	public void setCommittee(List<User> committee) {
		this.committee = committee;
	}

	/**
	 * Method to calculate the results based on a student's record 
	 * and all courses
	 * @param record - the student's full record
	 * @param allCourses - 
	 * @return a list of requirments and details about those requiremnts
	 */
	public List<RequirementCheck> getResults(StudentRecord record, List<Course> allCourses) {
		//String degreeName = record.getDegreeSought().getDegreeName();
		
		RequirementCheck r = new RequirementCheck();
		requirementCheckResults = r.generateResults(record, allCourses);
		return requirementCheckResults;
	}
}
