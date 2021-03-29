package fxSali;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sali.*;

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
public class SaliGUIController implements Initializable {

    @FXML private ComboBoxChooser<Harjoitus> cbPvm;
    @FXML private StringGrid<Suoritus> sgSuoritukset;
    @FXML private Label labelVirhe;
    

    /**
     * Alustaa salipäiväkirjan
     * @param url ei käytössä
     * @param bundle ei käytössä
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    
    
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
        poistaSuoritus();
    }
    
    
    /*
     * Lisää uuden harjoituksen. Ohjelma lisää avatessa automaattisesti uuden harjoituksen, jos yhtään
     * harjoitusta ei ole valmiina olemassa
     */
    @FXML private void handleLisaaHarjoitus() {
        uusiHarjoitus();
    }
    
    
    /*
     * Poistaa aktiivisen harjoituksen, esim. 24.01.2021 18:00.
     * TODO: Edelliseen harjoitukseen siirtyminen, kun tämänhetkinen poistetaan
     */
    @FXML private void handlePoistaHarjoitus() {
        poistaHarjoitus();
    }
    

    /*
     * Näyttää listan tehdyistä liikkeistä ennätyksineen.
     * Liikkeen nimeä klikkaamalla voi nähdä sen suoritushistorian.
     */
    @FXML void handleNaytaLiikkeet() {
        ModalController.showModal(SaliLiikkeetController.class.getResource("SaliLiikkeetView.fxml"), "Sali", null, sali);
    }
    

    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handleVaihdaKayttaja() {
        avaa();
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
    private Harjoitus harjoitusKohdalla;
    
    
    /**
     * Tekee tarvittavat muut alustukset. Alustetaan mm.harjoitus-comboboxin kuuntelija.
     */
    protected void alusta() {
        cbPvm.clear();
        cbPvm.addSelectionListener(e -> naytaHarjoitus());
        
        Suoritus apusuoritus = new Suoritus();
        int eka = apusuoritus.ekaKentta();

        sgSuoritukset.setOnGridEdit( (g, suoritus, defValue, r, c) -> {
            if ( c == 0 ) {
                for (Liike liike : sali.annaLiikkeet()) { 
                    if (liike.getLiikeNimi().equalsIgnoreCase(defValue)) { // Asetetaan syötettyä liikkeen nimeä vastaava LiikeID
                        suoritus.asetaLiike(liike);
                    }
                }
                if (!sali.onkoLiike(defValue) && defValue.length() > 0) {
                    Liike uusi = lisaaLiike(defValue);
                    suoritus.asetaLiike(uusi);
                }
            }
            haeSuoritukset(); // TODO: Nyt siirtää valitsimen aina ylimpään. Haittaako?
            return defValue;
        });
        
        sgSuoritukset.setOnGridLiveEdit((g, suoritus, defValue, r, c, edit) -> {
            String virhe = null;
            if ( c != 0 ) virhe = suoritus.aseta(c+eka,defValue); // tarkistetaan, onko syötetty arvo laillinen
            if ( virhe == null ) { // jos on, tehdään tämä
                sali.korvaaTaiLisaa(suoritus); // jotta saadaan muutos
                edit.setStyle(null); // virhetyyli pois
                Dialogs.setToolTipText(edit,"");
                naytaVirhe(virhe);
            } else {
                edit.setStyle("-fx-background-color: red"); // virhetyyli päälle
                Dialogs.setToolTipText(edit,virhe);
                naytaVirhe(virhe);
            }
            return defValue;
        });
    }
    
    
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
     * Hakee tallennetut harjoitukset ComboBoxChooseriin
     */
    private void haeHarjoitukset() {
        cbPvm.clear(); // Poistaa tyhjän arvon comboBoxista
        for (int i = sali.getHarjoituksia()-1; i >= 0; i--) {
            Harjoitus harjoitus = sali.annaHarjoitukset().get(i);
            cbPvm.add(harjoitus.getPvm(), harjoitus); //TODO: Piilota harjoitusID, näytä pelkkä päivämäärä?
        }
        cbPvm.setSelectedIndex(0);
    }
    
    
    /**
     * Asettaa nykyiseksi harjoitukseksi cbPvm:stä valitun harjoituksen.
     * Vaikuttaa uuden suorituksen harjoitusID:hen, sekä siihen,
     * minkä harjoituksen suoritukset näkyvät StringGridissä.
     */
    protected void naytaHarjoitus() {
        harjoitusKohdalla = cbPvm.getSelectedObject();
        if (harjoitusKohdalla == null) return;
        haeSuoritukset();
    }
    
    
    /**
     * Hakee tallennetut suoritukset stringGridiin, ja muuttaa liikeID:t liikenimiksi
     * TODO: overloadattu funktio mille annetaan parametriksi liikeID? Hakisi tietyn liikkeen suoritushistorian
     */
    private void haeSuoritukset() {
        sgSuoritukset.clear();
        if ( sali.getSuorituksia() == 0 ) return;
        String[] rivi = new String[6];
        for (Suoritus suor : sali.annaSuoritukset()) {
            if (suor.getHarjoitusID() == harjoitusKohdalla.getHarjoitusID()) {
                rivi[0] = "";
                if ( suor.getLiikeID() != 0 ) 
                    rivi[0] = sali.annaLiike(suor.getLiikeID()).getLiikeNimi();
                for (int k=1; k < rivi.length; k++)
                    rivi[k] = suor.anna(k);
                sgSuoritukset.add(suor, rivi);
            }
        }
    }
    
    /*
     * Poistetaan valittu suoritus
     */
    private void poistaSuoritus() {
        int r = sgSuoritukset.getRowNr();
        Suoritus suoritus = sgSuoritukset.getObject(r);
        if ( suoritus == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko suoritus: " + suoritus.toString(), "Kyllä", "Ei") ) // TODO: Muuta sanomaan vain valittu suoritus
            return;
        sali.poista(suoritus);
        haeSuoritukset();
    }
    
    
    /*
     * Poistetaan valittu harjoitus ja siihen liitetyt suoritukset
     */
    private void poistaHarjoitus() {
        Harjoitus harjoitus = harjoitusKohdalla;
        if ( harjoitus == null ) return;
        if ( !Dialogs.showQuestionDialog(
                "Poisto", "Poistetaanko harjoitus ja sen suoritukset: " + harjoitus.toString(), "Kyllä", "Ei") )
            return;
        sali.poistaHarjoitus(harjoitus);
        haeHarjoitukset();
    }
    
    
    /**
     * Alustaa salin lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) { //TODO: Voiko olla private?
        kayttajanimi = nimi;
        setTitle("Salipäiväkirja - " + kayttajanimi);
        try {
            sali.lueTiedostosta(nimi);
            
            haeHarjoitukset();
            haeSuoritukset();
            
            return null;
        } catch (SailoException e) {
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
        if (harjoitusKohdalla == null) {
            uusiHarjoitus();
            sali.annaHarjoitukset().get(0);
        }
        return true;
    }
    
    
    /**
     * Lisää uuden harjoituksen
     */
    public void uusiHarjoitus() {
        Harjoitus harjoitus = new Harjoitus();
        harjoitus.rekisteroi(); //TODO: Luo tyhjä rivi, johon voi kirjoittaa halutut tiedot. Jos ei onnistu, luo dialogi.
        
        try {
            sali.lisaa(harjoitus);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden suorituksen lisäämisessä " + e.getMessage());
            return;
        }
        
        haeHarjoitukset();        
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
     */
    private void lisaaSuoritus() {
        if (harjoitusKohdalla == null) {
            Dialogs.showMessageDialog("Luo ensin harjoitus\nsuoritukselle");
            return;
        }
        
        Suoritus suoritus = new Suoritus(harjoitusKohdalla.getHarjoitusID());
        suoritus.rekisteroi();
        // suoritus.taytaKyykkyTiedoilla();    //TODO: Luo tyhjä rivi, johon voi kirjoittaa halutut tiedot. Jos ei onnistu, luo dialogi.
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
     * Lisätään käyttäjälle uusi liike.
     */
    private Liike lisaaLiike(String nimi) {
        Liike liike = new Liike();
        liike.setLiikeNimi(nimi);
        liike.rekisteroi();
        try {
            sali.lisaa(liike);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return liike; 
    }
    
    
    /**
     * Asetetaan käytettävä sali
     * @param sali sali, jota käytetään tässä käyttöliittymässä
     */
    public void setSali(Sali sali) {
        this.sali = sali;
    }
}