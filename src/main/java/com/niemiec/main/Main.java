package com.niemiec.main;

//import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
		VBox vbox = loader.load();
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
