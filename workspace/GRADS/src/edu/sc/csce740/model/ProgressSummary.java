package edu.sc.csce740.model;

import java.util.List;

public class ProgressSummary {

	private StudentRecord record;
	private RequirementCheck results;
	private List<RequirementCheckResults> requirementCheckResults;
	/**
	 * @return the record
	 */
	public StudentRecord getRecord() {
		return record;
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(StudentRecord record) {
		this.record = record;
	}

	/**
	 * @return the results
	 */
	public List<RequirementCheckResults> getResults() {
		String degreeName = record.getDegreeSought().getDegreeName();
		results = new RequirementCheck();
		requirementCheckResults = results.generateResults(record);

		return requirementCheckResults;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(RequirementCheck results) {
		this.results = results;
	}
	
}
