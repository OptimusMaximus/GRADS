package edu.sc.csce740.model;

import java.util.List;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.DegreeRequirements;
import edu.sc.csce740.model.Milestone;

/**
 * 
 */

/**
 * @author brandemr
 *
 */
public class MasterOfScience extends DegreeRequirements {

	private List<Milestone> milestones;
	
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
		
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	public boolean checkMilestone(Milestone milestone){
		return false;
	}

}
