package application;

/**
 * Person represents a single contact
 * Class orginally from internship application
 * Created Jan 26th 2022
 * modifications: Location method
 * @author Winjoy Nyariaki Tiop
 *
 */

public class Person {
	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String location;
	
	/**
	 * Constructs a person object with the person's first name, last name, phone number and email address
	 * @param firstName First name of the perosn
	 * @param lastName Last name of the person
	 * @param phone phone number for the person 
	 * @param email email address for the person
	 */
	
	public Person(String firstName, String lastName, String phone, String email,String location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * constructor for person with no arguements
	 */
	public Person() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Gets the first name of the person
	 * @return Returns The first name of the perosn
	 */
	
	//Getter for First Name
	public String getFirstName() {return this.firstName;}
	
	/**
	 * Sets the first name of the person
	 * @param firstName The first name of the perosn
	 */
	//Setter for First Name
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
	/**
	 * Gets the last name of the person
	 * @return Returns The last name of the perosn
	 */
	
	//Getter for Last Name
	public String getLastName() {return this.lastName;}
	
	/**
	 *  Sets the last name of the person
	 * @param lastName The last name of the perosn
	 */
	//Setter for Last Name 
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	/**
	 * Gets the phone number for the person
	 * @return Returns The phone number of the perosn
	 */
	
	//Getter for Phone Number
	public String getPhone() {return this.phone;}
	
	/**
	 * Sets the phone number for the person
	 * @param phone The phone number for the perosn
	 */
	
	//Setter for Phone Number
	public void setPhone(String phone) {this.phone = phone;}
	
	/**
	 * Gets the email address for the person
	 * @return Returns The email address of the perosn
	 */
	
	//Getter for Email Address
	public String getEmail() {return this.email;}
	
	/**
	 * Sets the email address for the person
	 * @param email The email address for the perosn
	 */
	//Setter for Email Address
	public void setEmail(String email) {this.email = email;}
	
	/**
	 * Gets the location for the person
	 * @return Returns The location for the perosn
	 */
	
	//Getter for Location
	public String getLocation() {return this.location;}
	
	/**
	 * Sets the location address for the person
	 * @param location The location for the perosn
	 */
	//Setter for Location
	public void setLocation(String location) {this.location = location;}


	
}
