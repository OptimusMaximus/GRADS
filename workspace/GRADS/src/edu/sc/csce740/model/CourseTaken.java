package edu.sc.csce740.model;

public class CourseTaken {

	private Term term;
	

	private String grade;
	//TODO: add this to diagram
	private Course course;
	
	public CourseTaken getCourseTaken(){
		return this;
	}
	
	public void setCourseTaken(String grade, Term term, Course course){
		this.grade = grade;
		this.term = term;
		this.course = course;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Term getTermTaken() {
		return term;
	}

	public void setTermTaken(Term termTaken) {
		this.term = termTaken;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setCourseTaken(Course courseTaken) {
		this.course = courseTaken;
	}
}
