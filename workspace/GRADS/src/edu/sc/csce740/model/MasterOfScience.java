package edu.sc.csce740.model;

import java.util.List;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.DegreeRequirements;
import edu.sc.csce740.model.Milestone;

/**
 * 
 */

/**
 * @author brandemr
 *
 */
public class MasterOfScience extends DegreeRequirements {

	private List<Milestone> milestones;
	
	@Override
	public List<Milestone> getMilestones(){
		return milestones;
	}
	
	@Override
	public void addMilestone(Milestone milestone){
		milestones.add(milestone);
	}

	@Override
	public boolean checkMilestone(Milestone milestone){
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
