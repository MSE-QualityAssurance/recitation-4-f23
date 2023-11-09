package edu.cmu.cs.analysis.model;

/**
* <b>Description:</b> The class Person in the package model.<br>
* @author Johan Giraldo.
*/

public abstract class Person {

//Attributes
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String nationality;
	private String birthdate;
		
//Constructor
		
	/**
	 * <b>Description:</b> The constructor of the class Spectator.<br>
	 * <b>Pre:</b> No one parameter can be null.<br>
	 * <b>Post:</b> All attributes of the class are initialized.<br> 
	 * @param id The person's id.
	 * @param firstName The person's first name.
	 * @param lastName The person's last name.
	 * @param email The person's email.
	 * @param gender The person's gender.
	 * @param nationality The person's nationality.
	 * @param birthdate The person's birthdate.
	 */
		
	public Person(String id, String firstName, String lastName, String email, String gender, String nationality, String birthdate) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	    this.nationality = nationality;
		this.birthdate = birthdate;
	}
		
//Getters
	
	/**
	 * <b>Description:</b> This method allows returning the attribute id.<br>
	 * @return The attribute id.
	 */
		
	public String getId() {
		return id;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute FirstName.<br>
	 * @return The attribute FirstName.
	 */

	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute LastName.<br>
	 * @return The attribute LastName.
	 */

	public String getLastName() {
		return lastName;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute email.<br>
	 * @return The attribute email.
	 */

	public String getEmail() {
		return email;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute gender.<br>
	 * @return The attribute gender.
	 */

	public String getGender() {
		return gender;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute nationality.<br>
	 * @return The attribute nationality.
	 */

	public String getNationality() {
		return nationality;
	}
	
	/**
	 * <b>Description:</b> This method allows returning the attribute birthdate.<br>
	 * @return The attribute birthdate.
	 */

	public String getBirthdate() {
		return birthdate;
	}
	
//Setters
	
	/**
	 * <b>Description:</b> This method allows setting the attribute id.<br>
	 * <b>Post:</b> The attribute id will be replaced by the one that enters by parameter.<br>
	 * @param id The new id.
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute firstName.<br>
	 * <b>Post:</b> The attribute firstName will be replaced by the one that enters by parameter.<br>
	 * @param firstName The new firstName.
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute lastName.<br>
	 * <b>Post:</b> The attribute lastName will be replaced by the one that enters by parameter.<br>
	 * @param lastName The new lastName.
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute email.<br>
	 * <b>Post:</b> The attribute email will be replaced by the one that enters by parameter.<br>
	 * @param email The new email.
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute gender.<br>
	 * <b>Post:</b> The attribute gender will be replaced by the one that enters by parameter.<br>
	 * @param gender The new gender.
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute nationality.<br>
	 * <b>Post:</b> The attribute nationality will be replaced by the one that enters by parameter.<br>
	 * @param nationality The new nationality.
	 */

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * <b>Description:</b> This method allows setting the attribute birthdate.<br>
	 * <b>Post:</b> The attribute birthdate will be replaced by the one that enters by parameter.<br>
	 * @param id The new id.
	 */

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

//Methods
	
	/**
	 * <b>Description:</b> This method converting the person's attributes in a String.<br>
	 * @return A String wit the person's attributes.
	 */
	
	@Override
	public String toString() {
		
		String toString = "";
		
		toString += "Id: " + getId() + "\n";
		toString += "First name: " + getFirstName() + "\n";
		toString += "Last name: " + getLastName() + "\n";
		toString += "Email: " + getEmail() + "\n";
		toString += "Gender: " + getGender() + "\n";
		toString += "Country: " + getNationality() + "\n";
		toString += "Birthdate: " + getBirthdate() + "\n";
		
		return toString;
	}
	
}
