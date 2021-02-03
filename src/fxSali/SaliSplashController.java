package fxSali;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kysytään harjoittelijan nimi ja luodaan tätä varten dialogi.
 * @author lasse
 * @version 3 Feb 2021
 *
 */
public class SaliSplashController implements ModalControllerInterface<String>{
    
    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    
    @FXML private void handleOK() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }
    

    @FXML private void handleCancel() {
        ModalController.closeStage(textVastaus);
    }
    
    
    @Override
    public String getResult() {
        return vastaus;
    }
    
    
    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    
    
    /**
     * Mitä tehdään, kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }
    
    
    /**
     * Luodaan dialogi harjoittelijan nimen kysymiselle, ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                SaliSplashController.class.getResource("SaliSplashView.fxml"),
                "Sali",
                modalityStage, oletus);
    }
}   
