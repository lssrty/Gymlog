package fxSali;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Salipäiväkirja, mihin voi tallentaa harjoituksen aikana tehdyt toistot, sarjat ja painot per liike. 
 * Oletuksena ohjelmassa on valmiina kyykky, penkki ja maastaveto, mutta muita liikkeitä voi lisätä. 
 * Suorituksiin on mahdollisuus lisätä vapaamuotoinen kommentti, sekä koettu rasitus asteikolla 1-10.
 * @author lasse
 * @version 24 Jan 2021
 *
 */
public class SaliMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SaliGUIView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("sali.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
