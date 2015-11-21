/**
 * 
 */
package edu.sc.csce740.model;

import edu.sc.csce740.GRADS;

/**
 * Class defining a user object for the GRADS system. 
 * @author Maximus Brandel
 * @author Ibrahim Elsayed
 * @author Christian Merchant
 * @version 1.00 2015-11-21
 *
 */
public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String role;
	private String department;
	private boolean tempEditFlag = false; 
	
	public String getUserID(){
		return id;
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

	public void setUserID(String userID) {
		this.id = userID;
	}

	public void setFirstName(String firstName) {
		if (GRADS.getRole().equals("STUDENT"))
		{
			setUserTempEdit(true);
		}
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		if (GRADS.getRole().equals("STUDENT"))
		{
			setUserTempEdit(true);
		}
		this.lastName = lastName;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public void setDepartment(String department) {
		this.department = department;
	}	
	
	public String toString(){
		return id + " " + firstName + " "  + lastName + " " + role + " " + department;
		
	}
	public boolean getTempEditStatus()
	{
		return tempEditFlag; 
	}
	private void setUserTempEdit(boolean flag)
	{
		tempEditFlag = flag; 
	}
	
	

}
