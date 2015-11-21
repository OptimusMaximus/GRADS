/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * Class extending degree requirements for the Master of Software Engineering in Computer Science at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class MasterOfSoftwareEngineering extends DegreeRequirements {

	private Course internship;
	private List<Milestone> milestones;
	
	@Override
	public Course getInternship(){
		return internship;
	}
	
	@Override
	public void setInternship(Course internship){
		this.internship = internship;
	}

	@Override
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	@Override
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	@Override
	public boolean checkMilestone(Milestone milestone) {
		// TODO Auto-generated method stub
		return false;
	}

}
