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

	@Override
	public List<Milestone> getMilestones() {
		return null;		
	}

	@Override
	public void addMilestone(Milestone milestone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkMilestone(Milestone milestone) {
		// TODO Auto-generated method stub
		return false;
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
