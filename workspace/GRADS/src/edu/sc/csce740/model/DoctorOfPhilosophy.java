/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * Class extending degree requirements for the Doctor of Philosophy in Computer Science at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class DoctorOfPhilosophy extends DegreeRequirements {

	private List<Milestone> milestones;
	
	@Override
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	@Override
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	//TODO: add checkMilestone to this class for class diagram
	@Override
	public boolean checkMilestone(Milestone milestone){
		return milestones.contains(milestone);
	}

	@Override
	public Course getInternship() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInternship(Course internship) {
		// TODO Auto-generated method stub
		
	}
}
