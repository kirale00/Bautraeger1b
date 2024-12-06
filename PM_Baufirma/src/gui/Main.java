package gui;

import controller.DatabaseHelper;
import gui.kunde.KundeControl;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new KundeControl(primaryStage);
	}	
	
	public static void main(String[] args) throws SQLException {
		launch(args);
	}
}