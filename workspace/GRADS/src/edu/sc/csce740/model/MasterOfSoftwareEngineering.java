/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * @author brandemr
 *
 */
public class MasterOfSoftwareEngineering implements DegreeRequirements {

	private Course internship;
	
	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getMinGPA()
	 */
	public double getMinGPA() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getMaxDegreeTime()
	 */
	public int getMaxDegreeTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getDissertation()
	 */
	public boolean getDissertation() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getThesis()
	 */
	public boolean getThesis() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getReport()
	 */
	public boolean getReport() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getExitExam()
	 */
	public boolean getExitExam() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getMinElectiveHours()
	 */
	public double getMinElectiveHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getRemainingCoreCourses()
	 */
	public List<Course> getRemainingCoreCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#checkCoreCourses(edu.sc.csce740.model.Course)
	 */
	public boolean checkCoreCourses(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getCoreCourses()
	 */
	public List<Course> getCoreCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getDegreeName()
	 */
	public String getDegreeName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Course getInternship(){
		return internship;
	}
	
	public void getInternship(Course internship){
		this.internship = internship;
	}

}
