/**
 * 
 */
package edu.sc.csce740.model;

/**
 * @author brandemr
 *
 */
public class Term {
	private String semester;
	private int year;
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setTerm(String semester, int year){
		this.semester = semester;
		this.year = year;
	}
	
}
