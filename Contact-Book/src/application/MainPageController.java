package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

/**
 * Controls the main page of the contact book
 * @author Winjoy Nyariaki Tiop
 *
 */
public class MainPageController implements Initializable {

	//Instance Variable
	private Main main = new Main();
	
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label emailLabel;
	
    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private Button editButton;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    @FXML
    private Label locationLabel;
    
    @FXML
    private Region MapRegion;
    
    @FXML
    private PieChart myPieChart;
    
    /**
	 * Initalizes controller:
	 * Sets the column of the tableview.
	 * Sets content of table view to object in observableList
	 * Watches which person in tableview is selected and gets index
	 * 
	 */	
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//Source: https://www.youtube.com/watch?v=mtdlX2NMy4M
    	firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		//Seting information in observaable list in table view
		tableView.setItems(main.getPersonData());
		
		//Monitoring when a person in the table is selected
		tableView.getSelectionModel().selectedIndexProperty().addListener( 
			     new ChangeListener<Number>() { 
			         @Override 
			          public void changed(ObservableValue observable, Number oldValue, Number newValue) {
			        	  int index = newValue.intValue(); 
			        	  if (index >= 0) {
			        		  //displaying that person
			        		displayinformation(index);
			        	  }
			               }
			     } 
			);
			
		
    }
    
    /**
     * Controls the add button. 
     * Opens a new window for user to input person information
     * @param event clicking of the add button
     */
    

    @FXML
    void addNewContactButton(ActionEvent event) {
    	//sending a null person to set information
    	main.newPersonWindow(null); 
    	//updating piechart
    	pieChartData(main.getPersonData().size());
    }
    
    /**
     * Controls the edit button. 
     * Pin points the person that was selected.
     * Opens a new window with the person's information,allowing users to modify information
     * @param event clicking of the edit button
     */

    @FXML
    void editExistingContactButton(ActionEvent event) {
    	//Getting the initial size of the data stored in observablelist
    	int intialSize = main.getPersonData().size(); 
    	
    	//Making sure there's a contact stored in observablelist
    	if(main.getPersonData().size()>0) {
    		//Getting the position of the person that was clicked
    		int index = tableView.getSelectionModel().getSelectedIndex();
    		//Getting the person at that position
    		Person person = (Person) tableView.getSelectionModel().getSelectedItem();
    		//Sending information to person window to be altered
    		main.newPersonWindow(person);
    		tableView.setItems(main.getPersonData());
    		//Getting the final size of the data stored in observablelist
    		int CurrentSize = main.getPersonData().size();
    		//Verifying that a modification has been made and removing old contact
    		if(CurrentSize > intialSize) {
    			main.getPersonData().remove(index);
    			tableView.setItems(main.getPersonData());
    		}
    		//updating piechart
    		pieChartData(main.getPersonData().size());
    	}
    	
    }
    /**
     * Controls the delete button. 
     * Pin points the contact that was selected. 
     * takes index and deletes object from obervablelist which is attached to tableview
     * @param event clicking of the delete button
     */

    @FXML
    void deleteContactButton(ActionEvent event) {
    	//Getting the position of the person that was clicked
    	int index = tableView.getSelectionModel().getSelectedIndex();
    	//Removing Person information from list
    	main.getPersonData().remove(index);
    	//Updating piechart
    	pieChartData(main.getPersonData().size());
    }
    
    /**
     * Takes the index from initializer, and in pins person in observable- which stores person information.
     * Retrieves the person's information and displays the information in the labels
     * @param index position on the table view that was selected
     */
    
    public void displayinformation(int index) {
    	//Getting the person at the position that was clicked
    	Person personClicked =main.getPersonData().get(index);
    	//displaying information in labels
    	firstNameLabel.setText(personClicked.getFirstName());
    	lastNameLabel.setText(personClicked.getLastName());
    	phoneLabel.setText(personClicked.getPhone());
    	emailLabel.setText(personClicked.getEmail());
    	locationLabel.setText(personClicked.getLocation());
    	
    }
    /**
     * Generates pieChart data based on user input
     * Gets the countries of the contacts and generates precentage for each country
     * @param numberofPeople number of people that have been entered 
     */
    
    public void pieChartData(int numberofPeople) {
    	//Getting an array of countries 
    	String[] arrayOfContactCountries = listofCountries(numberofPeople);
    	//Getting an arraylist of unique countries
    	ArrayList<String> uniqueCountries = getUnqiueLocations(arrayOfContactCountries);
    	//Creating size of data needed to be stores based of unique number of countries
    	int sizeOfPieChart = uniqueCountries.size();
    	PieChart.Data[] myPieChartData = new PieChart.Data[sizeOfPieChart];
    	//looping through piechart and setting data 
    	for(int i = 0; i < sizeOfPieChart; i++ ) {
    		//Getting country of interest in unique country list 
    		myPieChartData[i] = new PieChart.Data(uniqueCountries.get(i),
    				//Getting percent appear of the country in the list of countries
    				getStatsForCountry(numberofPeople,uniqueCountries.get(i),
    						arrayOfContactCountries));
    	}
    	//Adding information,labels, legend to piechart
    	myPieChart.setData(FXCollections.observableArrayList(myPieChartData));
    	myPieChart.setTitle("Where are my contacts from?");
    	myPieChart.setLabelsVisible(true);
    	myPieChart.setLegendVisible(true);
    	
    }
    	
    /**
     * Creates a new Arraylist of unique countries. 
     * Removes duplicates from orignal entries
     * @param locations list of all the countries from each contact
     * @return a list of countries with no duplication 
     */
    public ArrayList<String> getUnqiueLocations(String[] locations) {
    	//Creating a arraylist of type string
    	ArrayList<String> unqiuelist = new ArrayList<String>(); 
    	//looping through array of all the locations stored
    	for(int i = 0; i < locations.length; i++) {
    		//taking only the unique countries, avoiding duplication
    		if(unqiuelist.contains(locations[i])) {}
    		else {unqiuelist.add(locations[i]);}
    	}
    	return unqiuelist;
    }
    
    /**
     * Gathers all the user inputed locations
     * @param numberofPeople number of contact information that has been entered
     * @return Returns a string array of all contact's location
     */
    
    //Creates a list of countries in the people added with duplicates
    public String[] listofCountries(int numberofPeople) {
    	//Creating a list of all the locations stored
    	String[] locationArray = new String[numberofPeople];
   		for(int i = 0; i < numberofPeople; i++) {
   			Person personOfInterest = main.getPersonData().get(i);
   			locationArray[i] = personOfInterest.getLocation();
   			}
   		return locationArray;
    }
    
    /**
     * Determines the number of time each individual country appears
     * @param numberofPeople number of contact information that has been entered
     * @param location the country of interest
     * @param locations list of all the countries from each contact
     * @return precentage of times a country appears
     */
    public float getStatsForCountry(int numberofPeople, String location,String[] locations) {
    	//Counting the appearance of a location of interest
    	int count = 0; 
    	for(int i = 0; i < locations.length; i++) {
    		if(locations[i].equals(location)) {
    			count++;
    		}
    	}
    	//Finding the precentage of times it appears
    	return (count*100/numberofPeople);
}
}