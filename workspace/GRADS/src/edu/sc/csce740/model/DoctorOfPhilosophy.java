/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * @author brandemr
 *
 */
public class DoctorOfPhilosophy extends DegreeRequirements {

	private List<Milestone> milestones;
	
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getMinGPA()
//	 */
//	public double getMinGPA() {
//		// TODO Auto-generated method stub
//		return minGPA;
//	}
//	
//	public void setMaxDegreeTime(int time){
//		this.maxDegreeTime = time;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getMaxDegreeTime()
//	 */
//	public int getMaxDegreeTime() {
//		// TODO Auto-generated method stub
//		return maxDegreeTime;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getDissertation()
//	 */
//	public boolean getDissertation() {
//		// TODO Auto-generated method stub
//		return dissertation;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getThesis()
//	 */
//	public boolean getThesis() {
//		// TODO Auto-generated method stub
//		return thesis;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getReport()
//	 */
//	public boolean getReport() {
//		// TODO Auto-generated method stub
//		return report;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getExitExam()
//	 */
//	public boolean getExitExam() {
//		// TODO Auto-generated method stub
//		return exam;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getMinElectiveHours()
//	 */
//	public double getMinElectiveHours() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getRemainingCoreCourses()
//	 */
//	public List<Course> getRemainingCoreCourses() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#checkCoreCourses(edu.sc.csce740.model.Course)
//	 */
//	public boolean checkCoreCourses(Course course) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getCoreCourses()
//	 */
//	public List<Course> getCoreCourses() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see edu.sc.csce740.model.DegreeRequirements#getDegreeName()
//	 */
//	public String getDegreeName() {
//		// TODO Auto-generated method stub
//		return degreeName;
//	}
	
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	//TODO: add checkMilestone to this class for class diagram
	public boolean checkMilestone(Milestone milestone){
		return false;
	}
}
