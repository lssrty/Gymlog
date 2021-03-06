/**
 * 
 */
package sali;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static kanta.RasitusTarkistus.*;

/**
 * TODO: Poista turhat getterit, anna(int i) toteuttaa saman switchillä varmaankin kaikissa tarvittavissa tilanteissa?
 * TODO: toString, parse, hashcode
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
     */
    public String getKommentit() {
        return kommentit;
    }
    
    
    /**
     * Palauttaa liikkeen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return liikeID ja liikenimi tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Liike penkki = new Liike();
     *   penkki.parse("   2  |  penkki");
     *   penkki.toString() === "2|penkki";
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                getHarjoitusID() + "|" +
                getLiikeID() + "|" +        // TODO: Korvaa liikenimellä ja korvaa getterit anna(i):llä
                getSarjat() + "|" +
                getToistot() + "|" +
                getPainot() + "|" +
                getRasitus() + "|" +
                getKommentit();
    }

    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }

    
    /**
     * Selvittää liikkeen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta liikkeen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.parse("   1  |  1  | 1  | 3 | 5 | 160.0 | 8.0 | Selkä kipeä");
     *   kyykkysarja.getTunnusNro() === 1;
     *   kyykkysarja.toString() === "1|1|1|3|5|160.0|8.0|Selkä kipeä";
     *
     *   kyykkysarja.rekisteroi();
     *   int n = kyykkysarja.getTunnusNro();
     *   kyykkysarja.parse(""+(n+20));       // Otetaan merkkijonosta vain liikeID
     *   kyykkysarja.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kyykkysarja.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        harjoitusID = Mjonot.erota(sb, '|', harjoitusID);
        liikeID = Mjonot.erota(sb, '|', liikeID);
        sarjat = Mjonot.erota(sb, '|', sarjat);
        toistot = Mjonot.erota(sb, '|', toistot);
        painot = Mjonot.erota(sb, '|', painot);
        rasitus = Mjonot.erota(sb, '|', rasitus);
        kommentit = Mjonot.erota(sb, '|', kommentit);
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
        kommentit = "Selkä kipeä " + ((int) (Math.random() * (999 - 1)) + 1);
    }
    
    
    /**
     * TODO: Tulostuksessa muuta liikkeen ID liikkeen nimeksi
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
     * Antaa suoritukselle seuraavan rekisterinumeron.
     * @return suorituksen uusi tunnusNro
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
     * Asettaa lajitteluavaimeksi suorituksen tunnusnumeron
     */
    @Override
    public int hashCode() {
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
     *   kyykkysarja.anna(4) === "8.0";
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
        
        System.out.println("\n" + kyykkysarja);
        System.out.println(kyykkysarja2);
    }
}
