/**
 * 
 */
package edu.sc.csce740.model;

/**
 * @author brandemr
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
