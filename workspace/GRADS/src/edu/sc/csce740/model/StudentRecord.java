package edu.sc.csce740.model;

import java.util.List;

import com.google.gson.annotations.Expose;

import edu.sc.csce740.GRADS;

/**
 * Class defining a student record object for students at the University of South Carolina
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
	
	public User getUser(){	
		return student;
	}
	public String getFirstName(){
		return student.getFirstName();
	}
	public String getLastName(){
		return student.getLastName();
	}
	public String getRole(){
		return student.getRole();
	}
	
	public Term getTermBegan() {
		return termBegan;
	}
	public void setTermBegan(Term termBegan) {
		this.termBegan = termBegan;
	}
	public Degree getDegreeSought() {
		return degreeSought;
	}
	public void setDegreeSought(Degree degreeSought) {
		this.degreeSought = degreeSought;
	}
	public Degree getCertificateSought() {
		return certificateSought;
	}
	public void setCertificateSought(Degree certificateSought) {
		this.certificateSought = certificateSought;
	}
	public List<User> getAdvisors() {
		return advisors;
	}
	//TODO: remove "All" from setAllAdvisors
	public void setAdvisor(List<User> advisor) {
		this.advisors = advisor;
	}
//	public void setAdvisor(User advisor){
//		this.advisor.add(advisor);
//	}
	
	public List<User> getCommittee() {
		return committee;
	}
	public void setCommittee(List<User> committee) {
		this.committee = committee;
	}
	public boolean getTempEdit() {
		return tempEdit;
	}
	
	private void setTempEdit(boolean tempEdit) {
		this.tempEdit = tempEdit;
	}
	public String getDept() {
		return department;
	}
	public List<Degree> getPreviousDegrees() {
		return previousDegrees;
	}
	
	public void setCoursesTaken(CourseTaken course){
		if(GRADS.getRole().equals("STUDENT")){
			setTempEdit(true);
		}
		coursesTaken.add(course);
	}
	public List<CourseTaken> getCoursesTaken() {
		return coursesTaken;
	}
	public List<Milestone> getMilestonesSet() {
		return milestonesSet;
	}
	public void addMilestone(Milestone milestone){
		this.milestonesSet.add(milestone);
	}
	public List<String> getNotes() {
		return notes;
	}
	public void addNote(String note){
		this.notes.add(note);
	}
	
	public void setFirstName(String firstName){
		if (GRADS.getRole().equals("STUDENT"))
		{
			setTempEdit(true);
		}
		this.student.setFirstName(firstName);
	}
	
	public void setLastName(String lastName){
		if (GRADS.getRole().equals("STUDENT"))
		{
			setTempEdit(true);
		}
		this.student.setLastName(lastName);
	}
	
}
