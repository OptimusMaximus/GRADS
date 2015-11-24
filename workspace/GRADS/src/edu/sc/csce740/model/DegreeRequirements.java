/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * Abstract class defining elements in a given degree or security certificate at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public abstract class DegreeRequirements {

	private List<Course> coreCourses;
	private double minGPA;
	private int maxDegreeTime;
	private boolean dissertation;
	private boolean thesis;
	private boolean report;
	private boolean exam;
	private int additionalCreditHours;
	private List<String> additionalCreditExcludeCourseIDs;
	private List<Course> additionalCreditIncludeCourses;
	private int degreeBasedCreditHours;
	private int degreeBasedNonCSCEHours;
	private String degreeBasedMaxCourse;
	private int degreeBasedMaxHours;
	private List<String> degreeBasedExcludeCourseIDs;
	private int degreeBasedCreditHoursWithValidMasterDegree;
	private int thesisCreditHours;
	private List<String> thesisCourseIDs;
	private String degreeName;
	private int additionalCreditINFASMinHours;
	private int additionalCreditINFASMaxNonCSCEHours;
	private int additionalCreditINFASMaxIncludeMasterHours;
	private int coursesInvalidYears;
	
	
	/**
	 * Method to get the core course objects for the degree
	 * @return a list of course objects that define the core courses for the degree
	 */
	public List<Course> getCoreCourses() {
		return coreCourses;
	}
	/**
	 * Method to set the core course objects for the degree
	 * @param coreCourses the list of core course objects to set for the degree
	 */
	public void setCoreCourses(List<Course> coreCourses) {
		this.coreCourses = coreCourses;
	}
	/**
	 * Method to get the minimum GPA allowed for the degree
	 * @return the minGPA allow for the degree
	 */
	public double getMinGPA() {
		return minGPA;
	}
	/**
	 * Method to set the minimum GPA allowed for the degree
	 * @param minGPA the minGPA to set for the degree
	 */
	public void setMinGPA(double minGPA) {
		this.minGPA = minGPA;
	}
	/**
	 * Method to get the maximum degree time in contiguous years allowed to complete the degree
	 * @return the maxDegreeTime the max degree time allowed to complete the degree
	 */
	public int getMaxDegreeTime() {
		return maxDegreeTime;
	}
	/**
	 * Method to set the maximum degree time in contiguous years allowed to complete the degree
	 * @param maxDegreeTime the maxDegreeTime to set
	 */
	public void setMaxDegreeTime(int maxDegreeTime) {
		this.maxDegreeTime = maxDegreeTime;
	}
	/**
	 * Method to check if a dissertation is required for the degree
	 * @return the dissertation true if so, false else
	 */
	public boolean isDissertation() {
		return dissertation;
	}
	/**
	 * Method to set if a dissertation is required for the degree
	 * @param dissertation the dissertation to set true if so, false else
	 */
	public void setDissertation(boolean dissertation) {
		this.dissertation = dissertation;
	}
	/**
	 * Method to check if a thesis is required for the degree
	 * @return thesis true if so, false else
	 */
	public boolean isThesis() {
		return thesis;
	}
	/**
	 * Method to set if a thesis is required for the degree
	 * @param thesis true if so, false else
	 */
	public void setThesis(boolean thesis) {
		this.thesis = thesis;
	}
	/**
	 * Method to check if a report is required for the degree
	 * @return the report true if so, false else
	 */
	public boolean isReport() {
		return report;
	}
	/**
	 * Method to set if a report is required for the degree
	 * @param report true if so false else
	 */
	public void setReport(boolean report) {
		this.report = report;
	}
	/**
	 * Method to check if an exam is required for the degree
	 * @return the exam true if so false else
	 */
	public boolean isExam() {
		return exam;
	}
	/**
	 * Method to set if an exam is required for the degree
	 * @param exam the exam to set true if so false else
	 */
	public void setExam(boolean exam) {
		this.exam = exam;
	}
	/**
	 * Method to get the minimum additional credit hours required for the degree
	 * @return the additionalCreditHours
	 */
	public int getAdditionalCreditHours() {
		return additionalCreditHours;
	}
	/**
	 * Method to set the minimum additional credit hours for a degree
	 * @param additionalCreditHours the additionalCreditHours to set as a double
	 */
	public void setAdditionalCreditHours(int additionalCreditHours) {
		this.additionalCreditHours = additionalCreditHours;
	}
	/**
	 * @return the degreeBasedCreditHours
	 */
	public int getDegreeBasedCreditHours() {
		return degreeBasedCreditHours;
	}
	/**
	 * @param degreeBasedCreditHours the degreeBasedCreditHours to set
	 */
	public void setDegreeBasedCreditHours(int degreeBasedCreditHours) {
		this.degreeBasedCreditHours = degreeBasedCreditHours;
	}
	/**
	 * @return the degreeBasedCreditHoursWithValidMasterDegree
	 */
	public int getDegreeBasedCreditHoursWithValidMasterDegree() {
		return degreeBasedCreditHoursWithValidMasterDegree;
	}
	/**
	 * @param degreeBasedCreditHoursWithValidMasterDegree :
	 * 	the degreeBasedCreditHoursWithValidMasterDegree to set
	 */
	public void setDegreeBasedCreditHoursWithValidMasterDegree(int degreeBasedCreditHoursWithValidMasterDegree) {
		this.degreeBasedCreditHoursWithValidMasterDegree = degreeBasedCreditHoursWithValidMasterDegree;
	}
	/**
	 * @return the thesisCreditHours
	 */
	public int getThesisCreditHours() {
		return thesisCreditHours;
	}
	/**
	 * @param thesisCreditHours the thesisCreditHours to set
	 */
	public void setThesisCreditHours(int thesisCreditHours) {
		this.thesisCreditHours = thesisCreditHours;
	}
	/**
	 * Method to get the string name of the degree
	 * @return the degreeName as a string
	 */
	public String getDegreeName() {
		return degreeName;
	}
	/**
	 * Method to set the string name of the degree
	 * @param degreeName the degreeName to set as a string
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	/**
	 * Method to get the remaining core courses 
	 * @return the remainingCoreCourses() list of remaining courses required for the degree 
	 */
	public List<Course> getRemainingCoreCourses() {
		return null;
	}
	/**
	 * Method to check if a supplied course is a core course
	 * @param course the course to check
	 * @return true if so false else
	 */
	public boolean checkCoreCourses(Course course) {
		return false;
	}
	
	/**
	 * Abstract method to get the list of milestones for a particular degree
	 * @return the <code>List</code> of milestones for the degree
	 */
	public abstract List<Milestone> getMilestones();
	//public abstract setMilestones();
	
	/**
	 * Abstract method to add milestones to the degree
	 * @param milestone the milestone to add
	 */
	public abstract void addMilestone(Milestone milestone);

	/**
	 * Abstract method to check if a milestone is in the degree 
	 * @param milestone the milestone to be checked
	 * @return true if it is in the degree requirements, else false
	 */
	public abstract boolean checkMilestone(Milestone milestone);
	
	/**
	 * Abstract method to get the internship course object for the degree
	 * @return the internship course object. Null if the degree doesn't require one
	 */
	public abstract Course getInternship();
	
	/**
	 * Abstract class to set the internship for the degree
	 * @param internship the course object of the internship to set
	 */
	public abstract void setInternship(Course internship);
	/**
	 * @return the additionalCreditExcludeCourseIDs
	 */
	public List<String> getAdditionalCreditExcludeCourseIDs() {
		return additionalCreditExcludeCourseIDs;
	}
	/**
	 * @param additionalCreditExcludeCourseIDs the additionalCreditExcludeCourseIDs to set
	 */
	public void setAdditionalCreditExcludeCourseIDs(List<String> additionalCreditExcludeCourseIDs) {
		this.additionalCreditExcludeCourseIDs = additionalCreditExcludeCourseIDs;
	}
	/**
	 * @return the additionalCreditIncludeCourses
	 */
	public List<Course> getAdditionalCreditIncludeCourses() {
		return additionalCreditIncludeCourses;
	}
	/**
	 * @param additionalCreditIncludeCourses the additionalCreditIncludeCourses to set
	 */
	public void setAdditionalCreditIncludeCourses(List<Course> additionalCreditIncludeCourses) {
		this.additionalCreditIncludeCourses = additionalCreditIncludeCourses;
	}
	/**
	 * @return the degreeBasedNonCSCEHours
	 */
	public int getDegreeBasedNonCSCEHours() {
		return degreeBasedNonCSCEHours;
	}
	/**
	 * @param degreeBasedNonCSCEHours the degreeBasedNonCSCEHours to set
	 */
	public void setDegreeBasedNonCSCEHours(int degreeBasedNonCSCEHours) {
		this.degreeBasedNonCSCEHours = degreeBasedNonCSCEHours;
	}
	/**
	 * @return the degreeBasedMaxCourse
	 */
	public String getDegreeBasedMaxCourse() {
		return degreeBasedMaxCourse;
	}
	/**
	 * @param degreeBasedMaxCourse the degreeBasedMaxCourse to set
	 */
	public void setDegreeBasedMaxCourse(String degreeBasedMaxCourse) {
		this.degreeBasedMaxCourse = degreeBasedMaxCourse;
	}
	/**
	 * @return the degreeBasedMaxHours
	 */
	public int getDegreeBasedMaxHours() {
		return degreeBasedMaxHours;
	}
	/**
	 * @param degreeBasedMaxHours the degreeBasedMaxHours to set
	 */
	public void setDegreeBasedMaxHours(int degreeBasedMaxHours) {
		this.degreeBasedMaxHours = degreeBasedMaxHours;
	}
	/**
	 * @return the degreeBasedExcludeCourseIDs
	 */
	public List<String> getDegreeBasedExcludeCourseIDs() {
		return degreeBasedExcludeCourseIDs;
	}
	/**
	 * @param degreeBasedExcludeCourseIDs the degreeBasedExcludeCourseIDs to set
	 */
	public void setDegreeBasedExcludeCourseIDs(List<String> degreeBasedExcludeCourseIDs) {
		this.degreeBasedExcludeCourseIDs = degreeBasedExcludeCourseIDs;
	}
	/**
	 * @return the thesisCourseIDs
	 */
	public List<String> getThesisCourseIDs() {
		return thesisCourseIDs;
	}
	/**
	 * @param thesisCourseIDs the thesisCourseIDs to set
	 */
	public void setThesisCourseIDs(List<String> thesisCourseIDs) {
		this.thesisCourseIDs = thesisCourseIDs;
	}
	/**
	 * @return the additionalCreditINFASMinHours
	 */
	public int getAdditionalCreditINFASMinHours() {
		return additionalCreditINFASMinHours;
	}
	/**
	 * @param additionalCreditINFASMinHours the additionalCreditINFASMinHours to set
	 */
	public void setAdditionalCreditINFASMinHours(int additionalCreditINFASMinHours) {
		this.additionalCreditINFASMinHours = additionalCreditINFASMinHours;
	}
	/**
	 * @return the additionalCreditINFASMaxNonCSCEHours
	 */
	public int getAdditionalCreditINFASMaxNonCSCEHours() {
		return additionalCreditINFASMaxNonCSCEHours;
	}
	/**
	 * @param additionalCreditINFASMaxNonCSCEHours the additionalCreditINFASMaxNonCSCEHours to set
	 */
	public void setAdditionalCreditINFASMaxNonCSCEHours(int additionalCreditINFASMaxNonCSCEHours) {
		this.additionalCreditINFASMaxNonCSCEHours = additionalCreditINFASMaxNonCSCEHours;
	}
	/**
	 * @return the additionalCreditINFASMaxIncludeMasterHours
	 */
	public int getAdditionalCreditINFASMaxIncludeMasterHours() {
		return additionalCreditINFASMaxIncludeMasterHours;
	}
	/**
	 * @param additionalCreditINFASMaxIncludeMasterHours the additionalCreditINFASMaxIncludeMasterHours to set
	 */
	public void setAdditionalCreditINFASMaxIncludeMasterHours(int additionalCreditINFASMaxIncludeMasterHours) {
		this.additionalCreditINFASMaxIncludeMasterHours = additionalCreditINFASMaxIncludeMasterHours;
	}
	/**
	 * @return the coursesInvalidYears
	 */
	public int getCoursesInvalidYears() {
		return coursesInvalidYears;
	}
	/**
	 * @param coursesInvalidYears the coursesInvalidYears to set
	 */
	public void setCoursesInvalidYears(int coursesInvalidYears) {
		this.coursesInvalidYears = coursesInvalidYears;
	}
	
	
}
