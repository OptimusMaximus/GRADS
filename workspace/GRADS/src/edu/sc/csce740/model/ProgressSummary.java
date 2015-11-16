package edu.sc.csce740.model;

public class ProgressSummary {

	private StudentRecord record;
	private RequirementCheck results;
	
	public RequirementCheck getResults(String userID){
		results = new RequirementCheck();
		DegreeRequirements degreeRequirements = results.getDegreeRequirements("PHD");
		return null;
	}
	
	
}
