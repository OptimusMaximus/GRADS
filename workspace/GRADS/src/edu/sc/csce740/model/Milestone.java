/**
 * 
 */
package edu.sc.csce740.model;

/**
 * Class defining fields of milestones in a degree program at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class Milestone {
	
	/**
	 * Instance variable to with the name of the milestone
	 */
	private String milestone;
	/**
	 * Instance variable with the term the milestone was completed
	 */
	private Term term;
	
	/**
	 * Method to get the term the milestone was completed
	 * @return the term object associated with the milestone
	 */
	public Term getTerm() {
		return term;
	}

	/**
	 * Method to set the term the milestone was completed
	 * @param term the term the milestone was completed
	 */
	public void setTerm(Term term) {
		this.term = term;
	}

	/**
	 * Method to set the milestone name
	 * @param milestone the milestone name as a <code>String</code> 
	 */
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	/**
	 * Method to get the mileston name
	 * @return milestone the milestone name
	 */
	public String getMilestone(){
		return milestone;
	}
	
	/**
	 * Convenience method to set a milestone in one method
	 * @param milestone the milestone name
	 * @param term the term the milestone was completed
	 */
	public void setMilestone(String milestone, Term term){
		this.milestone = milestone;
		this.term = term;
	}
}
