/**
 * 
 */
package sali;

import java.io.PrintStream;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Suoritus                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää suorituksen attribuutit(liike,toistot jne)|                   |
 * | - osaa tarkistaa, että rasitus on 1-10 välillä     |                   |
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
 *
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
    
    
    /**
     * Tulostetaan suorituksen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + "  " + harjoitusID + "  "
                + liikeID);
        out.print(" sarjat: " + sarjat + " toistot: " + toistot + " painot: " + painot + " rasitus: " + rasitus);
        out.println("  kommentit: " + kommentit);
    }
    

    /**
     *
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Suoritus kyykkysarja = new Suoritus();
        Suoritus kyykkysarja2 = new Suoritus();
        
        // kyykkysarja.rekisteroi();
        // kyykkysarja2.rekisteroi();
        
        kyykkysarja.tulosta(System.out);
        // kyykkysarja.taytaKyykkyTiedoilla();
        kyykkysarja.tulosta(System.out);
        
        kyykkysarja2.tulosta(System.out);
        // kyykkysarja2.taytaKyykkyTiedoilla();
        kyykkysarja2.tulosta(System.out);
        
    }

}
