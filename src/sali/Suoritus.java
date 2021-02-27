/**
 * 
 */
package sali;

import java.io.OutputStream;
import java.io.PrintStream;
import static kanta.RasitusTarkistus.*;

/**
 * TODO: Poista turhat getterit, anna toteuttaa saman switchillä varmaankin kaikissa tarvittavissa tilanteissa?
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Suoritus                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää suorituksen attribuutit(liike,toistot jne)|                   |
 * | - osaa tarkistaa, että rasitus on 1-10 välillä     |                   | // <--- TODO: tee rasituksen arvon tarkistus
 * | - osaa muuttaa 4|2|2|2|1|125|8|Hyvin meni          |                   |
 * |   suorituksen tiedoiksi                            |                   |
 * | - osaa laittaa merkkijonon i:nneksi kentäksi       |                   |
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   |
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
 * @version 24 Feb 2021
 */
public class Suoritus {
    
    private int     tunnusNro;
    private int     harjoitusID = 0;
    private int     liikeID     = 0;
    private int     sarjat      = 0;
    private int     toistot     = 0;
    private double  painot      = 0;
    private double  rasitus     = 0;
    private String  kommentit   = "";
    
    private static int seuraavaNro    = 1;
    
    
    /**
     * @return harjoituksen ID
     * TODO: Lisää testejä
     * @example
     * <pre name="test">
     * Suoritus kyykkysarja = new Suoritus();
     * kyykkysarja.getHarjoitusID() === 0;
     * </pre>
     */
    public int getHarjoitusID() {
        return harjoitusID;
    }
    
    
    /**
     * @return liikkeen ID
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getLiikeID() === 1;
     * </pre>
     */
    public int getLiikeID() {
        return liikeID;
    }
    
    
    /**
     * @return suorituksen sarjamäärä
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getSarjat() === 3;
     * </pre>
     */
    public int getSarjat() {
        return sarjat;
    }
    
    
    /**
     * @return suorituksen toistomäärät
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getToistot() === 5;
     * </pre>
     */
    public int getToistot() {
        return toistot;
    }
    
    
    /**
     * @return Sarjapainot
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getPainot() ~~~ 160;
     * </pre>
     */
    public double getPainot() {
        return painot;
    }
    
    
    /**
     * @return rasitus
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getRasitus() ~~~ 8;
     * </pre>
     */
    public double getRasitus() {
        return rasitus;
    }
    
    
    /**
     * @return suorituksen kommentit
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getKommentit() === "Selkä kipeä";
     * </pre>
     */
    public String getKommentit() {
        return kommentit;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot suoritukselle.
     * Harjoituksen ID arvotaan 1-9999 väliltä.
     */
    public void taytaKyykkyTiedoilla() {
        harjoitusID = ((int) (Math.random() * (9999 - 1)) + 1);
        liikeID = 1;
        sarjat = 3;
        toistot = 5;
        painot = 160;
        rasitus = 8;
        kommentit = "Selkä kipeä";
    }
    
    
    /**
     * Tulostetaan suorituksen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro));
        out.print("  harjoituksen ID: " + harjoitusID + " liikkeen ID: " + liikeID);
        out.print(" sarjat: " + sarjat + " toistot: " + toistot + " painot: " + painot + " rasitus: " + rasitus);
        out.println("  kommentit: " + kommentit);
    }
    
    
    /**
     * Tulostetaan suorituksen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa jäsenelle seuraavan rekisterinumeron.
     * @return jäsenen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja1 = new Suoritus();
     *   kyykkysarja1.getTunnusNro() === 0;
     *   kyykkysarja1.rekisteroi();
     *   Suoritus kyykkysarja2 = new Suoritus();
     *   kyykkysarja2.rekisteroi();
     *   int n1 = kyykkysarja1.getTunnusNro();
     *   int n2 = kyykkysarja2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa suorituksen tunnusnumeron.
     * @return suorituksen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Kertoo, minkä sarakkeen kohdalle palautetaan mitäkin tietoa
     * @param i sarakkeen indeksi
     * @return sarakkeeseen tuleva tieto
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.anna(5) === "Selkä kipeä";
     * </pre>
     */
    public String anna(int i) {
        switch (i) {
            case 0: return ""+liikeID;
            case 1: return ""+sarjat;
            case 2: return ""+toistot;
            case 3: return ""+painot;
            case 4: return ""+rasitus;
            case 5: return kommentit;
            default: break;
        }
        return "";
    }
    
    
    /**
     *
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Suoritus kyykkysarja = new Suoritus(), kyykkysarja2 = new Suoritus();
        kyykkysarja.rekisteroi();
        kyykkysarja2.rekisteroi();
        
        kyykkysarja.tulosta(System.out);
        kyykkysarja.taytaKyykkyTiedoilla();
        kyykkysarja.tulosta(System.out);
        
        kyykkysarja2.tulosta(System.out);
        kyykkysarja2.taytaKyykkyTiedoilla();
        kyykkysarja2.tulosta(System.out);
        
    }
}
