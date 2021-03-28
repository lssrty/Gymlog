package fxSali;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sali.Harjoitus;
import sali.Liike;
import sali.Sali;
import sali.Suoritus;

/**
 * Controller ohjelman liikelistalle. TODO: Tarkista, hakeeko SaliGUIControllerissa olevan salin. Säätöä modaalisuuden kanssa.
 * @author lasse
 * @version 3 Feb 2021
 *
 */
public class SaliLiikkeetController implements ModalControllerInterface<Sali>, Initializable {

    @FXML private StringGrid<Liike> sgLiikkeet;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //  
    }

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


    @Override
    public Sali getResult() {
        return sali;
    }


    @Override
    public void handleShown() {
        haeLiikkeet();
        
    }


    @Override
    public void setDefault(Sali oletus) {
        sali = oletus;
        
    }
    
  //===========================================================================================    
 // Tästä eteenpäin oleva koodi ei liity suoraan käyttöliittymään    
    
    private Sali sali;
    
    /**
     * Hakee tallennetut liikkeet StringGridiin, ja etsii niiden sarjaennätyksen sekä ennätyksen päivämäärän
     * TODO: Miten saadaan tähän SaliGUIControllerin sali?
     */
    private void haeLiikkeet() {
        sgLiikkeet.clear();
       
        
        String[] rivi = new String[3];
         for (Liike lii : sali.annaLiikkeet()) {
             Suoritus ennatys = sali.haeEnnatys(lii.getLiikeID());
             Harjoitus harjoitus = sali.annaHarjoitukset().get(ennatys.getHarjoitusID()-1);
             rivi[0] = lii.getLiikeNimi();
             rivi[1] = ennatys.anna(2) + " x " + ennatys.anna(3); // TODO : suurimman painon hakeminen
             rivi[2] = harjoitus.getPvm().substring(0, 10); // TODO: Ylläolevan liikkeen harjoituksen pvm hakeminen    
             sgLiikkeet.add(lii, rivi); // TODO: Vain sellaisten liikkeiden lisäys, millä on suorituksia
             }
        
    } 
}