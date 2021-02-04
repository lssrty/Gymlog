package fxSali;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;

/**
 * Controller ohjelman liikelistalle. TODO: Saata toimivaksi. Jotain säätöä modaalisuuden kanssa?
 * @author lasse
 * @version 3 Feb 2021
 *
 */
public class SaliLiikkeetController implements ModalControllerInterface<String>{

    
    /*
     * Näyttää historian liikkeen suorituksista
     */
    @FXML void handleLiikeHistoria() {
        Dialogs.showMessageDialog("Ei osata vielä näyttää liikehistoriaa");
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
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }

}