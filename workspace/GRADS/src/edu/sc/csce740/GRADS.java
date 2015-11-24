package edu.sc.csce740;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.CourseTaken;
import edu.sc.csce740.model.ProgressSummary;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;
import edu.sc.csce740.exception.CoursesInvalidException;
import edu.sc.csce740.exception.DataCanNotBeWrittenException;
import edu.sc.csce740.exception.InvalidUserException;
import edu.sc.csce740.exception.DataNotRetrievedException;
import edu.sc.csce740.exception.ProgressSummaryNotGeneratedException;

/**
 * Class that implement a graduate tracking system "GRADS" 
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 * 
 */

public class GRADS implements GRADSIntf {
	/**
	 * The current session user for the GRADS system
	 */
	private String currentUser;
	/**
	 * In memory data structure storing all the users in the database
	 */
	private List<User> allUsers;
	
	/**
	 *In memory data structure storing all StudentRecord objects of students in CSCE graduate program 
	 */
	private List<StudentRecord> allRecords;
	
	/**
	 * In memory data structure storing all CSCE graduate courses that 
	 * students may take at the University of South Carolina
	 */
	private List<Course> allCourses;
	
	/**
	 * The role of the current session user
	 */
	private static String role;
	
	/**
	 * The file location for the record
	 */
	private String recordFile;
		
	/**
	 * @return the recordFile
	 */
	public String getRecordFile() {
		return recordFile;
	}

	/**
	 * @param recordFile the recordFile to set
	 */
	public void setRecordFile(String recordFile) {
		this.recordFile = recordFile;
	}

	/**
	 * Static method that can check the <code>role</code> of the current user. Used in <code>StudentRecord</code> and 
	 * <code>User</code>to set the temporary edit flag for security
	 * @return String role of the current session user
	 */
	public static String getRole() {
		return role;
	}

