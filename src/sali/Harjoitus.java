/**
 * 
 */
package sali;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Harjoitus                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - osaa laittaa päivämäärän ja kellonajan 1.        |                   |
 * | kentäksi                                           |                   |
 * | - osaa antaa i:nnen kentän merkkijonona            |                   |
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
 * @author lasse
 * @version 26 Feb 2021
 *
 */
public class Harjoitus {
    private int harjoitusID;
    private String pvm;
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Alustetaan harjoitus nykyisellä päivämäärällä
     * @example
     * <pre name="test">
     * #import java.time.LocalDateTime;
     * #import java.time.format.DateTimeFormatter;
     * LocalDateTime paivamaara = LocalDateTime.now();
     * DateTimeFormatter pvmMuotoilija = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss");
     * String pvm = paivamaara.format(pvmMuotoilija);
     * Harjoitus treeni = new Harjoitus();
     * treeni.getPvm() === pvm;
     * </pre>
     */
    public Harjoitus() {
        LocalDateTime paivamaara = LocalDateTime.now();
        DateTimeFormatter pvmMuotoilija = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss");
        this.pvm = paivamaara.format(pvmMuotoilija);
    }
    

    /**
     * Palautetaan harrastuksen oma id
     * @return harrastuksen id
     */
    public int getHarjoitusID() {
        return harjoitusID;
    }
    
    
    /**
     * Palautetaan harrastuksen päivämäärä ja aika
     * @return harrastuksen pvm
     */
    public String getPvm() {
        return pvm;
    }
    
    
    /**
     * Antaa harrastukselle seuraavan rekisterinumeron.
     * @return harrastuksen uusi tunnus_nro
     * @example
     * <pre name="test">
     *   Harjoitus treeni1 = new Harjoitus();
     *   treeni1.getHarjoitusID() === 0;
     *   treeni1.rekisteroi();
     *   Harjoitus treeni2 = new Harjoitus();
     *   treeni2.rekisteroi();
     *   int n1 = treeni1.getHarjoitusID();
     *   int n2 = treeni2.getHarjoitusID();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        harjoitusID = seuraavaNro;
        seuraavaNro++;
        return harjoitusID;
    }

      
    /**
     * Palauttaa harjoituksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return harjoitusID ja pvm tolppaeroteltuna merkkijonona 
     */
    @Override
    public String toString() {
        return "" + getHarjoitusID() + "|" + pvm;
    }

    
    /**
     * Selvittää harjoituksen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva harjoitusID.
     * @param rivi josta harjoituksen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Harjoitus treeni1 = new Harjoitus();
     *   treeni1.parse("   2  |  07.03.2021_15:04.35");
     *   treeni1.getHarjoitusID() === 2;
     *   treeni1.toString() === "2|07.03.2021_15:04.35";
     *
     *   treeni1.rekisteroi();
     *   int n = treeni1.getHarjoitusID();
     *   treeni1.parse(""+(n+20));       // Otetaan merkkijonosta vain harjoitusID
     *   treeni1.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   treeni1.getHarjoitusID() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setHarjoitusID(Mjonot.erota(sb, '|', getHarjoitusID()));
        pvm = Mjonot.erota(sb, '|', pvm);
    }
    
    
    /**
     * Asettaa harjoitusID:n ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    public void setHarjoitusID(int nr) {
        harjoitusID = nr;
        if (harjoitusID >= seuraavaNro) seuraavaNro = harjoitusID + 1;
    }


    /**
     * Tulostetaan harjoituksen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(harjoitusID + " " + pvm);
    }

    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Harjoitus har = new Harjoitus();
        har.rekisteroi();
        har.tulosta(System.out);
        
        var har2 = new Harjoitus();
        har2.rekisteroi();
        har2.tulosta(System.out);
        
        System.out.println(har);
        System.out.println(har2);
    
    }

}
