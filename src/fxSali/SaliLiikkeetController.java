package fxSali;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import sali.Liike;
import sali.Sali;
import sali.Suoritus;

/**
 * Controller ohjelman liikelistalle. TODO: Tarkista, hakeeko SaliGUIControllerissa olevan salin. Säätöä modaalisuuden kanssa.
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
    
    /**
     * Hakee tallennetut liikkeet StringGridiin, ja etsii niiden sarjaennätyksen sekä ennätyksen päivämäärän
     * TODO: Miten saadaan tähän SaliGUIControllerin sali?
     */
    private void haeLiikkeet() {
        sgLiikkeet.clear();
        /*
        String[] rivi = new String[3];
         for (int i=0; i < sali.getLiikkeita(); i++) {
            Liike lii = sali.annaLiike(i);
            rivi[0] = lii.getLiikeNimi();
            rivi[1] = "1000"; // TODO : suurimman painon hakeminen
            rivi[2] = "1.1.2000"; // TODO: Ylläolevan liikkeen harjoituksen pvm hakeminen    
            sgLiikkeet.add(lii, rivi);
            }
        */
    }   
}