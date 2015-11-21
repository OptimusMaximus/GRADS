package edu.sc.csce740.model;

/**
 * Class for implementing the courses that have been taken by a student
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class CourseTaken {
	
	/**
	 * The term when the course is taken
	 */
	private Term term;	

	
	/**
	 * The grade the student received on 4.0 scale 
	 */
	private String grade;
	//TODO: add this to diagram
	
	/**
	 * The course object of this instance
	 */
	private Course course;
	
	/**
	 * Method to get the current course object
	 * @return the current course object
	 */
	public CourseTaken getCourseTaken(){
		return this;
	}
	
	/**
	 * Method to set a course taken
	 * @param grade grade received 
	 * @param term the term the course is taken
	 * @param course the course object to add
	 */
	public void setCourseTaken(String grade, Term term, Course course){
		this.grade = grade;
		this.term = term;
		this.course = course;
	}
	
	/**
	 * Returns the grade for the course
	 * @return String grade of the course
	 */
	public String getGrade(){
		return grade;
	}
	
	/**
	 * Method to get the term the course was taken
	 * @return the term the course was taken
	 */
	public Term getTerm() {
		return term;
	}

	/**
	 * Method to set the term taken for the course
	 * @param term the term to be set
	 */
	public void setTerm(Term term) {
		this.term = term;
	}

	/**
	 * Method to get the course object
	 * @return
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Method to set the course object
	 * @param course the object to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Method to get the term in which the course was taken
	 * @return the term the course was taken
	 */
	public Term getTermTaken() {
		return term;
	}

	/** 
	 * Method to set the term taken
	 * @param termTaken sets the term taken
	 */
	public void setTermTaken(Term termTaken) {
		this.term = termTaken;
	}

	/**
	 * Method to set the grade made by the student in the course
	 * @param grade the grade to set for the student
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * Method to set the course object taken by the student
	 * @param courseTaken the course object taken
	 */
	public void setCourseTaken(Course courseTaken) {
		this.course = courseTaken;
	}
}
