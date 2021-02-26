/**
 * 
 */
package sali;

import java.io.PrintStream;

/**
 * TODO: Liike-luokka toimivaksi
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
    }

}
