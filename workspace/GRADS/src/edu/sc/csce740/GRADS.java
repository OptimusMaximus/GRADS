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
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.CourseTaken;
import edu.sc.csce740.model.Degree;
import edu.sc.csce740.model.DegreeRequirements;
import edu.sc.csce740.model.DoctorOfPhilosophy;
import edu.sc.csce740.model.ProgressSummary;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;

/**
 * @author brandemr
 * 
 */
public class GRADS implements GRADSIntf {
	private String currentUser;
	private List<User> allUsers;
	private List<StudentRecord> allRecords;
	private List<Degree> allDegrees;
	private List<Course> allCourses;
	private static String role; 
		
	private File getFile(String fileName) {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	 }
	
	public void writeToFile(String fileName, String text) throws IOException{
		File file = new File(fileName);
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(text);
		fileWriter.flush();
		fileWriter.close();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#loadUsers(java.lang.String)
	 */
	public void loadUsers(String usersFile) throws Exception {
		// TODO Auto-generated method stub
		
		allUsers = new Gson().fromJson(new FileReader(getFile(usersFile)),
				new TypeToken<List<User>>() {
				}.getType());
		// String representation = new
		// GsonBuilder().setPrettyPrinting().create().toJson(users);
		//System.out.println(allUsers.get(0).toString());
		// Gson gson = new Gson();
		// String json = gson.toJson(users.get(0));
		// System.out.println(json);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#loadCourses(java.lang.String)
	 */
	public void loadCourses(String coursesFile) throws Exception {
		// TODO Auto-generated method stub
		allCourses = new Gson().fromJson(new FileReader(getFile(coursesFile)),
				new TypeToken<List<Course>>() {
				}.getType());
		// System.out.println(courses.size());
		// Gson gson = new Gson();
		// String json = gson.toJson(courses.get(0));
		// System.out.println(json);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#loadRecords(java.lang.String)
	 */
	public void loadRecords(String recordsFile) throws Exception {
		// TODO Auto-generated method stub
		allRecords = new Gson().fromJson(new FileReader(getFile(recordsFile)),
				new TypeToken<List<StudentRecord>>() {
				}.getType());
		// System.out.println(studentRecords.size());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#setUser(java.lang.String)
	 */
	public void setUser(String userId) throws Exception 
	{
		// TODO Auto-generated method stub
		validateSession(userId);
		this.currentUser = userId;
		role = lookupUser(userId).getRole();
		
	}

	public static String getRole()
	{
		return role; 
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

	public List<String> getStudentIDs() throws Exception {
		// TODO Auto-generated method stub
		if (!(lookupUser(getUser()).getRole().equals("GRADUATE_PROGRAM_COORDINATOR")))
		{
			throw new Exception ("No Access: Not a Graduate Program Coordinator");
		}
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

	public List<String> getGPCIDs() throws Exception {
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
	public StudentRecord getTranscript(String userId) throws Exception 
	{
		
		validateAccess(userId);
		for (int i=0; i< allRecords.size(); i++)
		{
			if (allRecords.get(i).getUser().getUserID().equals(userId))
			{
				return allRecords.get(i);
			}
		}			
		return null;
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
				if (permanent){/*write out in Json, overwriting recordFile*/}	
			}
			else	
				allRecords.remove(index);
				allRecords.add(transcript);
				if (permanent) {/*write out Json file, overwriting recordFile*/ }
		} 
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
		StudentRecord record;
		StudentRecord tempRecord;
		
		record = getTranscript(userId);
		tempRecord = record;
		
		if(permanent){
			record.addNote(note);
		} else {
			//TODO: still need to work on this
			tempRecord.addNote(note);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#generateProgressSummary(java.lang.String)
	 */
	public ProgressSummary generateProgressSummary(String userId)
			throws Exception {
		validateAccess(userId);
		ProgressSummary progessSummary = new ProgressSummary();
		progessSummary.setRecord(this.getTranscript(userId));
		progessSummary.getResults();
		return progessSummary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sc.csce740.GRADSIntf#simulateCourses(java.lang.String,
	 * java.util.List)
	 */
	public ProgressSummary simulateCourses(String userId, List<CourseTaken> courses) throws Exception 
	{
		StudentRecord transcript;
		transcript = getTranscript(userId);
		for (int i = 0; i<courses.size(); i++)
		{
			transcript.setCoursesTaken(courses.get(i));
		}	
		this.updateTranscript(userId, transcript,false);
		return generateProgressSummary(userId);
	}

	private void validateSession(String userId) throws Exception
	{
		User user = lookupUser(userId);
		if (!(userId.equals(user.getUserID()))) 
		{
			throw new Exception ("Session Initation Failed: Not in user database");
		}
		if(!("COMPUTER_SCIENCE".equals(user.getDepartment())))
		{
			throw new Exception ("Session Initation Failed: Department mismatch");
		}
		if (!(user.getRole().equals("STUDENT")) 
		   && !(user.getRole().equals("GRADUATE_PROGRAM_COORDINATOR")))
		{
			throw new Exception ("Session Initiation Failed: Not qualified to access GRADS");
		} 
	}
	private void validateAccess(String userId) throws Exception
	{
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
	
	private User lookupUser(String userId)
	{
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
	
	private int getRecordIndex(String userId) throws Exception
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
}
