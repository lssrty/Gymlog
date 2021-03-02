/**
 * 
 */
package sali;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
    }

}
