package fxSali;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sali.Sali;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Pääohjelma Sali-ohjelman käynnistämiseksi.
 * @author lasse
 * @version 24 Jan 2021
 *
 */
public class SaliMain extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("SaliGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final SaliGUIController saliCtrl = (SaliGUIController)ldr.getController();

            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("sali.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sali");
            
            Sali sali = new Sali();
            saliCtrl.setSali(sali);
            // Platform.setImplicitExit(false); // tätä ei kai saa laittaa

            primaryStage.setOnCloseRequest((event) -> {
                    if ( !saliCtrl.voikoSulkea() ) event.consume();
                });
            
            primaryStage.show();
            if ( !saliCtrl.avaa() ) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Käynnistetään käyttöliittymä
	 * @param args komentorivin parametrit
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
