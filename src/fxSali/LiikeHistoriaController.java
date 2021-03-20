package fxSali;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * TODO: Laita näyttämään oikean liikkeen dataa mallidatan sijaan
 * TODO: Liike tuodaan samalla tavalla kuin sali LiikkeetControlleriin, sali tuodaan erikseen
 * https://tim.jyu.fi/view/kurssit/tie/ohj2/tyokalut/JavaFX/Dialogs#enemm%C3%A4n-parametreja-modal-dialogille
 * Liikehistorian esittämisen hoitava luokka
 * 
 * @author lasse
 * @version 3.3.2021
 */
public class LiikeHistoriaController implements ModalControllerInterface<String> {
    @FXML TextArea tulostusAlue;
    
    @FXML private void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }
    
    
    @Override
    public String getResult() {
        return null;
    } 

    
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        // tulostusAlue.setText(oletus);
    }

    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //
    }
    
    
    /**
     * @return alue johon tulostetaan
     */
    public TextArea getTextArea() {
        return tulostusAlue;
    }
    
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     * @return kontrolleri, jolta voidaan pyytää lisää tietoa
     */
    public static LiikeHistoriaController tulosta(String tulostus) {
        LiikeHistoriaController tulostusCtrl = 
          ModalController.showModeless(LiikeHistoriaController.class.getResource("LiikeHistoriaView.fxml"),
                                       "Tulostus", tulostus);
        return tulostusCtrl;
    }

}
