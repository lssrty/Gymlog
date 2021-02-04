package fxSali;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Controller ohjelman pääkäyttöliittymälle.
 * @author lasse
 * @version 24 Jan 2021
 *
 */
public class SaliGUIController {

    @FXML private ComboBoxChooser<String> cbPvm;
    @FXML private ComboBoxChooser<String> cbLisaaSuoritus;
    
    private String kayttajanimi = "Harjoittelija";


    /*
     * Lisää suoritusvalikkoon uuden rivin
     */
    @FXML void handleLisaaSuoritus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta suoritusta");
    }
    
    
    /*
     * Poistaa valitun suorituksen, esim. kyykky 3x5x150kg @ 10
     */
    @FXML private void handlePoistaSuoritus() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko suoritus: EI OSATA POISTAA VIELÄ", "Kyllä", "Ei");
        if ( vastaus ) return;// poistaSuoritus(...
    }
    
    
    /*
     * Lisää uuden harjoituksen. Ohjelma lisää avatessa automaattisesti uuden harjoituksen per päivä,
     * mutta jos halutaan luoda useampi harjoitus samalle päivälle, niin se onnistuu tällä.
     */
    @FXML private void handleLisaaHarjoitus() {
        uusiHarjoitus();
    }
    
    
    /*
     * Poistaa aktiivisen harjoituksen, esim. 24.01.2021 18:00.
     * TODO: Mitä tehdään, kun harjoitus poistetaan? Siirrytäänkö edelliseen, vai onko tyhjä?
     */
    @FXML private void handlePoistaHarjoitus() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko harjoitus: EI OSATA POISTAA VIELÄ", "Kyllä", "Ei");
        if ( vastaus ) return;// poistaHarjoitus(...
    }
    

    /*
     * Näyttää listan tehdyistä liikkeistä ennätyksineen.
     * Liikkeen nimeä klikkaamalla voi nähdä sen suoritushistorian.
     */
    @FXML void handleNaytaLiikkeet() {
        ModalController.showModal(SaliGUIController.class.getResource("SaliLiikkeetView.fxml"), "Sali", null, "");
    }
    

    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    
    
    /*
     * Pitäisi kutsua uusiHarjoitus-metodia toisen harjoituksen luomiseksi samalle päivälle.
     * En osaa kuitenkaan vielä lukea Combo boxin tekstiä ja toteuttaa sillä ehtolausetta.
     * En tiedä miksi kyseinen getSelectedText() ei yksinään toimi.
     */
    @FXML
    void Select() {
        String harjoitus = cbPvm.getSelectedText();
        if (harjoitus == "Uusi harjoitus...") uusiHarjoitus();
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
     * Lisää automaattisesti avauspäivälle luodun harjoituksen lisäksi
     * uuden harjoituksen eri kellonajalla
     */
    public void uusiHarjoitus() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta harjoitusta");
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
}