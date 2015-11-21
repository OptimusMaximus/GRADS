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
	
	private String milestone;
	private Term term;
	
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getMilestone(){
		return milestone;
	}
	
	public void setMilestone(String milestone, Term term){
		this.milestone = milestone;
		this.term = term;
	}
}
