package fxSali;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
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
        LiikeHistoriaController liikeCtrl = LiikeHistoriaController.tulosta(null);
        // ModalController.showModal(SaliLiikkeetController.class.getResource("LiikeHistoriaView.fxml"), "Sali", null, "");
        tulostaValitut(liikeCtrl.getTextArea());
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
        sgLiikkeet.setSortable(-1, false);
        
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
       
        if ( sali.getSuorituksia() == 0 ) return;
        String[] rivi = new String[3]; // TODO: Piilota suoritukset, joita ei ole tehty. Onnistuu haeEnnatys avulla.
         for (Liike lii : sali.annaLiikkeet()) {
             Suoritus ennatys = sali.haeEnnatys(lii.getLiikeID());
             Harjoitus harjoitus = sali.annaHarjoitus(ennatys.getHarjoitusID());
             rivi[0] = lii.getLiikeNimi();
             rivi[1] = ennatys.anna(2) + " x " + ennatys.anna(3); // TODO : suurimman painon hakeminen
             rivi[2] = "";
             if (harjoitus != null ) 
                 rivi[2] = harjoitus.getPvm().substring(0, 10); // TODO: Ylläolevan liikkeen harjoituksen pvm hakeminen    
             sgLiikkeet.add(lii, rivi); // TODO: Vain sellaisten liikkeiden lisäys, millä on suorituksia
             }
        
    } 
    
    
    /**
     * Tulostaa valitun liikkeen suoritukset tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        Liike liike = sgLiikkeet.getObject();
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println(liike.getLiikeNimi() + " suoritushistoria");
            os.println("-------------------------------------\n\n");
            os.println("Päivämäärä | sarjat | toistot |  painot  | rasitus | kommentit");
            os.println("------------------------------------------------------------");
            for (Suoritus suo: sali.annaSuoritukset()) {
                if ( suo.getLiikeID() == liike.getLiikeID()) {
                    os.print(sali.annaHarjoitus(suo.getHarjoitusID()).getPvm().substring(0, 11));
                    suo.tulosta(os);
                }
            }
        }
    }
}