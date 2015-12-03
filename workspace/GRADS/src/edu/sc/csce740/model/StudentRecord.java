package edu.sc.csce740.model;

import java.util.List;

import com.google.gson.annotations.Expose;

import edu.sc.csce740.GRADS;
import edu.sc.csce740.exception.InvalidUserException;

/**
 * Class defining a student record object for students at 
 * the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class StudentRecord {

	private User student;
	private String department;
	private Term termBegan;
	private Degree degreeSought;
	private Degree certificateSought;
	private List<Degree> previousDegrees;
	private List<User> advisors;
	private List<User> committee;
	private List<CourseTaken> coursesTaken;
	private List<Milestone> milestonesSet;
	private List<String> notes;
	private boolean tempEdit = false;
	
	/**
	 * Method to return the user object associated with the record
	 * @return student the student object associated with this record
	 */
	public User getUser(){	
		return student;
	}
	
	/**
	 * Method to set the user for this student record. Sets the temp edit flag in the record if the User object 
	 * flag is set
	 * @param user the user to set for this record
	 */
	public void setUser(User user)
	{
		if (user.getTempEditStatus() == true)
		{
			setTempEdit(true);
		}
		student = user; 
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.department = user.getDepartment();
	}
	
	/**
	 * Method to get the first name from the record
	 * @return firstName - the first name from the record
	 */
	public String getFirstName(){
		return student.getFirstName();
	}
	
	/**
	 * Method to get the last name from the record
	 * @return lirstName - the last name from the record
	 */
	public String getLastName(){
		return student.getLastName();
	}
	
	/**
	 * Method to get the role from the record
	 * @return the role from the record
	 * (either STUDENT or GRADUATE_PROGRAM-COORDINATOR)
	 */
	public String getRole(){
		return student.getRole();
	}
	
	/**
	 * Method to get the term began from the record
	 * @return termBegan - the term began from the record
	 */
	public Term getTermBegan() {
		return termBegan;
	}
	
	/**
	 * Method to set the term at which the stusdent started his degree
	 * @param termBegan - the term at which the stusdent started his degree
	 */
	public void setTermBegan(Term termBegan) {
		tempEditCheck(GRADS.getRole());
		this.termBegan = termBegan;
	}
	
	/**
	 * Method to get the student's degree sought from the record
	 * @return degreeSought - the student's degree sought the record
	 */
	public Degree getDegreeSought() {
		return degreeSought;
	}
	
	/**
	 * Method to get the degree the student is pursuing
	 * @param degreeSought - the degree the student is pursuing
	 */
	public void setDegreeSought(Degree degreeSought) {
		tempEditCheck(GRADS.getRole());
		this.degreeSought = degreeSought;
	}
	
	/**
	 * Method to get the student's certificate sought from the record
	 * @return certificateSought - the student's certificate sought the record
	 */
	public Degree getCertificateSought() {
		return certificateSought;
	}
	
	/**
	 * Method to get the certificate the student is pursuing
	 * @param certificateSought - the certificate the student is pursuing
	 * @throws InvalidUserException if a student attempts to access this field
	 */
	 public void setCertificateSought(Degree certificateSought) throws InvalidUserException {
		 validationCheck(GRADS.getRole()); 
		this.certificateSought = certificateSought;
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
	public void setAdvisor(List<User> advisor) {
		this.advisors = advisor;
	}
	
	/**
	 * Method to get a list of student's committee members from the record
	 * @return committee - a list of student's committee members from the record
	 */
	public List<User> getCommittee() {
		return committee;
	}
	
	/**
	 * Method to set a list of student's committee members
	 * @param committee - a list of student's committee members 
	 */
	public void setCommittee(List<User> committee) {
		this.committee = committee;
	}
	
	/**
	 * Method to get the temporary edit flag in a student record
	 * @return boolean true if the student has edited a field in the record over which they have only temporary 
	 * edit permissions, false otherwise
	 */
	public boolean getTempEdit() {
		return tempEdit;
	}
	
	/**
	 * Method to set the temporary edit flag in a student record
	 * @param boolean true if the student has edited a field in the record over which they have only temporary 
	 * edit permissions, false otherwise
	*/
	private void setTempEdit(boolean tempEdit) {
		this.tempEdit = tempEdit;
	}
	
	/**
	 * Method to get the department of the user from the record
	 * @return department - the user's department a string from the record
	 */
	public String getDept() {
		return department;
	}
	
	/**
	 * Method to get the student's previous degree(s)
	 * @return previousDegrees - the student's list of previous degrees
	 */
	public List<Degree> getPreviousDegrees() {
		return previousDegrees;
	}
	
	/**
	 * Method to add a course to the list of taken course
	 * @param course - the course to be added to the list of taken course
	 */
	 public void setCoursesTaken(CourseTaken course){
		tempEditCheck(GRADS.getRole());
		coursesTaken.add(course);
	}
	
	/**
	 * Method to get a list of all taken courses
	 * @return coursesTaken - list of all taken courses
	 */
	 public List<CourseTaken> getCoursesTaken() {
		return coursesTaken;
	}
	
	/**
	 * Method to get a list of all milestones
	 * @return milestonesSet - list of all milestones
	 */
	public List<Milestone> getMilestonesSet() {
		return milestonesSet;
	}
	
	/**
	 * Method to add a milestone to the list of milestones
	 * @param milestone - the milestone to be added to the list of milestones
	 * @throws InvalidUserException if a student attempts to access this field
	 */
	public void addMilestone(Milestone milestone) throws InvalidUserException{
		validationCheck(GRADS.getRole());
		this.milestonesSet.add(milestone);
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
	 * @throws InvalidUserException if a student attempts to access this field
	 */
	public void addNote(String note) throws InvalidUserException{
		validationCheck(GRADS.getRole());
		this.notes.add(note);
	}
	
	/**
	 * Method to set the first name associated with the user
	 * @param firstName the firstName of the user
	 */
	public void setFirstName(String firstName){
		tempEditCheck(GRADS.getRole());
		this.student.setFirstName(firstName);
	}
	
	/**
	 * Method to set the last name associated with the user
	 * @param lastName the last name of the user
	 */
	public void setLastName(String lastName){
		tempEditCheck(GRADS.getRole());		
		this.student.setLastName(lastName);
	}
	
	/**
	 * Method to set the temporary edit flag if a student edits a field for which they have only 
	 * temporary editing permissions
	 * @param role the role of the current system user
	 */
	private void tempEditCheck(String role)
	{
		if (role.equals("STUDENT"))
		{
			setTempEdit(true);
		}
	}
	
	/**
	 * Method to check if a student is attempting to access an illegal field
	 * @param role the role of the current system user
	 * @throws InvalidUserException if the current system user role is that of a <code>STUDENT</code>
	 */
	private void validationCheck(String role) throws InvalidUserException
	{
		if (role.equals("STUDENT"))
		{
			throw new InvalidUserException("Access Denied: Access Permissions invalid");
		}
	}
}
