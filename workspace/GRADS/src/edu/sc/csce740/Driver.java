/**
 * 
 */
package edu.sc.csce740;

import com.google.gson.GsonBuilder;

/**
 * @author brandemr
 * test
 */
public class Driver {

	/**
	 * @param args
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GRADS grads = null;
		grads = new GRADS();
		
		try {
			grads.loadUsers("resources/users.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadRecords("resources/students.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadCourses("resources/courses.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grads.setUser("mhunt");
		//System.out.println(grads.getTranscript("mhunt").getCoursesTaken().get(0).getCourse().getName());
		//System.out.println(grads.getStudentIDs());
		//System.out.println(grads.getGPCIDs());
		//System.out.println(grads.getTranscript("mhunt").getNotes().get(1));
		
		//Testing if degree reqs are being loaded
		grads.addNote("mhunt", "I'm a note!!!", true);
		grads.generateProgressSummary("mhunt");
		grads.getTranscript("mhunt").setLastName("xxxxxxx");
		grads.updateTranscript("mhunt", grads.getTranscript("mhunt"), true);
		grads.validateAccess("ggay");	
		//For testing...don't delete!
//		String representation = new GsonBuilder().setPrettyPrinting().create().toJson(allRecords);
//		writeToFile("records.txt", representation );
		
	}

}
