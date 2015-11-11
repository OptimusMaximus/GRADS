/**
 * 
 */
package edu.sc.csce740.model;

/**
 * @author brandemr
 *
 */
public class User {
	private String userID;
	private String firstName;
	private String lastName;
	private String role;
	private String department;
	
	public String getUserID(){
		return userID;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
