package fxSali;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author lasse
 * @version 24 Jan 2021
 *
 */
public class SaliGUIController {

    @FXML private ComboBoxChooser<String> cbPvm;
    @FXML private ComboBoxChooser<String> cbLisaaSuoritus;
    
    private String kayttajanimi = "Harjoittelija";


    @FXML private void handlePoistaSuoritus() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa suorituksia");
    }
    
    
    @FXML private void handleLisaaHarjoitus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta harjoitusta");
    }
    
    
    @FXML private void handlePoistaHarjoitus() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa aktiivista harjoitusta");
    }
    

    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    @FXML void handleVaihdaKayttaja() {
        ModalController.showModal(SaliGUIController.class.getResource("SaliSplashView.fxml"), "Sali", null, "");
    }
    
    
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    @FXML private void handleTietoja() {
        ModalController.showModal(SaliGUIController.class.getResource("SaliTietoa.fxml"), "Sali", null, "");
    }
    
    
    //===========================================================================================    
    // Tästä eteenpäin oleva koodi ei liity suoraan käyttöliittymään    
            
    
    /**
     * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        kayttajanimi = nimi;
        String virhe = "Ei osata vielä luoda tai lukea tiedostoja";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusinimi = SaliSplashController.kysyNimi(null, kayttajanimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan (paitsi ei osata vielä)");
    }
    
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    private void uusiLiikeValittu() {
        String valittuLiike = cbLisaaSuoritus.getSelectedText();
        if (valittuLiike == "Uusi liike...") {
            ModalController.showModal(SaliGUIController.class.getResource("SaliUusiLiike.fxml"), "Sali", null, "");
        }
    }
}
