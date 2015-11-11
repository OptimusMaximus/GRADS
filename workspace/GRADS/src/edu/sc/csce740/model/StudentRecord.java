package edu.sc.csce740.model;

import java.util.List;

public class StudentRecord {

	private User student;
	private String dept;
	private Term termBegan;
	private Degree degreeSought;
	private Degree certificateSought;
	private List<Degree> previousDegrees;
	private List<User> advisor;
	private List<User> committee;
	private List<Course> coursesTaken;
	private List<Milestone> milestoneSet;
	private List<String> notes;
	private boolean tempEdit;
	
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
		return advisor;
	}
	//TODO: remove "All" from setAllAdvisors
	public void setAdvisors(List<User> advisor) {
		this.advisor = advisor;
	}
	public void addAdvisor(User advisor){
		this.advisor.add(advisor);
	}
	
	public List<User> getCommittee() {
		return committee;
	}
	public void setCommittee(List<User> committee) {
		this.committee = committee;
	}
	public boolean getTempEdit() {
		return tempEdit;
	}
	//TODO: why setTempEdit private on class diagram
	public void setTempEdit(boolean tempEdit) {
		this.tempEdit = tempEdit;
	}
	public String getDept() {
		return dept;
	}
	public List<Degree> getPreviousDegrees() {
		return previousDegrees;
	}
	public List<Course> getCoursesTaken() {
		return coursesTaken;
	}
	public List<Milestone> getMilestoneSet() {
		return milestoneSet;
	}
	public void addMilestone(Milestone milestone){
		this.milestoneSet.add(milestone);
	}
	public List<String> getNotes() {
		return notes;
	}
	public void addNote(String note){
		this.notes.add(note);
	}
	
	public void setFirstName(String firstName){
		this.student.setFirstName(firstName);
	}
	
	public void setLastName(String lastName){
		this.student.setLastName(lastName);
	}
}
