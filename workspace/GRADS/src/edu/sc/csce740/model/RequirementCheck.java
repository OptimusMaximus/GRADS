/**
 * 
 */
package edu.sc.csce740.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.exception.CoursesInvalidException;


/**
 * Class checking and calculating requirements for the <code>ProgressSummary</code>
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class RequirementCheck {

	/**
	 * Instance variable containing the name of the requirement
	 */
	public String name;
	/**
	 * Instance variable containing either "true" or "false" to indicate whether 
	 * this requiremnt is passed or not
	 */
	public String passed;
	/**
	 * Instance variable containing the details about the requirment ()
	 */
	public Details details;
	
	
	public RequirementCheck() {
		
	}


	/**
	 * Method to get the string indicating whether a requirment is passed or not
	 * @return the string indicating whether a requirment is passed or not
	 */
	public String getPassed() {
		return passed;
	}


	/**
	 * Method to set the string indicating whether a requirment is passed or not
	 * @param passed - the string to set 
	 */
	public void setPassed(String passed) {
		this.passed = passed;
	}


	/**
	 * Method to get the  name of the requirment
	 * @return the  name of the requirment
	 */
	public String getName() {
		return name;
	}


	/**
	 * Method to set the  name of the requirment
	 * @param name - the  name of the requirment
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Method to get the details of the requirment
	 * @return the  details of the requirment
	 */
	public Details getDetails() {
		return details;
	}


	/**
	 * Method to set the details of the requirment
	 * @param details -  the details to set
	 */
	public void setDetails(Details details) {
		this.details = details;
	}

	/**
	 * Convenience method for importing flatfile from the hard disk into memory 
	 * @param fileName the <code>String</code> name of the file to be retrieved
	 * @return File being loaded
	 */
	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	/**
	 * This method will generate the results used in the progress summary report. 
	 * @param record, the StudentRecord of the student
	 * @param allCourses, a List of Courses
	 * @return List of RequirementChecks results
	 */
	public List<RequirementCheck> generateResults(StudentRecord record, List<Course> allCourses){
		String degreeName = record.getDegreeSought().getDegreeName();
		List<RequirementCheck> requirementCheckResults = new ArrayList<RequirementCheck>();
		List<String> thesis = null;
		
		DegreeRequirements requirements = getDegreeRequirements(degreeName);
		
		List<CourseTaken> coursesTaken = new ArrayList<CourseTaken>();	
		coursesTaken = removeCoursesWithNoGrade(record.getCoursesTaken());
		
		List<CourseTaken> validCoursesTaken = new ArrayList<CourseTaken>();
		validCoursesTaken = getValidCoursesTaken(coursesTaken, allCourses);		
			
		List<CourseTaken> valid700LevelCoursesTaken = new ArrayList<CourseTaken>();
		valid700LevelCoursesTaken = getValid700LevelCoursesTaken(validCoursesTaken, allCourses);
		
		//Core Course Requirement
		requirementCheckResults.add(getCoreCoursesRequirementCheckResults(record, coursesTaken, getRemainingCourses(coursesTaken, requirements), requirements));
		
		//Additional Credits Requirement
		requirementCheckResults.add(getAdditionalCreditsRequirementCheckResults(record, coursesTaken, valid700LevelCoursesTaken, requirements));

		//Degree Based Credits Requirements
		if(degreeName.equals("PHD") || degreeName.equals("MS") || degreeName.equals("ME")){
			requirementCheckResults.add(getDegreeBasedCreditsRequirementCheckResults(record, coursesTaken, valid700LevelCoursesTaken, requirements));		
		}
		
		//Thesis Requirement
		if(degreeName.equals("PHD") || degreeName.equals("MS")){
			thesis = requirements.getThesisCourseIDs();
			requirementCheckResults.add(getThesisCreditsRequirementCheckResults(record, validCoursesTaken, thesis));
		}
		
		//Time Requirement
		int requiredYears = requirements.getMaxDegreeTime();
		requirementCheckResults.add(getWithinTimeRequirementCheckResults(record, requiredYears));
		
		//GPA Requirement
		requirementCheckResults.add(getGpaRequirementCheckResults(record, calculateGPA(record)));
		
		//Milestones Requirement
		if(!degreeName.equals("INFAS")){
			requirementCheckResults.add(getMilestonesRequirementCheckResults(record, getRemainingMilestones(record)));		
		}
		
		return requirementCheckResults;
	}
	
	/**
	 * This method will generate the results for the GPA requirement 
	 * @param record, the StudentRecord of the student
	 * @param gpa, student's gpa
	 * @return gpaRequirementCheckResults
	 */
	private RequirementCheck getGpaRequirementCheckResults(StudentRecord record, String gpa) {
		RequirementCheck gpaRequirementCheckResults = new RequirementCheck();
		gpaRequirementCheckResults.setName("GPA");
		if (Double.parseDouble(gpa) >= 3.0){
			gpaRequirementCheckResults.setPassed("true");
		} else {
			gpaRequirementCheckResults.setPassed("false");
		}		
		Details gpaDetails = new Details();
		gpaDetails.setGpa(Double.parseDouble(gpa));
		gpaRequirementCheckResults.setDetails(gpaDetails);
		return gpaRequirementCheckResults;
	}
	
	/**
	 * This method will generate the results for the Core Course requirement 
	 * @param record, the StudentRecord of the student
	 * @param coursesTaken, the list of courses taken 
	 * @param coreCoursesRemaining, the list of core courses remaining
	 * @param requirements, the degree requirements
	 * @return coreCoursesRequirementCheckResults
	 */
	private RequirementCheck getCoreCoursesRequirementCheckResults(StudentRecord record, List<CourseTaken> coursesTaken, List<Course> coreCoursesRemaining, DegreeRequirements requirements) {
		RequirementCheck coreCoursesRequirementCheckResults = new RequirementCheck();
		coreCoursesRequirementCheckResults.setName("CORE_COURSES_" + requirements.getDegreeName().toUpperCase());
		
		Details coreCoursesDetails = new Details();
		List<String> note = new ArrayList<String>();
		List<CourseTaken> coreCoursesTaken = getCoreCourses(coursesTaken, requirements);		
		
		//Check to see if there are any expired courses for PHD, if so then take them out of list
		List<CourseTaken> expiredCoursesTaken = expiredCoursesTaken(coreCoursesTaken, requirements);
		List<CourseTaken> updatedCoursesTaken = new ArrayList<CourseTaken>();
		if(requirements.getDegreeName().toUpperCase().equals("PHD")){
			if (expiredCoursesTaken.size() > 0){
				for(int i = 0; i < expiredCoursesTaken.size(); i++){
					for(int j = 0; j < coreCoursesTaken.size(); j++){
						if(!expiredCoursesTaken.get(i).getCourse().getName().equals(coreCoursesTaken.get(j).getCourse().getName())){
							updatedCoursesTaken.add(coreCoursesTaken.get(j));
						}
					}
					note.add(expiredCoursesTaken.get(i).getCourse().getId().toUpperCase() + " course is invalid since " + requirements.getCoursesInvalidYears() + " years have passed.");
					coreCoursesDetails.setNotes(note);
					coreCoursesRequirementCheckResults.setDetails(coreCoursesDetails);
					coreCoursesRemaining.add(expiredCoursesTaken.get(i).getCourse());
				}
			}
		} else{
			updatedCoursesTaken = coreCoursesTaken;
		}
		
		if (0 == coreCoursesRemaining.size()){
			coreCoursesRequirementCheckResults.setPassed("true");
		} else {
			coreCoursesRequirementCheckResults.setPassed("false");
			for(int i = 0; i < coreCoursesRemaining.size(); i++){
				note.add("Must pass " + coreCoursesRemaining.get(i).getId().toUpperCase() + " " + coreCoursesRemaining.get(i).getName() );
			}
			coreCoursesDetails.setNotes(note);
		}
		
		coreCoursesDetails.setCoursesTaken(updatedCoursesTaken);
		coreCoursesRequirementCheckResults.setDetails(coreCoursesDetails);
		return coreCoursesRequirementCheckResults;
	}
	
	/**
	 * This method will generate the results for the Additional Credits requirement 
	 * @param record, the StudentRecord of the student
	 * @param coursesTaken, the list of courses taken 
	 * @param valid700LevelCoursesTaken, the list of 700 level courses remaining
	 * @param requirements, the degree requirements
	 * @return additionalCreditsRequirementCheckResults
	 */
	private RequirementCheck getAdditionalCreditsRequirementCheckResults(StudentRecord record, List<CourseTaken> coursesTaken, List<CourseTaken> valid700LevelCoursesTaken, DegreeRequirements requirements) {
		RequirementCheck additionalCreditsRequirementCheckResults = new RequirementCheck();
		additionalCreditsRequirementCheckResults.setName("ADDITIONAL_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		Details additionalCreditDetails = new Details();
		
		int totalElectiveHoursTaken = 0;
		int remainingElectiveHours = 0;
		int numOfRequiredElectiveHours = getNumberOfAdditionalHours(record.getDegreeSought().getDegreeName());				
		List <String> notes = new ArrayList<String>();
		
		//Check to see if there are any expired courses for PHD, if so then take them out of list
		List<CourseTaken> expiredCoursesTaken = expiredCoursesTaken(valid700LevelCoursesTaken, requirements);
		List<CourseTaken> updatedCoursesTaken = new ArrayList<CourseTaken>();
		
		if(requirements.getDegreeName().toUpperCase().equals("PHD")){
			if (expiredCoursesTaken.size() > 0){
				for(int i = 0; i < expiredCoursesTaken.size(); i++){
					for(int j = 0; j < valid700LevelCoursesTaken.size(); j++){
						if(!expiredCoursesTaken.get(i).getCourse().getName().equals(valid700LevelCoursesTaken.get(j).getCourse().getName())){
							updatedCoursesTaken.add(valid700LevelCoursesTaken.get(j));
						}
					}
					notes.add(expiredCoursesTaken.get(i).getCourse().getId().toUpperCase() + " course is invalid since " + requirements.getCoursesInvalidYears() + " years have passed.");
					additionalCreditDetails.setNotes(notes);
					additionalCreditsRequirementCheckResults.setDetails(additionalCreditDetails);

				}
				valid700LevelCoursesTaken = updatedCoursesTaken;
			}
			
		} 
		
		
		Course course = new Course();		
		if(!requirements.getDegreeName().equals("MSE")){
			for (int i = 0; i < valid700LevelCoursesTaken.size(); i++){
				if (!course.isCoreCourse(record.getDegreeSought().getDegreeName(), valid700LevelCoursesTaken.get(i).getCourse())){
					totalElectiveHoursTaken += Integer.parseInt(valid700LevelCoursesTaken.get(i).getCourse().getCreditHours());
				}
			}
		}
		
		//For ME and MS and PHD
		List<String> name = requirements.getDegreeBasedExcludeCourseIDs();
		if(name != null){
			int totalNotAllowedHours = 0;
			for(int j = 0; j < name.size(); j++){
				for (int i = 0; i < valid700LevelCoursesTaken.size(); i++){	
					if(valid700LevelCoursesTaken.get(i).getCourseTaken().getCourse().getId().contains(name.get(j))){
						totalNotAllowedHours += Integer.parseInt(valid700LevelCoursesTaken.get(i).getCourseTaken().getCourse().getNumCredits());
					}
				}
			}
			if(totalNotAllowedHours > 0){
				remainingElectiveHours += totalNotAllowedHours;
				if(!requirements.getDegreeName().equals("PHD")){
					notes.add(name + " may not be applied to the degree");
				}
			}			
		}
		//For MSE
		List<Course> requiredElectives = requirements.getAdditionalCreditIncludeCourses();
		if(requiredElectives != null){
			int counter = 0;
			for(int i = 0; i < requiredElectives.size(); i++){
				for(int j = 0; j < coursesTaken.size(); j++){
					if(coursesTaken.get(j).getCourse().getName().equals(requiredElectives.get(i).getName())){
						totalElectiveHoursTaken += Integer.parseInt(coursesTaken.get(j).getCourse().getCreditHours());
					}
					if(counter == 0 || coursesTaken.get(j).getCourse().getName().equals(requirements.getInternship().getName())){
						counter = 1;
					}
				}
			}
				
			if(counter > 0){
				String fullName = requirements.getInternship().getId().toUpperCase() + " " + requirements.getInternship().getName();
				notes.add("Students must pass " + fullName);
				additionalCreditDetails.setNotes(notes);
			}
		}
		
		//For INFAS
		int additionalCreditINFASMinHours = requirements.getAdditionalCreditINFASMinHours();
		if(additionalCreditINFASMinHours > 0){
			if(totalElectiveHoursTaken < additionalCreditINFASMinHours){
				notes.add("At least " + additionalCreditINFASMinHours + " hours must be CSCE courses numbered above 700 that are not core courses");
				additionalCreditDetails.setNotes(notes);
			}
			int totalNonCSCEHours = 0;
			for(int i = 0; i < coursesTaken.size(); i++){
				if(!coursesTaken.get(i).getCourse().getId().contains("csce")){
					totalNonCSCEHours += Integer.parseInt(coursesTaken.get(i).getCourse().getCreditHours());
				}
			}
			if(totalNonCSCEHours > requirements.getAdditionalCreditINFASMaxNonCSCEHours()){
				notes.add("Students may count a maximum of 6 hours in nonCSCE courses.");
				additionalCreditDetails.setNotes(notes);
			}
		}
		
		remainingElectiveHours = numOfRequiredElectiveHours - totalElectiveHoursTaken + remainingElectiveHours;
		if(remainingElectiveHours <= 0 ){
			additionalCreditsRequirementCheckResults.setPassed("true");			
			notes.add("Additional credit hours taken is: " + totalElectiveHoursTaken 
					+ ", Additional credit hours needed is: " +  numOfRequiredElectiveHours);
			additionalCreditDetails.setNotes(notes);
			additionalCreditsRequirementCheckResults.setDetails(additionalCreditDetails);
		} else {
			additionalCreditsRequirementCheckResults.setPassed("false");
			notes.add("Must pass "+ remainingElectiveHours +" more hours of CSCE courses numbered above 700 that are not core courses.");
			additionalCreditDetails.setNotes(notes);
			additionalCreditsRequirementCheckResults.setDetails(additionalCreditDetails);
		}
		
		return additionalCreditsRequirementCheckResults;
	}

	/**
	 * This method will generate the results for the Degree Based Credits requirement 
	 * @param record, the StudentRecord of the student
	 * @param coursesTaken, the list of courses taken 
	 * @param valid700LevelCoursesTaken, the list of 700 level courses remaining
	 * @param requirements, the degree requirements
	 * @return degreeBasedCreditsRequirementCheckResults
	 */
	private RequirementCheck getDegreeBasedCreditsRequirementCheckResults(StudentRecord record, 
			List<CourseTaken> coursesTaken, List<CourseTaken> valid700LevelCoursesTaken, DegreeRequirements requirements){
		RequirementCheck degreeBasedCreditsRequirementCheckResults = new RequirementCheck();
		degreeBasedCreditsRequirementCheckResults.setName("DEGREE_BASED_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		Details details = new Details();
		List <String> notes = new ArrayList<String>();
		
		String degreeName = record.getDegreeSought().getDegreeName();
		int numberOfRequiredHours = 0;
		int totalNumberOf700LevelHoursTaken = 0;
		int totalNumberOfHoursTaken = 0;
		int remainingTotalHours = 0;
		int remaining700LevelHours = 0;
		boolean masterDegreeFlag = false;
		
		String tempDegreeName;
		for (int i = 0; i < record.getPreviousDegrees().size(); i++){
			tempDegreeName = record.getPreviousDegrees().get(i).getDegreeName();
			if(tempDegreeName == "MS" || tempDegreeName == "MENG" || tempDegreeName == "MSE"){
				numberOfRequiredHours = getNumberOfDegreeBasedHoursForPreviousMasterDegree(degreeName);
				masterDegreeFlag = true;
				break;
			}
			else{
				numberOfRequiredHours = getNumberOfDegreeBasedHours(degreeName);
			}
		}

			
		//Remove thesis credits from valid courses
		List<CourseTaken> updatedCoursesTaken = new ArrayList<CourseTaken>();
		List<String> thesis = requirements.getThesisCourseIDs();
		int thesisHours = 0;
		if(thesis != null){
			for(int i = 0; i < coursesTaken.size(); i++){
				if(!isThesisCourse(coursesTaken.get(i).getCourseTaken(), thesis)){		        
					updatedCoursesTaken.add(coursesTaken.get(i).getCourseTaken());
				} else{
					thesisHours += Integer.parseInt(coursesTaken.get(i).getCourseTaken().getCourse().getCreditHours());
				}
			}
		} else{
			updatedCoursesTaken = coursesTaken;
		}


		for (int i = 0; i < updatedCoursesTaken.size(); i++){
			totalNumberOfHoursTaken += Integer.parseInt(updatedCoursesTaken.get(i).getCourse().getCreditHours());
		}
		
		for (int i = 0; i < valid700LevelCoursesTaken.size(); i++){
			totalNumberOf700LevelHoursTaken += Integer.parseInt(valid700LevelCoursesTaken.get(i).getCourse().getCreditHours());
		}
		remainingTotalHours = numberOfRequiredHours - totalNumberOfHoursTaken;
		
		//For ME and MS
		int allowedNonCSCEHours = requirements.getDegreeBasedNonCSCEHours();
		if(allowedNonCSCEHours != 0){
			int totalNonCSCEHours = 0;
			for (int i = 0; i < updatedCoursesTaken.size(); i++){			
				if(!updatedCoursesTaken.get(i).getCourseTaken().getCourse().getId().contains("csce")){
					totalNonCSCEHours += Integer.parseInt(updatedCoursesTaken.get(i).getCourseTaken().getCourse().getNumCredits());
				}			
			}

			if(totalNonCSCEHours > allowedNonCSCEHours){
				remainingTotalHours -= totalNonCSCEHours;
				notes.add("May only count a total of  "+ allowedNonCSCEHours +" hours of Non-CSCE courses.");
			}			
		}
		
		//For ME and MS
		int maxAllowedHours = requirements.getDegreeBasedMaxHours();
		if(maxAllowedHours != 0){
			int totalMaxAllowedHours = 0;
			String maxName = requirements.getDegreeBasedMaxCourse().toUpperCase();
			for (int i = 0; i < updatedCoursesTaken.size(); i++){			
				if(updatedCoursesTaken.get(i).getCourseTaken().getCourse().getId().contains(maxName)){
					totalMaxAllowedHours += Integer.parseInt(updatedCoursesTaken.get(i).getCourseTaken().getCourse().getNumCredits());
				}			
			}
			if(totalMaxAllowedHours > maxAllowedHours){
				remainingTotalHours += totalMaxAllowedHours;
				notes.add("At most, " + maxAllowedHours + " hours of "  + maxName + " may be applied toward the degree");
			}			
		}
				
		//For ME and MS and PHD
		List<String> name = requirements.getDegreeBasedExcludeCourseIDs();
		if(name != null){
			int totalNotAllowedHours = 0;
			for(int j = 0; j < name.size(); j++){
				for (int i = 0; i < updatedCoursesTaken.size(); i++){				
					if(updatedCoursesTaken.get(i).getCourseTaken().getCourse().getId().contains(name.get(j))){
						totalNotAllowedHours += Integer.parseInt(updatedCoursesTaken.get(i).getCourseTaken().getCourse().getNumCredits());
					}
				}
			}

			if(totalNotAllowedHours > 0 ){
				remainingTotalHours += totalNotAllowedHours;
				totalNumberOf700LevelHoursTaken += totalNotAllowedHours;
				notes.add(name + " may not be applied to the degree");
			}			
		}

		if(masterDegreeFlag){
			remaining700LevelHours = numberOfRequiredHours - totalNumberOf700LevelHoursTaken + thesisHours;
			if(totalNumberOf700LevelHoursTaken >= numberOfRequiredHours ){
				degreeBasedCreditsRequirementCheckResults.setPassed("true");
				details.setCoursesTaken(valid700LevelCoursesTaken);
			} else{
				degreeBasedCreditsRequirementCheckResults.setPassed("false");
				details.setCoursesTaken(valid700LevelCoursesTaken);
				notes.add("Must pass "+ remaining700LevelHours +" more hours of CSCE courses numbered above 700.");
				details.setNotes(notes);
				
			}
		} else{
			remaining700LevelHours = numberOfRequiredHours/2 - totalNumberOf700LevelHoursTaken + thesisHours;
			if(totalNumberOf700LevelHoursTaken >= numberOfRequiredHours/2 && totalNumberOfHoursTaken >= numberOfRequiredHours){
				degreeBasedCreditsRequirementCheckResults.setPassed("true");
				details.setCoursesTaken(updatedCoursesTaken);
			} else{
				degreeBasedCreditsRequirementCheckResults.setPassed("false");
				details.setCoursesTaken(updatedCoursesTaken);
				notes.add("Must pass " + remainingTotalHours + " more hours of graduate courses.");
				if(degreeName.equals("PHD")){
						notes.add("Must pass "+ remaining700LevelHours +" more hours of CSCE courses numbered above 700.");
					}
				details.setNotes(notes);						
			}
		}	
		degreeBasedCreditsRequirementCheckResults.setDetails(details);
		return degreeBasedCreditsRequirementCheckResults;
	}
	
	/**
	 * This method will generate the results for the Thesis Credits requirement 
	 * @param record, the StudentRecord of the student
	 * @param coursesTaken, the list of valid courses taken 
	 * @param thesis, the list of thesis' required for the degree
	 * @return thesisCreditsRequirementCheckResults
	 */
	private RequirementCheck getThesisCreditsRequirementCheckResults(StudentRecord record,
			List<CourseTaken> validCoursesTaken, List<String> thesis) {
		RequirementCheck thesisCreditsRequirementCheckResults = new RequirementCheck();
		thesisCreditsRequirementCheckResults.setName("THESIS_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		
		String degreeName = record.getDegreeSought().getDegreeName();
		int numberOfRequiredThesisCreditHours = getNumberOfThesisCreditHours(degreeName);
		int totalNumberOfThesisHours = 0;
		int remainingThesisHours = 0;
		List<CourseTaken> thesisCoursesTaken = new ArrayList<CourseTaken>();
		
		for(int i = 0; i < validCoursesTaken.size(); i++){
			if(isThesisCourse(validCoursesTaken.get(i), thesis)){
				totalNumberOfThesisHours += Integer.parseInt(validCoursesTaken.get(i).getCourse().getCreditHours());
				thesisCoursesTaken.add(validCoursesTaken.get(i).getCourseTaken());
			}
		}
		remainingThesisHours = numberOfRequiredThesisCreditHours - totalNumberOfThesisHours;
		
		
		Details details = new Details();
		if(totalNumberOfThesisHours >= numberOfRequiredThesisCreditHours){
			thesisCreditsRequirementCheckResults.setPassed("true");
			details.setCoursesTaken(thesisCoursesTaken);			
		} else{
			thesisCreditsRequirementCheckResults.setPassed("false");					
			List <String> notes = new ArrayList<String>();
			notes.add("Must pass " + remainingThesisHours + " more hours of thesis courses.");
			details.setNotes(notes);
		}		
		thesisCreditsRequirementCheckResults.setDetails(details);
		
		return thesisCreditsRequirementCheckResults;
	}
	
	/**
	 * This method will generate the results for the Within Time requirement 
	 * @param record, the StudentRecord of the student
	 * @param requiredYears, the years the student is allowed for a requirement
	 * @return withinTimeRequirementCheckResults
	 */
	private RequirementCheck getWithinTimeRequirementCheckResults(StudentRecord record, int requiredYears) {
		RequirementCheck withinTimeRequirementCheckResults = new RequirementCheck();
		withinTimeRequirementCheckResults.setName("TIME_LIMIT_" + record.getDegreeSought().getDegreeName().toUpperCase());
		int yearBegan = record.getTermBegan().getYear();
		String semesterBegan = record.getTermBegan().getSemester();
		if (isWithinTime(semesterBegan, yearBegan, requiredYears)){
			withinTimeRequirementCheckResults.setPassed("true");
		} else {
			withinTimeRequirementCheckResults.setPassed("false");
			Details details = new Details();
			List <String> notes = new ArrayList<String>();
			notes.add("The time limit of " + requiredYears + " years has been exceeded");
			details.setNotes(notes);
			withinTimeRequirementCheckResults.setDetails(details);
		}
		
		return withinTimeRequirementCheckResults;
	}

	/**
	 * This method will generate the results for the Milestones requirement 
	 * @param record, the StudentRecord of the student
	 * @param milestonesRemaining, the list of remaining milestones
	 * @return milestonesRequirementCheckResults
	 */
	private RequirementCheck getMilestonesRequirementCheckResults(StudentRecord record, List<Milestone> milestonesRemaining) {
		RequirementCheck milestonesRequirementCheckResults = new RequirementCheck();
		milestonesRequirementCheckResults.setName("MILESTONES_" + record.getDegreeSought().getDegreeName().toUpperCase());
		
		if (0 == milestonesRemaining.size()){
			milestonesRequirementCheckResults.setPassed("true");
		} else {
			milestonesRequirementCheckResults.setPassed("false");
		}
		Details milestoneDetails = new Details();
		List<Milestone> milestones = new ArrayList<Milestone>();
		milestones = record.getMilestonesSet();
		milestoneDetails.setMilestones(milestones);
		milestonesRequirementCheckResults.setDetails(milestoneDetails);
		List<String> notes = new ArrayList<String>();
		for (int i = 0; i < milestonesRemaining.size(); i++){
			notes.add("Missing milestone " + milestonesRemaining.get(i).getMilestone());
		}
		milestoneDetails.setNotes(notes);
		return milestonesRequirementCheckResults;
	}

	/**
	 * This method will get the degree requirements for a degree
	 * @param degreeName, the degree being sought
	 * @return degreeRequirements for the degree
	 */
	private DegreeRequirements getDegreeRequirements(String degreeName){
		List<DegreeRequirements> degreeRequirements = null;
		int index = -1;
		try{
			switch(degreeName){
			case "PHD": 
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<DoctorOfPhilosophy>>() {}.getType());
						break;
			case "MS":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfScience>>() {
						}.getType());
						break;
			case "MSE":	
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfSoftwareEngineering>>() {
						}.getType());
						break;
			case "ME":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<MasterOfEngineering>>() {
						}.getType());
						break;
			case "INFAS":
						degreeRequirements = new Gson().fromJson(new FileReader(getFile("resources/degreeRequirements.txt")),
						new TypeToken<List<SecurityCertificate>>() {
						}.getType());
						break;
			default:
						return null;
						
			}			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < degreeRequirements.size(); i++){
			if(degreeName.equals(degreeRequirements.get(i).getDegreeName())){
				index = i;
			}
		}
		return degreeRequirements.get(index);
	}
	
	/**
	 * This method will calculate the gpa for the given student record 
	 * @param record, the StudentRecord of the student
	 * @return the calculated gpa
	 */
	private String calculateGPA(StudentRecord record){
		int totalAttemptedHours = 0;
		double qualityPoints = 0.0;
		List<CourseTaken> coursesTaken = record.getCoursesTaken();
		
		for(int i = 0; i < coursesTaken.size(); i++){
			int numOfCredits = Integer.parseInt(coursesTaken.get(i).getCourse().getNumCredits());
			totalAttemptedHours = totalAttemptedHours  + numOfCredits;
			String grade = coursesTaken.get(i).getGrade();
			if (grade.toUpperCase().equals("A")){
				qualityPoints = qualityPoints + numOfCredits * 4;
			} else if (grade.toUpperCase().equals("B")){
				qualityPoints = qualityPoints + numOfCredits * 3;
			} else if (grade.toUpperCase().equals("C")){
				qualityPoints = qualityPoints + numOfCredits * 2;
			} else if (grade.toUpperCase().equals("D")){
				qualityPoints = qualityPoints + numOfCredits * 1;
			} else if (grade.toUpperCase().equals("F")){
				qualityPoints = qualityPoints + numOfCredits * 0;
			} else if (grade.toUpperCase().equals("P")){
				totalAttemptedHours = totalAttemptedHours - numOfCredits;
			} else if (grade.toUpperCase().equals("_")){
				totalAttemptedHours = totalAttemptedHours - numOfCredits;
			}				
		}
		DecimalFormat f = new DecimalFormat("##.00");
		return f.format(qualityPoints / totalAttemptedHours);
	}
	
	/**
	 * This method will calculate remaining milestones the student hasn't taken
	 * @param record, the StudentRecord of the student
	 * @return the list of remaining milestones the student hasn't taken
	 */
	private List<Milestone> getRemainingMilestones(StudentRecord record){
		List<Milestone> milestonesRemaining = new ArrayList<Milestone>();
		List<Milestone> milestonesCompleted = record.getMilestonesSet();
		List<String> milestonesCompletedNames = new ArrayList<String>();
		String degreeName = record.getDegreeSought().getDegreeName();
		List<Milestone> allDegreeMilestones = getDegreeRequirements(degreeName).getMilestones();

		if (null != milestonesCompleted){
			for(int i = 0; i < milestonesCompleted.size(); i++){
				milestonesCompletedNames.add(milestonesCompleted.get(i).getMilestone());
			}
		}
		
		for(int i = 0; i < allDegreeMilestones.size(); i++){
			if (!milestonesCompletedNames.contains(allDegreeMilestones.get(i).getMilestone())){
				milestonesRemaining.add(allDegreeMilestones.get(i));
			}
		}
		
		return milestonesRemaining;
	}
	
	/**
	 * This method will calculate remaining milestones the student hasn't taken
	 * @param semesterBegan, the semester the student began
	 * @param yearBegan, the year the student began
	 * @param requiredYears, the number of years the student is allowed for a requirement
	 * @return true if within required time
	 */
	private boolean isWithinTime(String semesterBegan, int yearBegan, int requiredYears){
		int currentSemester;
		int semesterBeganCode = 0;
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		
		if(currentMonth > 0 && currentMonth < 5){
			//Spring
			currentSemester = 1;
		} else if (currentMonth > 7 && currentMonth < 13){
			//Fall
			currentSemester = 2;
		} else {
			//Summer
			currentSemester = 3; 
		}
		
		if(semesterBegan == "SPRING"){
			semesterBeganCode = 1;
		} else if (semesterBegan == "FALL"){
			semesterBeganCode = 2;
		} else {
			semesterBeganCode = 3;
		}
		
		if((currentYear - yearBegan) < requiredYears){
			return true;
		} else if ((currentYear - yearBegan) == requiredYears){
			if(currentSemester >= semesterBeganCode){
				return true;
			} else{
				return false;
			}
		} else {
			return false;
		}

	}
	
	/**
	 * This method will calculate remaining core courses the student hasn't taken
	 * @param coursesCompleted, list of courses taken
 	 * @param requirements, the degree requirments instance
	 * @return the list of remaining core courses the student hasn't taken
	 */
	private List<Course> getRemainingCourses(List<CourseTaken> coursesCompleted, DegreeRequirements requirements){
		List<Course> coursesRemaining = new ArrayList<Course>();
		List<Course> coreCourses = requirements.getCoreCourses();		
		List<String> coursesCompletedIDs = new ArrayList<String>();		
		
		for(int i = 0; i < coursesCompleted.size(); i++){
			coursesCompletedIDs.add(coursesCompleted.get(i).getCourse().getId());
		}
		for(int i = 0; i < coreCourses.size(); i++){
			if (!coursesCompletedIDs.contains(coreCourses.get(i).getCourse().getId())){
				coursesRemaining.add(coreCourses.get(i));
			}
		}
		return coursesRemaining;
	}
	
	/**
	 * This method will calculate degree's core courses the student hasn't taken
	 * @param coursesCompleted, list of courses taken
 	 * @param requirements, the degree requirments instance
	 * @return the list of a degree's core courses the student hasn't taken
	 */
	 private List<CourseTaken> getCoreCourses(List<CourseTaken> coursesCompleted, DegreeRequirements requirements){
		List<Course> coreCourses = requirements.getCoreCourses();		
		List<Course> coursesCompletedList = new ArrayList<Course>();		
		List<CourseTaken> coursesTaken = new ArrayList<CourseTaken>();
		
		for (int i = 0; i < coursesCompleted.size(); i++){
			coursesCompletedList.add(coursesCompleted.get(i).getCourse());
		}
		for (int j = 0; j < coursesCompleted.size(); j++){
			for (int i = 0; i < coreCourses.size(); i++){
				if (coursesCompleted.get(j).getCourse().getId().equals(coreCourses.get(i).getId())){
					coursesTaken.add(coursesCompleted.get(j));
				}
			}		
		}		
		return coursesTaken;
	}
	
	/**
	 * This method determines if a course is in the allCourses "database"
	 * @param courseTaken, list of courses taken
 	 * @param allCourses, list of all courses
	 * @return true if course is within allCourses
	 */	
	 private boolean isValidCourse(CourseTaken courseTaken, List<Course> allCourses){
		for(int i = 0; i < allCourses.size(); i++){
			if (courseTaken.getCourse().courseIsEqual(courseTaken.getCourse(), allCourses.get(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method generates a list of valid courses
	 * @param courseTaken, list of courses taken
 	 * @param allCourses, list of all courses
	 * @return a list of valid courses
	 */
	private List<CourseTaken> getValidCoursesTaken(List<CourseTaken> coursesTaken, List<Course> allCourses){
		List<CourseTaken> validCoursesTaken = new ArrayList<CourseTaken>(); 
		for (int i = 0; i < coursesTaken.size(); i++){
			if((isValidCourse(coursesTaken.get(i), allCourses))){
				validCoursesTaken.add(coursesTaken.get(i).getCourseTaken());
			}			
		}
		return validCoursesTaken;
	}
	
	/**
	 * This method generates a list of valid 700 level courses
	 * @param courseTaken, list of courses taken
 	 * @param allCourses, list of all courses
	 * @return a list of valid 700 level courses
	 */
	private List<CourseTaken> getValid700LevelCoursesTaken(List<CourseTaken> validCoursesTaken, List<Course> allCourses){
		List<CourseTaken> valid700LevelCoursesTaken = new ArrayList<CourseTaken>();
		for (int i = 0; i < validCoursesTaken.size(); i++){
			if((isValid700LevelCourse(validCoursesTaken.get(i)))){
				valid700LevelCoursesTaken.add(validCoursesTaken.get(i).getCourseTaken());
			}			
		}
		return valid700LevelCoursesTaken;
	}
	
	/**
	 * This method determines if a course is a valid 700 level course
	 * @param courseTaken, list of courses taken
	 * @return true if course is level 700 or above
	 */
	private boolean isValid700LevelCourse(CourseTaken courseTaken){
		int level = Integer.parseInt(courseTaken.getCourse().getId().substring(4, 7));
		if(level >= 700 ){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method determines if a course is a thesis course
	 * @param courseTaken, list of courses taken
	 * @param thesis, list of thesis' for a degree
	 * @return true if course is a thesis course
	 */
	private boolean isThesisCourse(CourseTaken courseTaken, List<String> thesis){
		String id = courseTaken.getCourse().getId();
		for(int i = 0; i < thesis.size(); i++){
			if(id.equals(thesis.get(i))){
				return true;
			}
		}
		return false;
	}	

	/**
	 * This method gets the additional credit hours for a degree
	 * @param degreeName, the degree name for the requirement
	 * @return number of additional credit hours needed for a degree
	 */
	private int getNumberOfAdditionalHours(String degreeName) {
		return getDegreeRequirements(degreeName).getAdditionalCreditHours();
	}

	/**
	 * This method gets the degree based credit hours for a degree
	 * @param degreeName, the degree name for the requirement
	 * @return number of degree based credit hours needed for a degree
	 */
	private int getNumberOfDegreeBasedHours(String degreeName) {
		return getDegreeRequirements(degreeName).getDegreeBasedCreditHours();
	}
	
	/**
	 * This method gets the degree based credit hours for a degree, and if the student has a previous master's degree
	 * @param degreeName, the degree name for the requirement
	 * @return number of degree based credit hours needed for a degree, and if the student has a previous master's degree
	 */
	private int getNumberOfDegreeBasedHoursForPreviousMasterDegree(String degreeName) {
		return getDegreeRequirements(degreeName).getDegreeBasedCreditHoursWithValidMasterDegree();
	}
	
	/**
	 * This method gets the thesis credit hours for a degree
	 * @param degreeName, the degree name for the requirement
	 * @return number of thesis credit hours needed for a degree
	 */
	private int getNumberOfThesisCreditHours(String degreeName){
		return getDegreeRequirements(degreeName).getThesisCreditHours();
	}
	
	/**
	 * This method removes courses with a blank grade
	 * @param coursesTaken, list of courses taken
	 * @return a list of courses with blank grade courses removed
	 */
	private List<CourseTaken> removeCoursesWithNoGrade(List<CourseTaken> coursesTaken){
		List<CourseTaken> updatedCoursesTakenBlankGradeRemoved = new ArrayList<CourseTaken>();
		for (int i = 0; i < coursesTaken.size(); i++){			
			if(!coursesTaken.get(i).getCourseTaken().getGrade().equals("_")){
				updatedCoursesTakenBlankGradeRemoved.add(coursesTaken.get(i).getCourseTaken());
			}			
		}
		return updatedCoursesTakenBlankGradeRemoved;
	}
	
	/**
	 * This method gets the expired courses taken
	 * @param coursesTaken, list of courses taken
	 * @param requirements, the degree requirements
	 * @return a list of expired courses
	 */
	private List<CourseTaken> expiredCoursesTaken(List<CourseTaken> coursesTaken, DegreeRequirements requirements){
		List<CourseTaken> expiredCoursesTaken = new ArrayList<CourseTaken>();		
		String semesterBegan = "";
		int yearBegan = 0;			
		for(int i = 0; i < coursesTaken.size(); i++){
			yearBegan = coursesTaken.get(i).getTermTaken().getYear();
			semesterBegan = coursesTaken.get(i).getTermTaken().getSemester();
			if(!isWithinTime(semesterBegan, yearBegan, requirements.getCoursesInvalidYears())){											
				expiredCoursesTaken.add(coursesTaken.get(i));
			}
		}		
		return expiredCoursesTaken;
	}
	
	/**
	 * This method gets the non-expired courses taken
	 * @param coursesTaken, list of courses taken
	 * @param requirements, the degree requirements
	 * @return a list of non-expired courses
	 */
	private List<CourseTaken> nonExpiredCoursesTaken(List<CourseTaken> coursesTaken, DegreeRequirements requirements){
		List<CourseTaken> nonExpiredCoursesTaken = new ArrayList<CourseTaken>();		
		String semesterBegan = "";
		int yearBegan = 0;			
		for(int i = 0; i < coursesTaken.size(); i++){
			yearBegan = coursesTaken.get(i).getTermTaken().getYear();
			semesterBegan = coursesTaken.get(i).getTermTaken().getSemester();
			if(isWithinTime(semesterBegan, yearBegan, requirements.getCoursesInvalidYears())){											
				nonExpiredCoursesTaken.add(coursesTaken.get(i));
			}
		}		
		return nonExpiredCoursesTaken;
	}
}
