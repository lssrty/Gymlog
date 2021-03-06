/**
 * 
 */
package sali;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Liike                                 | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   |
 * | - osaa laittaa merkkijonon i:nneksi kentäksi       |                   |
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
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author lasse
 * @version 26 Feb 2021
 *
 */
public class Liike {
    
    private int    liikeID      = 0;
    private String liikeNimi    = "";

    private static int seuraavaNro    = 1;
    
    
    /**
     * Antaa jäsenelle seuraavan rekisterinumeron.
     * @return jäsenen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Liike kyykky = new Liike();
     *   kyykky.getLiikeID() === 0;
     *   kyykky.rekisteroi();
     *   Liike penkki = new Liike();
     *   penkki.rekisteroi();
     *   int n1 = kyykky.getLiikeID();
     *   int n2 = penkki.getLiikeID();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        liikeID = seuraavaNro;
        seuraavaNro++;
        return liikeID;
    }
    
    
    /**
     * Palauttaa liikkeen tunnusnumeron.
     * @return liikkeen tunnusnumero
     */
    public int getLiikeID() {
        return liikeID;
    }
    
    
    /**
     * Asettaa liikeID:n ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setLiikeID(int nr) {
        liikeID = nr;
        if (liikeID >= seuraavaNro) seuraavaNro = liikeID + 1;
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
        return "" + getLiikeID() + "|" + liikeNimi;
    }

    
    /**
     * Selvittää liikkeen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva liikeID.
     * @param rivi josta liikkeen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Liike penkki = new Liike();
     *   penkki.parse("   2  |  penkki");
     *   penkki.getLiikeID() === 2;
     *   penkki.toString() === "2|penkki";
     *
     *   penkki.rekisteroi();
     *   int n = penkki.getLiikeID();
     *   penkki.parse(""+(n+20));       // Otetaan merkkijonosta vain liikeID
     *   penkki.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   penkki.getLiikeID() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setLiikeID(Mjonot.erota(sb, '|', getLiikeID()));
        liikeNimi = Mjonot.erota(sb, '|', liikeNimi);
    }

    
    /**
     * Palauttaa liikkeen nimen.
     * @return liikkeen nimi
     * @example
     * <pre name="test">
     * Liike dippi = new Liike();
     * dippi.setLiikeNimi("dippi");
     * dippi.getLiikeNimi() === "dippi";
     * </pre>
     */
    public String getLiikeNimi() {
        return liikeNimi;
    }
    
    
    /**
     * Asettaa liikkeen nimen
     * @param nimi liikkeelle annettava nimi
     * @example
     * <pre name="test">
     * Liike pystypunnerrus = new Liike();
     * pystypunnerrus.setLiikeNimi("pystypunnerrus");
     * pystypunnerrus.getLiikeNimi() === "pystypunnerrus";
     * </pre>
     */
    public void setLiikeNimi(String nimi) {
        liikeNimi = nimi;
    }
    
    
    /**
     * Tulostetaan liikkeen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", liikeID) + " " + liikeNimi);
    }

    
    /**
     * Asettaa lajitteluavaimeksi liikeID:n
     */
    @Override
    public int hashCode() {
        return liikeID;
    }

    
    /**
     * Testiohjelma liikkeelle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    
    Liike kyykky = new Liike();
    kyykky.rekisteroi();
    kyykky.setLiikeNimi("kyykky");
    
    Liike penkki = new Liike();
    penkki.rekisteroi();
    penkki.setLiikeNimi("penkki");
    
    Liike maastaveto = new Liike();
    maastaveto.rekisteroi();
    maastaveto.setLiikeNimi("maastaveto");
    
    kyykky.tulosta(System.out);
    penkki.tulosta(System.out);
    maastaveto.tulosta(System.out);
    
    System.out.println(kyykky);
    }

}
