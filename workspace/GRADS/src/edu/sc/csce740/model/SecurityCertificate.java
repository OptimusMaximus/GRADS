/**
 * 
 */
package edu.sc.csce740.model;

import java.util.List;

/**
 * Class extending degree requirements for the optional Security Certificate in Computer Science at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class SecurityCertificate extends DegreeRequirements {

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getMilestones()
	 */
	@Override
	public List<Milestone> getMilestones() {
		return null;		
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#addMilestone(edu.sc.csce740.model.Milestone)
	 */
	@Override
	public void addMilestone(Milestone milestone) {
		
		
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#checkMilestone(edu.sc.csce740.model.Milestone)
	 */
	@Override
	public boolean checkMilestone(Milestone milestone) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#getInternship()
	 */
	@Override
	public Course getInternship() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.model.DegreeRequirements#setInternship(edu.sc.csce740.model.Course)
	 */
	@Override
	public void setInternship(Course internship) {
		
		
	}

}
