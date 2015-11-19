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
	public void setUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		if (validateUser(userId)) {
			this.currentUser = userId;
		} else {
			// exception
		}
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
	public StudentRecord getTranscript(String userId) throws Exception {
		if (getGPCIDs().contains(getUser())
			|| getUser().equals(userId)){
			for (int i=0; i< allRecords.size(); i++){
				if (allRecords.get(i).getUser().getUserID().equals(userId)){
					return allRecords.get(i);
				}
			}
			//TODO 
//			throw new invalidTranscriptException ("invalid transcript lookup");
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
		
		
		StudentRecord record = getTranscript(userId);	
		if(permanent){
			int index = -1;
			record = transcript;
			for (int i=0; i< allRecords.size(); i++){
				if (allRecords.get(i).getUser().getUserID().equals(userId)){
					index = i;
				}
			}
			allRecords.remove(index);
			allRecords.add(transcript);
			
		} else {
			//TODO: still need to work on this
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
	public ProgressSummary simulateCourses(String userId,
			List<CourseTaken> courses) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private int getRecordIndex(String userID) {

		return -1;
	}

	private boolean validateUser(String id) {
		for (int i = 0; i < allUsers.size(); i++) {
			if (id.equals(allUsers.get(i).getUserID())
				&& "COMPUTER_SCIENCE".equals(allUsers.get(i).getDepartment())) {
				return true;
			}
		}
		return false;
	}
	
	public void validateAccess(String userID) throws Exception 
	{
		if (getGPCIDs().contains(getUser())){
			//;
		} else if (getStudentIDs().contains(userID) && getUser().equals(userID)){
			//return true;
		} else{
			throw new Exception("Illegal record access!");
		}
		
	}
}