	/**
	 * Sets the user <code>role</code> for the current session
	 * @param role the role to set
	 */
	private static void setRole(String role) {
		GRADS.role = role;
	}
	
	
	/**
	 * Convenience method for importing flatfile from the hard disk into memory 
	 * @param fileName the <code>String</code> name of the file to be retrieved
	 * @return File being loaded
	 */
	private File getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	 }
	
	/**
	 * Convenience method for exporting data to Json text file
	 * @param fileName the name of the file to be written to
	 * @param text the text data being written in Json format
	 * @throws DataCanNotBeWrittenException data write failure
	 */
	public void writeToFile(String fileName, String text) throws DataCanNotBeWrittenException{
		try{
			File file = new File(fileName);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(text);
			fileWriter.flush();
			fileWriter.close();
		}  catch (Exception e){
			throw new DataCanNotBeWrittenException("Can not write data to file");
		}
	}
	
	/**
     * Loads the list of system usernames and permissions.
     * @param usersFile the filename of the users file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
	public void loadUsers(String usersFile) throws DataNotRetrievedException {
		try{
			allUsers = new Gson().fromJson(new FileReader(getFile(usersFile)),
				new TypeToken<List<User>>() {
				}.getType());
		} catch (Exception e){
			throw new DataNotRetrievedException("Can not load user file");
		}
	}


    /**
     * Loads the list of valid courses.
     * @param coursesFile the filename of the users file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
	public void loadCourses(String coursesFile) throws DataNotRetrievedException  {
		try{
			allCourses = new Gson().fromJson(new FileReader(getFile(coursesFile)),
				new TypeToken<List<Course>>() {
				}.getType());
		} catch (Exception e){
			throw new DataNotRetrievedException("Can not load courses file");
		}
	}

	/**
     * Loads the list of system transcripts.
     * @param recordsFile the filename of the transcripts file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
	public void loadRecords(String recordsFile) throws DataNotRetrievedException  {
		try{
			allRecords = new Gson().fromJson(new FileReader(getFile(recordsFile)),
				new TypeToken<List<StudentRecord>>() {
				}.getType());
			setRecordFile(recordsFile);
		}  catch (Exception e){
			throw new DataNotRetrievedException("Can not load records file");
		}
	}
	

    /**
     * Sets the user id of the user currently using the system.
     * @param userId  the id of the user to log in.
     * @throws Exception  if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
	public void setUser(String userId)  throws InvalidUserException {
		validateSession(userId);
		this.currentUser = userId;	
		setRole(lookupUser(userId).getRole());		
	}

	/**
     * Closes the current session, logs the user out, and clears and session data.
     * @throws Exception  if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
	public void clearSession() throws Exception {
		this.currentUser = null;
		this.allCourses = null;
		this.allRecords = null;
		this.allUsers = null;
		role = null; 
	}

	/**
     * Gets the user id of the user currently using the system.
     * @return  the user id of the user currently using the system.
     */
	public String getUser() {
		return this.currentUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#getStudentIDs()
	 */
	/**
     * Gets a list of the userIds of the students that a GPC can view.
     * @return a list containing the userId of for each student in the
     *      system belonging to the current user 
     * @throws Exception is the current user is not a GPC.
     */
	public List<String> getStudentIDs() throws InvalidUserException {
		List<String> studentIDs = new ArrayList<String>();
		for (int i = 0; i < allUsers.size(); i++) {
			if ("STUDENT".equals(allUsers.get(i).getRole())
					&& "COMPUTER_SCIENCE".equals(allUsers.get(i).getDepartment())){
				studentIDs.add(allUsers.get(i).getUserID());
			} 
		}
		return studentIDs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#getStudentIDs()
	 */
	public List<String> getCSCEGPCIDs() throws InvalidUserException {
		List<String> studentIDs = new ArrayList<String>();
		for (int i = 0; i < allUsers.size(); i++) {
			if ("GRADUATE_PROGRAM_COORDINATOR".equals(allUsers.get(i).getRole())
					&& "COMPUTER_SCIENCE".equals(allUsers.get(i).getDepartment())){
				studentIDs.add(allUsers.get(i).getUserID());
			}
		}
		return studentIDs;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#getTranscript(java.lang.String)
	 */
	
	/**
     * Gets the raw student record data for a given userId.
     * @param userId  the identifier of the student.
     * @return  the student record data.
     * @throws Exception  if the form data could not be retrieved.  SEE NOTE IN 
     *      CLASS HEADER.
     */
	public StudentRecord getTranscript(String userId) throws InvalidUserException {
		if (getCSCEGPCIDs().contains(getUser())
			|| getUser().equals(userId)){
	
			for (int i=0; i< allRecords.size(); i++){
				if (allRecords.get(i).getUser().getUserID().equals(userId)){
					
					return allRecords.get(i);
				}
			}
		}		
		throw new InvalidUserException("Student record of: '" + userId + "' cannot be accessed by: " + getUser());
	}

	/**
     * Saves a new set of student data to the records data.  
     * @param userId the student ID to overwrite.
     * @param transcript  the new student record
     * @param permanent  a status flag indicating whether (if false) to make a 
     * temporary edit to the in-memory structure or (if true) a permanent edit.
     * @throws Exception  if the transcript data could not be saved or failed
     * a validity check.  SEE NOTE IN CLASS HEADER.
     */
	public void updateTranscript(String userId, StudentRecord transcript,
			Boolean permanent) throws Exception {
		
		validateAccess(userId);
		if (permanent && transcript.getTempEdit()){
			throw new Exception ("Update Failed: Temporary edits found in attempted permanent update");
		} else {
			int index = getRecordIndex(userId);
			if (index == -1) {
				System.out.println("Previous record not found. Adding new record to database...");
				allRecords.add(transcript);
				System.out.println("New record successfully added");
				if (permanent){
					String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
					writeToFile("src/" + getRecordFile(), representation);	
					loadRecords(getRecordFile());
				}	
			} else	{
				allRecords.remove(index);
				allRecords.add(transcript);
				if (permanent) {
					String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
					writeToFile("src/" + getRecordFile(), representation);	
					loadRecords(getRecordFile());
				}
			}
		} 
	}

	/**
     * Appends a note to a student record.  
     * @param userId the student ID to add a note to.
     * @param note  the note to append
     * @param permanent  a status flag indicating whether (if false) to make a 
     * temporary edit to the in-memory structure or (if true) a permanent edit.
     * @throws Exception  if the note could not be saved or a non-GPC tries to call. 
     * SEE NOTE IN CLASS HEADER.
     */
	public void addNote(String userId, String note, Boolean permanent)
			throws Exception {
		validateAccess(userId);

		StudentRecord transcript;
		transcript = getTranscript(userId);		
		transcript.addNote(note);
		
		if (permanent){
			updateTranscript(userId, transcript, true); 
		} else {
			updateTranscript(userId, transcript, false); 
		}
	}

	/**
     * Generates progress summary
     * @param userId the student to generate the record for.
     * @returns the student's progress summary in a data class matching the I/O file.
     * @throws Exception  if the progress summary could not be generated.  
     * SEE NOTE IN CLASS HEADER.
     */
	public ProgressSummary generateProgressSummary(String userId)
			throws ProgressSummaryNotGeneratedException {
		try{
			StudentRecord record = getTranscript(userId);
			
			ProgressSummary progressSummary = new ProgressSummary();
			//progressSummary.setRecord(record);
			progressSummary.setStudent(record.getUser());
			progressSummary.setDepartment(record.getDept());
			progressSummary.setTermBegan(record.getTermBegan());
			progressSummary.setDegreeSought(record.getDegreeSought());
			progressSummary.setAdvisors(record.getAdvisors());
			progressSummary.setCommittee(record.getCommittee());
			progressSummary.getResults(record, allCourses);
			
			String representation = new GsonBuilder().setExclusionStrategies(new ExclStrat()).setPrettyPrinting().create().toJson(progressSummary);
			writeToFile("src/resources/progressSummary.txt", representation);
			
			return progressSummary;
		} catch (Exception e){
			throw new ProgressSummaryNotGeneratedException("Can not generate progress summary");
		}		
		
	}

	/**
     * Generates a new progress summary, assuming that the student passes the
     * provided set of prospective courses.
     * @param userId the student to generate the record for.
     * @param courses a list of the prospective courses.
     * @returns the student's hypothetical progress summary
     * @throws Exception  if the progress summary could not be generated or the courses  
     * are invalid. SEE NOTE IN CLASS HEADER.
     */
	public ProgressSummary simulateCourses(String userId,
			List<CourseTaken> courses) throws CoursesInvalidException, Exception {
		Course course = null;
		boolean flag = false; 
		StudentRecord record = getTranscript(userId);
		
		//Check to see if the courses passed in are valid courses
		for(int i = 0; i < courses.size(); i++){
			for(int j = 0; j < allCourses.size(); j++){
				course = allCourses.get(j);
				if (course.courseIsEqual(course.getCourse(), courses.get(i).getCourse())){
					flag = true;
					break;
				}
			}
			if (!flag){
				throw new CoursesInvalidException("Invalid course given " + courses.get(i).getCourse().getId());
			}
			//System.out.println(record);
			record.setCoursesTaken(courses.get(i));
		}
		updateTranscript(userId, record, false);
		return generateProgressSummary(userId);
	}
	
	/**
	 * Method to retrieve the user object from <code>allUsers</code> associated with the <code>userId</code>
	 * @param userId the userId of the user to look up 
	 * @return the user object associated with the <code>userId</code>
	 * @throws InvalidUserException 
	 */
	private User lookupUser(String userId) throws InvalidUserException {
		User user = null;
		for (int i = 0; i < allUsers.size(); i++) 
		{
			if (userId.equals(allUsers.get(i).getUserID()))
			{
				user = allUsers.get(i);
				break;	
			}
		}
		if (user == null){
			throw new InvalidUserException("User not in database");
		}
		return user; 
	}
	
	
	/**
	 * Method to return the position of the user object associated with <code>userId</code>
	 * @param userId the id of the user we wish to locate the position of within <code>allRecords</code>
	 * @return the index position of the user object associated with <code>userId</code> in <code>allRecords</code>
	 * @throws InvalidUserException 
	 */
	private int getRecordIndex(String userId) throws InvalidUserException 
	{
		int index = -1;
		for (int i=0; i< allRecords.size(); i++)
		{
			if (allRecords.get(i).getUser().getUserID().equals(userId))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * Method that validates the creation of a new GRADS session
	 * @param userId the id of the user trying to create a new session GRADS
	 * @throws InvalidUserException 
	 */
	private void validateSession(String userId) throws InvalidUserException {
		User user = lookupUser(userId);
		if (!(userId.equals(user.getUserID()))) {
			throw new InvalidUserException ("Session Initation Failed: Not in user database");
		}
		if(!("COMPUTER_SCIENCE".equals(user.getDepartment()))){
			throw new InvalidUserException ("Session Initation Failed: Department mismatch");
		}
		if (!(user.getRole().equals("STUDENT")) 
		   && !(user.getRole().equals("GRADUATE_PROGRAM_COORDINATOR"))){
			throw new InvalidUserException ("Session Initiation Failed: Not qualified to access GRADS");
		} 
	}
	
	/**
	 * Method that validates user access to front-facing methods in GRADS
	 * @param userId the id of the user whose record the current session user is attempting to access
	 * @throws Exception 
	 */
	private void validateAccess(String userId) throws Exception {
		User accessedUser = lookupUser(userId);
		if (!(userId.equals(accessedUser.getUserID())) 
		   || !("COMPUTER_SCIENCE".equals(accessedUser.getDepartment()))){
			throw new Exception ("No Access: Department mismatch");
		}
		if (!(lookupUser(getUser()).getRole().equals("GRADUATE_PROGRAM_COORDINATOR")) 
		   && !(accessedUser.getRole().equals("STUDENT") && getUser().equals(userId))){
			throw new Exception ("No Access: Unauthorized record access");
		} 
	}
	
	/**
	 * Class that excludes fields when printing out to GSON
	 */
	public class ExclStrat implements ExclusionStrategy {

        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        public boolean shouldSkipField(FieldAttributes f) {

            return (f.getDeclaringClass() == StudentRecord.class && f.getName().equals("tempEdit")) ||
            		(f.getDeclaringClass() == User.class && f.getName().equals("tempEditFlag"));
        }

    }
	
}
