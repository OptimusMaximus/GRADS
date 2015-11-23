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
	private String department;
	private Term termBegan;
	private Degree degreeSought;
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
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the termBegan
	 */
	public Term getTermBegan() {
		return termBegan;
	}

	/**
	 * @param termBegan the termBegan to set
	 */
	public void setTermBegan(Term termBegan) {
		this.termBegan = termBegan;
	}

	/**
	 * @return the degreeSought
	 */
	public Degree getDegreeSought() {
		return degreeSought;
	}

	/**
	 * @param degreeSought the degreeSought to set
	 */
	public void setDegreeSought(Degree degreeSought) {
		this.degreeSought = degreeSought;
	}

	/**
	 * @return the advisors
	 */
	public List<User> getAdvisors() {
		return advisors;
	}

	/**
	 * @param advisors the advisors to set
	 */
	public void setAdvisors(List<User> advisors) {
		this.advisors = advisors;
	}

	/**
	 * @return the committee
	 */
	public List<User> getCommittee() {
		return committee;
	}

	/**
	 * @param committee the committee to set
	 */
	public void setCommittee(List<User> committee) {
		this.committee = committee;
	}

	/**
	 * @return the results
	 */
	public List<RequirementCheck> getResults(StudentRecord record, List<Course> allCourses) {
		//String degreeName = record.getDegreeSought().getDegreeName();
		
		RequirementCheck r = new RequirementCheck();
		requirementCheckResults = r.generateResults(record, allCourses);
		return requirementCheckResults;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(List<RequirementCheck> results) {
		this.requirementCheckResults = results;
	}
	
}
