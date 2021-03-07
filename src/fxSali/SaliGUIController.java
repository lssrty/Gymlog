package fxSali;

import fi.jyu.mit.fxgui.Chooser;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sali.SailoException;
import sali.Sali;
import sali.Suoritus;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Naytto                                | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Sali            |
 * | - hoitaa kaiken näyttöön tulevan tekstin           | - Suoritus        |
 * | - hoitaa kaiken tiedon pyytämisen käyttäjältä      | - Liike           |
 * |                                                    | - Harjoitus       |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * Controller ohjelman pääkäyttöliittymälle.
 * @author lasse
 * @version 24 Jan 2021
 *
 */
public class SaliGUIController {

    @FXML private ComboBoxChooser<String> cbPvm;
    @FXML private StringGrid<Suoritus> sgSuoritukset;
    @FXML private Label labelVirhe;
    

    /*
     * Lisää suoritusvalikkoon uuden rivin
     */
    @FXML void handleLisaaSuoritus() {
        // Dialogs.showMessageDialog("Ei osata vielä lisätä uutta suoritusta");
        lisaaSuoritus();
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
     * TODO: Edelliseen harjoitukseen siirtyminen, kun tämänhetkinen poistetaan
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
    
    private String kayttajanimi = "Harjoittelija";
    private Sali sali;
    private Suoritus suoritusKohdalla;
    private TextArea areaSuoritus = new TextArea();
    
    
    /**
     * Näyttää suoritusten alapuolella virhetekstin, jos tulee virhe
     * @param virhe näytettävä virhe
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    private void setTitle(String title) {
        ModalController.getStage(sgSuoritukset).setTitle(title);  //TODO: Selvitä, mitä getStage tekee, mallissa getStage(hakuehto)
    }
    
    
    /**
     * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        kayttajanimi = nimi;
        setTitle("Salipäiväkirja - " + kayttajanimi);
        try {
            sali.lueTiedostosta(nimi);
            // hae(0); //TODO: Toteuta hae-metodi suoritusten hakemiseksi sgSuoritukset -listaan
            return null;
        } catch (SailoException e) {
           // hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
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
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            sali.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }

    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
 
 
    
    
    
    /**
     * Lisätään käyttäjälle uusi suoritus
     * TODO: Luo suorituksen yhteys käyttäjän tiettyyn harjoitukseen
     * TODO: Muuta luotu suoritus näyttämään liikettä, eikä sen ID:tä
     */
    private void lisaaSuoritus() {
        Suoritus suoritus = new Suoritus(); //TODO: Lisää tähän parametriksi harjoitus, johon lisätään
        suoritus.rekisteroi();
        suoritus.taytaKyykkyTiedoilla();    //TODO: Luo tyhjä rivi, johon voi kirjoittaa halutut tiedot. Jos ei onnistu, luo dialogi.
        try {
            sali.lisaa(suoritus);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden suorituksen lisäämisessä " + e.getMessage());
            return;
        }
        
        String[] rivi = new String[6];
        for (int k=0; k < rivi.length; k++)
            rivi[k] = suoritus.anna(k);
        sgSuoritukset.add(suoritus, rivi);
    }
    
    
    /**
     * Asetetaan käytettävä sali
     * @param sali sali, jota käytetään tässä käyttöliittymässä
     */
    public void setSali(Sali sali) {
        this.sali = sali;
    }
}