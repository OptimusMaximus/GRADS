/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * @author brandemr
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
	private double minCSCEElectiveHours;
	private String degreeName;
	
	
	/**
	 * @return the coreCourses
	 */
	public List<Course> getCoreCourses() {
		return coreCourses;
	}
	/**
	 * @param coreCourses the coreCourses to set
	 */
	public void setCoreCourses(List<Course> coreCourses) {
		this.coreCourses = coreCourses;
	}
	/**
	 * @return the minGPA
	 */
	public double getMinGPA() {
		return minGPA;
	}
	/**
	 * @param minGPA the minGPA to set
	 */
	public void setMinGPA(double minGPA) {
		this.minGPA = minGPA;
	}
	/**
	 * @return the maxDegreeTime
	 */
	public int getMaxDegreeTime() {
		return maxDegreeTime;
	}
	/**
	 * @param maxDegreeTime the maxDegreeTime to set
	 */
	public void setMaxDegreeTime(int maxDegreeTime) {
		this.maxDegreeTime = maxDegreeTime;
	}
	/**
	 * @return the dissertation
	 */
	public boolean isDissertation() {
		return dissertation;
	}
	/**
	 * @param dissertation the dissertation to set
	 */
	public void setDissertation(boolean dissertation) {
		this.dissertation = dissertation;
	}
	/**
	 * @return the thesis
	 */
	public boolean isThesis() {
		return thesis;
	}
	/**
	 * @param thesis the thesis to set
	 */
	public void setThesis(boolean thesis) {
		this.thesis = thesis;
	}
	/**
	 * @return the report
	 */
	public boolean isReport() {
		return report;
	}
	/**
	 * @param report the report to set
	 */
	public void setReport(boolean report) {
		this.report = report;
	}
	/**
	 * @return the exam
	 */
	public boolean isExam() {
		return exam;
	}
	/**
	 * @param exam the exam to set
	 */
	public void setExam(boolean exam) {
		this.exam = exam;
	}
	/**
	 * @return the minCSCEElectiveHours
	 */
	public double getMinCSCEElectiveHours() {
		return minCSCEElectiveHours;
	}
	/**
	 * @param minCSCEElectiveHours the minCSCEElectiveHours to set
	 */
	public void setMinCSCEElectiveHours(double minCSCEElectiveHours) {
		this.minCSCEElectiveHours = minCSCEElectiveHours;
	}
	/**
	 * @return the degreeName
	 */
	public String getDegreeName() {
		return degreeName;
	}
	/**
	 * @param degreeName the degreeName to set
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	/**
	 * @return the remainingCoreCourses()
	 */
	public List<Course> getRemainingCoreCourses() {
		return null;
	}
	/**
	 * @param course the course to set
	 */
	public boolean checkCoreCourses(Course course) {
		return false;
	}
	
}
