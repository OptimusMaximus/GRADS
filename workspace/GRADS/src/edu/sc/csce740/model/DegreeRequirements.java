/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * @author brandemr
 *
 */
public interface DegreeRequirements {

	List<Course> coreCourses = null;
	double minGPA = 0.0;
	int maxDegreeTime = 0;
	boolean dissertation = false;
	boolean thesis = false;
	boolean report = false;
	boolean exam = false;
	double minCSCEElectiveHours = 0.0;
	String degreeName = null;
	
	public double getMinGPA();
	public int getMaxDegreeTime();
	//TODO: add getDissertation to class diagram
	public boolean getDissertation();
	public boolean getThesis();
	//TODO: add getReport to class diagram
	public boolean getReport();
	public boolean getExitExam();
	public double getMinElectiveHours();
	public List<Course> getRemainingCoreCourses();
	public boolean checkCoreCourses(Course course);
	public List<Course> getCoreCourses();
	public String getDegreeName();
	
	
}
