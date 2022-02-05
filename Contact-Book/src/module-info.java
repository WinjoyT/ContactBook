module FinalProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.management;
	
	//https://stackoverflow.com/questions/68763869/module-javafx-base-cannot-access-class-application-customer-in-module-fcr-beca
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
