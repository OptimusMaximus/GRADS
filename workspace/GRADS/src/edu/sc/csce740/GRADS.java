/**
 * 
 */
package edu.sc.csce740;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.CourseTaken;
import edu.sc.csce740.model.Degree;
import edu.sc.csce740.model.DegreeRequirements;
import edu.sc.csce740.model.DoctorOfPhilosophy;
import edu.sc.csce740.model.ProgressSummary;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;
import edu.sc.csce740.exception.CoursesInvalidException;
import edu.sc.csce740.exception.DataCanNotBeWrittenException;
import edu.sc.csce740.exception.InvalidUserException;
import edu.sc.csce740.exception.DataNotRetrievedException;
import edu.sc.csce740.exception.ProgressSummaryNotGeneratedException;

/**
 * @author brandemr
 * 
 */
/**
 * @author merchane
 *
 */
/**
 * @author merchane
 *
 */
public class GRADS implements GRADSIntf {
	private String currentUser;
	private List<User> allUsers;
	private List<StudentRecord> allRecords;
	private List<Degree> allDegrees;
	private List<Course> allCourses;
	private static String role;
		
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
		//Get file from resources folder
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
	
	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#loadUsers(java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#loadCourses(java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#loadRecords(java.lang.String)
	 */
	public void loadRecords(String recordsFile) throws DataNotRetrievedException  {
		try{
			allRecords = new Gson().fromJson(new FileReader(getFile(recordsFile)),
				new TypeToken<List<StudentRecord>>() {
				}.getType());
		}  catch (Exception e){
			throw new DataNotRetrievedException("Can not load records file");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#setUser(java.lang.String)
	 */
	public void setUser(String userId)  throws InvalidUserException {
		// TODO Auto-generated method stub
		validateSession(userId);
		this.currentUser = userId;	
		
//		if (validateUser(userId)) {
//			this.currentUser = userId;
//		} else {
//			throw new InvalidUserException("Invalid user");
//		}
		setRole(lookupUser(userId).getRole());
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#clearSession()
	 */
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub
		this.currentUser = null;
		this.allCourses = null;
		this.allDegrees = null;
		this.allRecords = null;
		this.allUsers = null;
		role = null; 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#getUser()
	 */
	public String getUser() {
		// TODO Auto-generated method stub
		return this.currentUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#getStudentIDs()
	 */

	public List<String> getCSCEStudentIDs() {
		// TODO Auto-generated method stub
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

	public List<String> getCSCEGPCIDs() {
		// TODO Auto-generated method stub
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
	public StudentRecord getTranscript(String userId) throws Exception {
		if (getCSCEGPCIDs().contains(getUser())
			|| getUser().equals(userId)){
			for (int i=0; i< allRecords.size(); i++){
				if (allRecords.get(i).getUser().getUserID().equals(userId)){
					return allRecords.get(i);
				}
			}
			//TODO 
//			throw new invalidTranscriptException ("invalid transcript lookup");
		}
		
		throw new Exception("Student record of: '" + userId + "' cannot be accessed by: " + getUser());
//		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#updateTranscript(java.lang.String,
	 * edu.sc.csce740.model.StudentRecord, java.lang.Boolean)
	 */
	public void updateTranscript(String userId, StudentRecord transcript,
			Boolean permanent) throws Exception {
		
		validateAccess(userId);
		if (permanent && transcript.getTempEdit())
		{
			throw new Exception ("Update Failed: Temporary edits found in attempted permanent update");
		}
		
		else
		{
			int index = getRecordIndex(userId);
			if (index == -1)
			{
				System.out.println("Previous record not found. Adding new record to database...");
				allRecords.add(transcript);
				System.out.println("New record successfully added");
				if (permanent){
					String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
					writeToFile("src/resources/students.txt", representation);	
					loadRecords("resources/students.txt");
				}	
			}
			else	
				allRecords.remove(index);
				allRecords.add(transcript);
				if (permanent) {
					String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
					writeToFile("src/resources/students.txt", representation);	
					loadRecords("resources/students.txt");
				}
		} 
		
//		if (validateAccess(userId)){
//			//StudentRecord originalRecord = getTranscript(userId);	
//			int index = -1;
//			//originalRecord = transcript;
//			for (int i=0; i< allRecords.size(); i++){
//				if (allRecords.get(i).getUser().getUserID().equals(userId)){
//					index = i;
//					break;
//				}
//			}
//			allRecords.remove(index);
//			allRecords.add(transcript);		
//			
//			if(permanent){
//				String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
//				writeToFile("src/resources/students.txt", representation);	
//				loadRecords("resources/students.txt");
//				
//			} 
//		} else {
//			throw new Exception("Transcript could not be updated");
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#addNote(java.lang.String, java.lang.String,
	 * java.lang.Boolean)
	 */
	public void addNote(String userId, String note, Boolean permanent)
			throws Exception {
		validateAccess(userId);
//		if (validateAccess(userId)){
//			StudentRecord transcript;
//			transcript = getTranscript(userId);
//			transcript.addNote(note);
//			
//			if (permanent){
//				updateTranscript(userId, transcript, true); 
//			} else {
//				updateTranscript(userId, transcript, false); 
//			}
//		} else {
//			throw new Exception("Note cannot be added to the record of '" + userId + "' by: " + getUser());
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#generateProgressSummary(java.lang.String)
	 */
	public ProgressSummary generateProgressSummary(String userId)
			throws ProgressSummaryNotGeneratedException {
		try{
			ProgressSummary progressSummary = new ProgressSummary();
			progressSummary.setRecord(getTranscript(userId));
			progressSummary.getResults();
			String representation = new GsonBuilder().setPrettyPrinting().create().toJson(progressSummary);
			writeToFile("src/resources/progessSummary.txt", representation);
			return progressSummary;
		} catch (Exception e){
			throw new ProgressSummaryNotGeneratedException("Can not generate progress summary");
		}		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#simulateCourses(java.lang.String,
	 * java.util.List)
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
			System.out.println(record);
			record.setCoursesTaken(courses.get(i));
		}
		updateTranscript(userId, record, false);
		return generateProgressSummary(userId);
	}

//	private boolean validateUser(String id) {
//		for (int i = 0; i < allUsers.size(); i++) {
//			if (id.equals(allUsers.get(i).getUserID())
//				&& "COMPUTER_SCIENCE".equals(allUsers.get(i).getDepartment())) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean validateAccess(String userID) throws Exception 
//	{
//		if (getCSCEGPCIDs().contains(getUser())){
//			return true;
//		} else if (getCSCEStudentIDs().contains(userID) && getUser().equals(userID)){
//			return true;
//		} else{
//			return false;
//		}
//		
//	}
	
	
	
	/**
	 * Method to retrieve the user object from <code>allUsers</code> associated with the <code>userId</code>
	 * @param userId the userId of the user to look up 
	 * @return the user object associated with the <code>userId</code>
	 */
	private User lookupUser(String userId) {
		User user = null;
		for (int i = 0; i < allUsers.size(); i++) 
		{
			if (userId.equals(allUsers.get(i).getUserID()))
			{
				user = allUsers.get(i);
				break;	
			}
		}
		return user; 
	}
	
	
	/**
	 * Method to return the position of the user object associated with <code>userId</code>
	 * @param userId the id of the user we wish to locate the position of within <code>allRecords</code>
	 * @return the index position of the user object associated with <code>userId</code> in <code>allRecords</code>
	 */
	private int getRecordIndex(String userId) 
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
		if (!(userId.equals(user.getUserID()))) 
		{
			throw new InvalidUserException ("Session Initation Failed: Not in user database");
		}
		if(!("COMPUTER_SCIENCE".equals(user.getDepartment())))
		{
			throw new InvalidUserException ("Session Initation Failed: Department mismatch");
		}
		if (!(user.getRole().equals("STUDENT")) 
		   && !(user.getRole().equals("GRADUATE_PROGRAM_COORDINATOR")))
		{
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
		   || !("COMPUTER_SCIENCE".equals(accessedUser.getDepartment()))) 
		{
			throw new Exception ("No Access: Department mismatch");
		}
		if (!(lookupUser(getUser()).getRole().equals("GRADUATE_PROGRAM_COORDINATOR")) 
		   && !(accessedUser.getRole().equals("STUDENT") && getUser().equals(userId)))
		{
			throw new Exception ("No Access: Unauthorized record access");
		} 
	}
}
