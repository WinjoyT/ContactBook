package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main lauches interface, allowing users to view application
 * @author Winjoy Nyariaki Tiop
 *
 */
public class Main  extends Application  {
	//Instance variables
	private Stage primaryStage; 
	//List here contact information is stored
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
	
	/**
	 * Creates an constructure for the Main class 
	 */
	public Main() { }
	
	
	/**
	 * Sets Main page of the application
	 * Source: https://bit.ly/3GcQfXe
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}
	
	/**
	 * Loads MainPage FXML file, lauching the main window where user information can be viewed
	 */
	public void mainWindow() {
		try {
			
			AnchorPane root = new AnchorPane();
			
			FXMLLoader loader = new FXMLLoader();
			
			root = (AnchorPane)loader.load(
					new FileInputStream("src/application/MainPage.fxml"));
			System.out.println("Loading main Window");
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads Person FXML file, lauching the person window where user information can inputed
	 */
	
	public void newPersonWindow(Person person) {
		try {
			
			AnchorPane root = new AnchorPane();
			
			FXMLLoader loader = new FXMLLoader();
			
			root = (AnchorPane)loader.load(
					new FileInputStream("src/application/Person.fxml"));
			System.out.println("Loading New Person Window");
			
			Scene scene = new Scene(root);
			
			Stage stage = new Stage();
			
			//Source: https://bit.ly/3GcQfXe 
			PersonController controller = loader.getController();
			controller.setMain(this, stage, person);
			stage.setScene(scene);
			stage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * Gets observableList that has been stored 
	 * @return Returns observableList containing person data
	 */
	public ObservableList<Person> getPersonData() { return this.personData;}
	
	
	/**
	 * Allows user to run program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
