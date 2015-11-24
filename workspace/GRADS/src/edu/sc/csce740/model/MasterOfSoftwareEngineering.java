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

	/**
	 * Instance variable holding the course object of the internship required for the MSE 
	 */
	private Course internship;
	/**
	 * Instance variable listing the milestones particular to the MSE 
	 */
	private List<Milestone> milestones;
	
	/* 
	 * Method to get the internsip course instance
	 * @returns the internsip course instance
	 */
	@Override
	public Course getInternship(){
		return internship;
	}
	
	/* 
	 * Method to set the internsip course instance
	 * @param internship - the internsip course instance to set
	 */
	@Override
	public void setInternship(Course internship){
		this.internship = internship;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getMilestones()
	 */
	@Override
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#addMilestone(edu.sc.csce740.model.Milestone)
	 */
	@Override
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#checkMilestone(edu.sc.csce740.model.Milestone)
	 */
	@Override
	public boolean checkMilestone(Milestone milestone) {
			return false;
	}

}
