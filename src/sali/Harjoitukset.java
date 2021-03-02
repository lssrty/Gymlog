/**
 * 
 */
package sali;

import java.util.*;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Harjoitukset                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - pitää yllä rekisteriä harjoitusten päivämääristä |                   |
 * | - osaa lisätä ja poistaa harjoituksen              |                   | <- TODO: Harjoituksen poisto
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
public class Harjoitukset implements Iterable<Harjoitus> {
    
    
    /** Taulukko harrastuksista */
    private final Collection<Harjoitus> alkiot = new ArrayList<Harjoitus>();


    /**
     * Harjoitusten alustaminen
     */
    public Harjoitukset() {
        // toistaiseksi ei tarvitse tehdä mitään
    }
    
    
    /**
     * Lisää uuden harjoituksen tietorakenteeseen.  Ottaa harjoituksen omistukseensa.
     * @param har lisättävä harjoitus.  Huom tietorakenne muuttuu omistajaksi.
     */
    public void lisaa(Harjoitus har) {
        alkiot.add(har);
    }

    
    /**
     * Palauttaa harjoittelijan harjoitusten lukumäärän
     * @return harjoitusten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Iteraattori kaikkien harjoitusten läpikäymiseen
     * @return harjoitusiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Harjoitukset treenit = new Harjoitukset();
     *  Harjoitus reeni1 = new Harjoitus(); treenit.lisaa(reeni1);
     *  Harjoitus reeni2 = new Harjoitus(); treenit.lisaa(reeni2);
     *  Harjoitus reeni3 = new Harjoitus(); treenit.lisaa(reeni3);
     *  Harjoitus reeni4 = new Harjoitus(); treenit.lisaa(reeni4);
     *  Harjoitus reeni5 = new Harjoitus(); treenit.lisaa(reeni5);
     * 
     *  Iterator<Harjoitus> i2=treenit.iterator();
     *  i2.next() === reeni1;
     *  i2.next() === reeni2;
     *  i2.next() === reeni3;
     *  i2.next() === reeni4;
     *  i2.next() === reeni5;
     *  i2.next() === reeni3;  #THROWS NoSuchElementException  
     * </pre>
     */
    @Override
    public Iterator<Harjoitus> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Haetaan kaikki harjoittelijan harjoitukset
     * @return tietorakenne jossa viiteet löydettyihin harjoituksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Harjoitukset treenit = new Harjoitukset();
     *  Harjoitus reeni1 = new Harjoitus(); treenit.lisaa(reeni1);
     *  Harjoitus reeni2 = new Harjoitus(); treenit.lisaa(reeni2);
     *  Harjoitus reeni3 = new Harjoitus(); treenit.lisaa(reeni3);
     *  Harjoitus reeni4 = new Harjoitus(); treenit.lisaa(reeni4);
     *  Harjoitus reeni5 = new Harjoitus(); treenit.lisaa(reeni5);
     *  
     *  List<Harjoitus> loytyneet;
     *  loytyneet = treenit.annaHarjoitukset();
     *  loytyneet.size() === 5; 
     *  loytyneet.get(0) == reeni1 === true;
     *  loytyneet.get(3) == reeni4 === true;
     * </pre> 
     */
    public List<Harjoitus> annaHarjoitukset() {
        List<Harjoitus> loydetyt = new ArrayList<Harjoitus>();
        for (Harjoitus har : alkiot)
            loydetyt.add(har);
        return loydetyt;
    }

    
    /**
     * Testiohjelma harjoituksille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Harjoitukset treenit = new Harjoitukset();
        Harjoitus reeni1 = new Harjoitus(); reeni1.rekisteroi(); treenit.lisaa(reeni1);
        Harjoitus reeni2 = new Harjoitus(); reeni2.rekisteroi(); treenit.lisaa(reeni2);
        Harjoitus reeni3 = new Harjoitus(); reeni3.rekisteroi(); treenit.lisaa(reeni3);
        Harjoitus reeni4 = new Harjoitus(); reeni4.rekisteroi(); treenit.lisaa(reeni4);
        Harjoitus reeni5 = new Harjoitus(); reeni5.rekisteroi(); treenit.lisaa(reeni5);


        System.out.println("============= Harjoitukset testi =================");

        List<Harjoitus> harjoitukset2 = treenit.annaHarjoitukset();

        for (Harjoitus har : harjoitukset2) {
            har.tulosta(System.out);
        }
    
    }

}
