package application;

import java.util.Locale;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Takes in user input for person information
 * @author Winjoy Nyariaki Tiop
 *
 */
public class PersonController {
	
	//instance variables
	private Stage stage;
	private Person person;
	private Main main;
	//List of countries
	private ObservableList<String> cities = FXCollections.observableArrayList();


    @FXML
    private Button cancelButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField phoneField;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private ComboBox<String> countryComboBox;
    
    
    /**
     * Allows person controller class to acess methods in both Main and Person class
     * Checks if person is null, if it is creates new person object
     * if not, takes the information in the textfield and sets person information
     * Source with modifications: https://bit.ly/32GCBho & https://bit.ly/3gb5C8i
     * @param main Main is class used to run application
     * @param stage stage is the window that is used to showcase person fxml file
     * @param person Person is object of type person 
     */
	
    public void setMain(Main main, Stage stage, Person person) {
    	//Source: https://stackoverflow.com/questions/43674408/how-do-i-display-all-country-list-in-combobox-javafx
    	//Getting all the countries and adding them to the combo box
        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = { obj.getDisplayCountry() };
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        countryComboBox.setItems(cities);
    	//Getting the stage, person and connecting to main page
    	this.stage = stage;
		this.person = person;
		this.main = main;
		
		//Checking if the person is empty, if not setting the textfield to display information
    	if(person != null) {
			firstNameField.setText(person.getFirstName());
			lastNameField.setText(person.getLastName());
			phoneField.setText(person.getPhone());
			emailField.setText(person.getEmail());
			countryComboBox.setValue(person.getLocation());
		}
    	//If contact is null creating a neew instance of it
    	else {
    		addNewContact();
    	}
    	
	}
    /**
     * Controls cancel button. Once cancel button is pressed closes the window
     * @param event clicking of cancel button
     */
    
    @FXML
    void cancelNewContactButton(ActionEvent event) {
    	stage.close();
    }
    
    /**
     * Controls confirm button. 
     * Monitors user input and sends error messages when appropriate 
     * if not sets contact information in person object and stores information in observablelist
     * @param event clicking of confirm button
     */

    @FXML
    void confirmNewContactButton(ActionEvent event) {
    	//getting the text in field box
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String phoneNumber = phoneField.getText();
    	String email = emailField.getText();
    	String location = countryComboBox.getValue();
    	
    	//making sure the phonenumber and email fit format and seting person information
    	if (isNumeric(phoneNumber) && emailVerifyer(email)) {
    		person.setFirstName(firstName);
        	person.setLastName(lastName);
        	person.setPhone(phoneNumber);
        	person.setEmail(email);
        	person.setLocation(location);
        	main.getPersonData().add(person);
        	stage.close();
    		
    	}
    	//Error messages
    	else {
    		//Checking to see if all the information is filled in, send approprate error message
        	if(firstName.isBlank()|| lastName.isBlank()||phoneNumber.isBlank()||
        			email.isBlank()||location.isBlank()) {
        		emptyContactErrorMessage(0);
        	}
        	
        	//Checking to see if phoneNumber is integer, send approprate error message
        	else if(isNumeric(phoneNumber) == false) {
        		emptyContactErrorMessage(1);
        		
        	}
        	//Checking to see email fits format, send approprate error message
        	else if(emailVerifyer(email) == false) {
        		emptyContactErrorMessage(2);
        	}
        		
    		
    	}
    	
    	
    }

    /**
     * Creates a new person object
     */
    public void addNewContact() {
    	person = new Person();
    }
	
	/**
	 * displays an error message if any contact information is not entered
	 */
	
	public void emptyContactErrorMessage(int value) {
		//displaying error message based on int entered
		if(value == 0) {
			errorMessage.setText("Please Enter All The Information");
		}
		else if(value == 1) {
			errorMessage.setText("Invalid phone number");
		}
		else if(value == 2) {
			errorMessage.setText("Invalid email");
		}
	}
	
	/**
	 * verifies that phone number is type int
	 * Source: //source: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	 * @param str Str is the phone number user inputs
	 * @return Returns true or false depending on input
	 */
	public static boolean isNumeric(String str) { 
		  try {  
			  //Checking if the string can be converted to int, if not return false
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	/**
	 * Makes sure email address follows appropriate format
	 * Source: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
	 * @param email email address user inputs
	 * @return Returns true or false depending on input
	 */
	public static boolean emailVerifyer(String email) {
		//format emails have
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		//checking if email entered mets email format
		if (pattern.matches(regex, email)) {
			   return true;
			} else {
			   return false;
	}
	}
}
