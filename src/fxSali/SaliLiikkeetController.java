package fxSali;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import sali.Liike;
import sali.Sali;

/**
 * Controller ohjelman liikelistalle. TODO: Saata toimivaksi. Jotain säätöä modaalisuuden kanssa?
 * @author lasse
 * @version 3 Feb 2021
 *
 */
public class SaliLiikkeetController implements ModalControllerInterface<Sali>{

    @FXML private StringGrid<Liike> sgLiikkeet;
    
    /*
     * Näyttää historian liikkeen suorituksista
     */
    @FXML void handleLiikeHistoria() {
        ModalController.showModal(SaliLiikkeetController.class.getResource("LiikeHistoriaView.fxml"), "Sali", null, "");
    }


    /*
     * Lisää tyhjän rivin uudelle liikkeelle
     */
    @FXML void handleLisaaLiike() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta liikettä");
    }

    
    /*
     * Avaa varmistusikkunan valitun liikkeen poistolle
     */
    @FXML void handlePoistaLiike() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko liike: EI OSATA POISTAA VIELÄ", "Kyllä", "Ei");
        if ( vastaus ) return; // poistaLiike(...
    }


    @Override
    public Sali getResult() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void setDefault(Sali oletus) {
        // TODO Auto-generated method stub
        
    }
    
  //===========================================================================================    
 // Tästä eteenpäin oleva koodi ei liity suoraan käyttöliittymään    
    


}