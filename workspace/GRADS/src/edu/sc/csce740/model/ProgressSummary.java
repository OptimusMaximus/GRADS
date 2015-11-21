package edu.sc.csce740.model;

/**
 * Class generating a progress summary for a given student at the University of South Carolina
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class ProgressSummary {

	private StudentRecord record;
	private RequirementCheck results;
	
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
	public RequirementCheck getResults() {
		String degreeName = record.getDegreeSought().getDegreeName();
		results = new RequirementCheck();
		System.out.println(results.generateResults(record).getNotes());
		//DegreeRequirements degreeRequirements = results.getDegreeRequirements(degreeName);
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(RequirementCheck results) {
		this.results = results;
	}
	
}
