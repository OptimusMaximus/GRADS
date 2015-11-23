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
import java.util.Iterator;
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
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the passed
	 */
	public String getPassed() {
		return passed;
	}


	/**
	 * @param passed the passed to set
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
	 * @return the details
	 */
	public Details getDetails() {
		return details;
	}


	/**
	 * @param details the details to set
	 */
	public void setDetails(Details details) {
		this.details = details;
	}

	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	public List<RequirementCheck> generateResults(StudentRecord record, List<Course> allCourses){
		
		List<RequirementCheck> requirementCheckResults = new ArrayList<RequirementCheck>();
		
		List<CourseTaken> coursesTaken = new ArrayList<CourseTaken>();
		coursesTaken = record.getCoursesTaken();
		
		List<CourseTaken> validCoursesTaken = new ArrayList<CourseTaken>();
		validCoursesTaken = getValidCoursesTaken(coursesTaken, allCourses);		
			
		List<CourseTaken> valid700LevelCoursesTaken = new ArrayList<CourseTaken>();
		valid700LevelCoursesTaken = getValid700LevelCoursesTaken(validCoursesTaken, allCourses);
		
		requirementCheckResults.add(getCoreCoursesRequirementCheckResults(record, getRemainingCourses(record)));
		requirementCheckResults.add(getAdditionalCreditsRequirementCheckResults(record, valid700LevelCoursesTaken));
		requirementCheckResults.add(getDegreeBasedCreditsRequirementCheckResults(record, validCoursesTaken, valid700LevelCoursesTaken));
		requirementCheckResults.add(getThesisCreditsRequirementCheckResults(record, validCoursesTaken));
		requirementCheckResults.add(getWithinTimeRequirementCheckResults(record));
		requirementCheckResults.add(getGpaRequirementCheckResults(record, calculateGPA(record)));
		requirementCheckResults.add(getMilestonesRequirementCheckResults(record, getRemainingMilestones(record)));		
		
		return requirementCheckResults;
	}
	
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

	private RequirementCheck getCoreCoursesRequirementCheckResults(StudentRecord record, List<Course> coreCoursesRemaining) {
		RequirementCheck coreCoursesRequirementCheckResults = new RequirementCheck();
		coreCoursesRequirementCheckResults.setName("CORE_COURSES_" + record.getDegreeSought().getDegreeName().toUpperCase());
		if (0 == coreCoursesRemaining.size()){
			coreCoursesRequirementCheckResults.setPassed("true");
		} else {
			coreCoursesRequirementCheckResults.setPassed("false");
		}
		Details coreCoursesDetails = new Details();
		List<CourseTaken> coursesTaken = new ArrayList<CourseTaken>();
		coursesTaken = record.getCoursesTaken();
		coreCoursesDetails.setCoursesTaken(getCoreCourses(record));
		coreCoursesRequirementCheckResults.setDetails(coreCoursesDetails);
		return coreCoursesRequirementCheckResults;
	}
	
	private RequirementCheck getAdditionalCreditsRequirementCheckResults(StudentRecord record, List<CourseTaken> valid700LevelCoursesTaken) {
		RequirementCheck additionalCreditsRequirementCheckResults = new RequirementCheck();
		additionalCreditsRequirementCheckResults.setName("ADDITIONAL_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		
		int totalElectiveHoursTaken = 0;
		int remainingElectiveHours = 0;
		int numOfRequiredElectiveHours = getNumberOfAdditionalHours(record.getDegreeSought().getDegreeName());				
		
		Course course = new Course();
		for (int i = 0; i < valid700LevelCoursesTaken.size(); i++){
			if (!course.isCoreCourse(record.getDegreeSought().getDegreeName(), valid700LevelCoursesTaken.get(i).getCourse())){
				totalElectiveHoursTaken += Integer.parseInt(valid700LevelCoursesTaken.get(i).getCourse().getCreditHours());
			}
		}		
		remainingElectiveHours = numOfRequiredElectiveHours - totalElectiveHoursTaken;
		
		if(remainingElectiveHours <= 0 ){
			additionalCreditsRequirementCheckResults.setPassed("true");
			Details additionalCreditDetails = new Details();
			List <String> notes = new ArrayList<String>();
			notes.add("Additional credit hours taken is: " + totalElectiveHoursTaken 
					+ ", Additional credit hours needed is: " +  numOfRequiredElectiveHours);
			additionalCreditDetails.setNotes(notes);
			additionalCreditsRequirementCheckResults.setDetails(additionalCreditDetails);
		} else {
			additionalCreditsRequirementCheckResults.setPassed("false");
			Details additionalCreditDetails = new Details();
			List <String> notes = new ArrayList<String>();
			notes.add("Must pass "+ remainingElectiveHours +" more hours of CSCE courses numbered above 700 that are not core courses.");
			additionalCreditDetails.setNotes(notes);
			additionalCreditsRequirementCheckResults.setDetails(additionalCreditDetails);
		}
		
		return additionalCreditsRequirementCheckResults;
	}

	private RequirementCheck getDegreeBasedCreditsRequirementCheckResults(StudentRecord record, 
			List<CourseTaken> validCoursesTaken, List<CourseTaken> valid700LevelCoursesTaken){
		RequirementCheck degreeBasedCreditsRequirementCheckResults = new RequirementCheck();
		degreeBasedCreditsRequirementCheckResults.setName("DEGREE_BASED_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		
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
		List<CourseTaken> coursesTakenWithThesisRemoved = new ArrayList<CourseTaken>();
		for(int i = 0; i < validCoursesTaken.size(); i++){
			if(!isThesisCourse(validCoursesTaken.get(i).getCourseTaken())){		        
				coursesTakenWithThesisRemoved.add(validCoursesTaken.get(i).getCourseTaken());
			}
		}		
		
		//Also remove courses taken that have no grade
		List<CourseTaken> coursesTakenWithThesisAndBlankGradeRemoved = new ArrayList<CourseTaken>();
		for (int i = 0; i < coursesTakenWithThesisRemoved.size(); i++){			
			if(!coursesTakenWithThesisRemoved.get(i).getCourseTaken().getGrade().equals("_")){
				coursesTakenWithThesisAndBlankGradeRemoved.add(coursesTakenWithThesisRemoved.get(i).getCourseTaken());
			}			
		}		

		for (int i = 0; i < coursesTakenWithThesisAndBlankGradeRemoved.size(); i++){
			totalNumberOfHoursTaken += Integer.parseInt(validCoursesTaken.get(i).getCourse().getCreditHours());
		}
		
		for (int i = 0; i < valid700LevelCoursesTaken.size(); i++){
			totalNumberOf700LevelHoursTaken += Integer.parseInt(valid700LevelCoursesTaken.get(i).getCourse().getCreditHours());
		}
		remaining700LevelHours = numberOfRequiredHours - totalNumberOf700LevelHoursTaken;
		remainingTotalHours = numberOfRequiredHours - totalNumberOfHoursTaken;

		Details details = new Details();		
		if(masterDegreeFlag){
			if(totalNumberOf700LevelHoursTaken >= numberOfRequiredHours ){
				degreeBasedCreditsRequirementCheckResults.setPassed("true");
				details.setCoursesTaken(valid700LevelCoursesTaken);
			} else{
				degreeBasedCreditsRequirementCheckResults.setPassed("false");
				details.setCoursesTaken(valid700LevelCoursesTaken);
				List <String> notes = new ArrayList<String>();
				notes.add("Must pass "+ remaining700LevelHours +" more hours of CSCE courses numbered above 700.");
				details.setNotes(notes);
				
			}
		} else{
			if(totalNumberOf700LevelHoursTaken >= numberOfRequiredHours/2 && totalNumberOfHoursTaken >= numberOfRequiredHours){
				degreeBasedCreditsRequirementCheckResults.setPassed("true");
				details.setCoursesTaken(coursesTakenWithThesisAndBlankGradeRemoved);
			} else{
				degreeBasedCreditsRequirementCheckResults.setPassed("false");
				details.setCoursesTaken(coursesTakenWithThesisAndBlankGradeRemoved);
				List <String> notes = new ArrayList<String>();
				notes.add("Must pass " + remainingTotalHours + " more hours of graduate courses.");
				notes.add("Must pass "+ remaining700LevelHours/2 +" more hours of CSCE courses numbered above 700.");
				details.setNotes(notes);						
			}
		}	
		degreeBasedCreditsRequirementCheckResults.setDetails(details);
		return degreeBasedCreditsRequirementCheckResults;
	}
	
	private RequirementCheck getThesisCreditsRequirementCheckResults(StudentRecord record,
			List<CourseTaken> validCoursesTaken) {
		RequirementCheck thesisCreditsRequirementCheckResults = new RequirementCheck();
		thesisCreditsRequirementCheckResults.setName("THESIS_CREDITS_" + record.getDegreeSought().getDegreeName().toUpperCase());
		
		String degreeName = record.getDegreeSought().getDegreeName();
		int numberOfRequiredThesisCreditHours = getNumberOfThesisCreditHours(degreeName);
		int totalNumberOfThesisHours = 0;
		int remainingThesisHours = 0;
		List<CourseTaken> thesisCoursesTaken = new ArrayList<CourseTaken>();
		
		for(int i = 0; i < validCoursesTaken.size(); i++){
			if(isThesisCourse(validCoursesTaken.get(i))){
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
	
	private RequirementCheck getWithinTimeRequirementCheckResults(StudentRecord record) {
		RequirementCheck withinTimeRequirementCheckResults = new RequirementCheck();
		withinTimeRequirementCheckResults.setName("TIME_LIMIT_" + record.getDegreeSought().getDegreeName().toUpperCase());
		if (isWithinTime(record)){
			withinTimeRequirementCheckResults.setPassed("true");
		} else {
			withinTimeRequirementCheckResults.setPassed("false");
			Details withinTimeDetails = new Details();
			List <String> notes = new ArrayList<String>();
			notes.add("The time limit has been exceeded");
			withinTimeDetails.setNotes(notes);
		}
		return withinTimeRequirementCheckResults;
	}

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
			case "CC":
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
	
	private List<Milestone> getRemainingMilestones(StudentRecord record){
		List<Milestone> milestonesRemaining = new ArrayList<Milestone>();
		List<Milestone> milestonesCompleted = record.getMilestonesSet();
		List<String> milestonesCompletedNames = new ArrayList<String>();
		String degreeName = record.getDegreeSought().getDegreeName();
		List<Milestone> allDegreeMilestones = getDegreeRequirements(degreeName).getMilestones();
		
		for(int i = 0; i < milestonesCompleted.size(); i++){
			milestonesCompletedNames.add(milestonesCompleted.get(i).getMilestone());
		}
		for(int i = 0; i < allDegreeMilestones.size(); i++){
			if (!milestonesCompletedNames.contains(allDegreeMilestones.get(i).getMilestone())){
				milestonesRemaining.add(allDegreeMilestones.get(i));
			}
		}
		
		return milestonesRemaining;
	}
	
	private boolean isWithinTime(StudentRecord record){
		int currentSemester;
		int yearBegan = record.getTermBegan().getYear();
		String semesterBegan = record.getTermBegan().getSemester();
		int semesterBeganCode = 0;
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String degreeSought = record.getDegreeSought().getDegreeName();


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
		
		if (degreeSought.equals("PHD")){		
			if((currentYear - yearBegan) < 8){
				return true;
			} else if ((currentYear - yearBegan) == 8){
				if(currentSemester >= semesterBeganCode){
					return true;
				} else{
					return false;
				}
			} else {
				return false;
			}
		} else {
			if((currentYear - yearBegan) < 6){
				return true;
			} else if ((currentYear - yearBegan) == 6){
				if(currentSemester >= semesterBeganCode){
					return true;
				} else{
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	private List<Course> getRemainingCourses(StudentRecord record){
		DegreeRequirements  degreeRequirements = getDegreeRequirements(record.getDegreeSought().getDegreeName());
		List<Course> coursesRemaining = new ArrayList<Course>();
		List<Course> coreCourses = degreeRequirements.getCoreCourses();
		List<CourseTaken> coursesCompleted = record.getCoursesTaken();		
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
	
	private List<CourseTaken> getCoreCourses(StudentRecord record){
		DegreeRequirements  degreeRequirements = getDegreeRequirements(record.getDegreeSought().getDegreeName());
		List<Course> coreCourses = degreeRequirements.getCoreCourses();
		List<CourseTaken> coursesCompleted = record.getCoursesTaken();		
		List<Course> coursesCompletedList = new ArrayList<Course>();		
		List<CourseTaken> coursesTaken = new ArrayList<CourseTaken>();;
		
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
	 * @return the notes
	 */
	private List<String> getNotes(StudentRecord record) {
		return record.getNotes();
	}
	
	//Returns true if valid CSCE course
	private boolean isValidCourse(CourseTaken courseTaken, List<Course> allCourses){
		for(int i = 0; i < allCourses.size(); i++){
			if (courseTaken.getCourse().courseIsEqual(courseTaken.getCourse(), allCourses.get(i))){
				return true;
			}
		}
		return false;
	}
	
	//Returns list of valid courses taken
	private List<CourseTaken> getValidCoursesTaken(List<CourseTaken> coursesTaken, List<Course> allCourses){
		List<CourseTaken> validCoursesTaken = new ArrayList<CourseTaken>(); 
		for (int i = 0; i < coursesTaken.size(); i++){
			if((isValidCourse(coursesTaken.get(i), allCourses))){
				validCoursesTaken.add(coursesTaken.get(i).getCourseTaken());
			}			
		}
		return validCoursesTaken;
	}
	
	//Returns a list of valid 700 level courses taken
	private List<CourseTaken> getValid700LevelCoursesTaken(List<CourseTaken> validCoursesTaken, List<Course> allCourses){
		List<CourseTaken> valid700LevelCoursesTaken = new ArrayList<CourseTaken>();
		for (int i = 0; i < validCoursesTaken.size(); i++){
			if((isValid700LevelCourse(validCoursesTaken.get(i)))){
				valid700LevelCoursesTaken.add(validCoursesTaken.get(i).getCourseTaken());
			}			
		}
		return valid700LevelCoursesTaken;
	}
	
	private boolean isValid700LevelCourse(CourseTaken courseTaken){
		int level = Integer.parseInt(courseTaken.getCourse().getId().substring(4, 7));
		if(level >= 700 && level != 799 && level != 899){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isThesisCourse(CourseTaken courseTaken){
		int level = Integer.parseInt(courseTaken.getCourse().getId().substring(4, 7));
		if(level == 799 || level == 899){
			return true;
		} else {
			return false;
		}
	}
	
	
	private int getNumberOfAdditionalHours(String degreeName) {
		return getDegreeRequirements(degreeName).getAdditionalCreditHours();
	}

	private int getNumberOfDegreeBasedHours(String degreeName) {
		return getDegreeRequirements(degreeName).getDegreeBasedCreditHours();
	}
	
	private int getNumberOfDegreeBasedHoursForPreviousMasterDegree(String degreeName) {
		return getDegreeRequirements(degreeName).getDegreeBasedCreditHoursWithValidMasterDegree();
	}
	
	private int getNumberOfThesisCreditHours(String degreeName){
		return getDegreeRequirements(degreeName).getThesisCreditHours();
	}
}
