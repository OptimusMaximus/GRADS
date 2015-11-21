package edu.sc.csce740.model;

import java.util.List;

/**
 * Class extending degree requirements for the Master of Engineering in Computer Science at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class MasterOfEngineering extends DegreeRequirements {

	/**
	 * Instance variable that sets the milestons particular to the ME
	 */
	private List<Milestone> milestones;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getInternship()
	 */
	@Override
	public Course getInternship() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#setInternship(edu.sc.csce740.model.Course)
	 */
	@Override
	public void setInternship(Course internship) {
		// TODO Auto-generated method stub
		
	}

}
