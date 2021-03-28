/**
 * 
 */
package sali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    private boolean muutettu = false;
    private String tiedostonPerusNimi = "harjoitukset";
    
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
        muutettu = true;
    }

    
    /**
     * Poistaa valitun harjoituksen
     * @param harjoitus poistettava harjoitus
     * @return tosi jos löytyi poistettava tietue 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Harjoitukset treenit = new Harjoitukset();
     *  Harjoitus reeni21 = new Harjoitus(); reeni21.rekisteroi();
     *  Harjoitus reeni11 = new Harjoitus(); reeni11.rekisteroi();
     *  Harjoitus reeni22 = new Harjoitus(); reeni22.rekisteroi();
     *  Harjoitus reeni12 = new Harjoitus(); reeni12.rekisteroi();
     *  Harjoitus reeni23 = new Harjoitus(); reeni23.rekisteroi(); 
     *  treenit.lisaa(reeni21);
     *  treenit.lisaa(reeni11);
     *  treenit.lisaa(reeni22);
     *  treenit.lisaa(reeni12);
     *  treenit.poista(reeni23) === false;  treenit.getLkm() === 4;
     *  treenit.poista(reeni11) === true;   treenit.getLkm() === 3;
     * </pre>
     */
    public boolean poista(Harjoitus harjoitus) {
        boolean ret = alkiot.remove(harjoitus);
        if (ret) muutettu = true;
        return ret;
    }
    
    
    /**
     * Lukee harjoitukset tiedostosta.
     * @param tied tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Harjoitukset treenit = new Harjoitukset();
     *  Harjoitus reeni21 = new Harjoitus(); reeni21.rekisteroi();
     *  Harjoitus reeni11 = new Harjoitus(); reeni11.rekisteroi();
     *  Harjoitus reeni22 = new Harjoitus(); reeni22.rekisteroi();
     *  Harjoitus reeni12 = new Harjoitus(); reeni12.rekisteroi();
     *  Harjoitus reeni23 = new Harjoitus(); reeni23.rekisteroi();
     *  String tiedNimi = "testiharjoitukset";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  treenit.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  treenit.lisaa(reeni21);
     *  treenit.lisaa(reeni11);
     *  treenit.lisaa(reeni22);
     *  treenit.lisaa(reeni12);
     *  treenit.lisaa(reeni23);
     *  treenit.tallenna();
     *  treenit = new Harjoitukset();
     *  treenit.lueTiedostosta(tiedNimi);
     *  Iterator<Harjoitus> i = treenit.iterator();
     *  i.next().toString() === reeni21.toString();
     *  i.next().toString() === reeni11.toString();
     *  i.next().toString() === reeni22.toString();
     *  i.next().toString() === reeni12.toString();
     *  i.next().toString() === reeni23.toString();
     *  i.hasNext() === false;
     *  treenit.lisaa(reeni23);
     *  treenit.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Harjoitus har = new Harjoitus();
                har.parse(rivi); // voisi olla virhekäsittely
                lisaa(har);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    
    /**
     * Tallentaa listan harjoituksista viitenumeroineen ja päivämäärineen tiedostoon.  
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Harjoitus har : this) {
                fo.println(har.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
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
     * Palauttaa harjoituksen, jossa on tietty harjoitusID. Jos ei ole, palauttaa null.
     * @param harjoitusID haettavan harjoituksen ID
     * @return haettava harjoitus
     * @example
     * <pre name="test">
     *  Harjoitukset treenit = new Harjoitukset();
     *  Harjoitus reeni1 = new Harjoitus(); reeni1.setHarjoitusID(1); treenit.lisaa(reeni1);
     *  Harjoitus reeni2 = new Harjoitus(); reeni2.setHarjoitusID(2); treenit.lisaa(reeni2);
     *  Harjoitus reeni3 = new Harjoitus(); reeni3.setHarjoitusID(3); treenit.lisaa(reeni3);
     *  Harjoitus reeni4 = new Harjoitus(); reeni4.setHarjoitusID(4); treenit.lisaa(reeni4);
     *  Harjoitus reeni5 = new Harjoitus(); reeni5.setHarjoitusID(5); treenit.lisaa(reeni5);
     *  treenit.annaHarjoitus(2) === reeni2;
     *  treenit.annaHarjoitus(5) === reeni5;
     *  treenit.annaHarjoitus(10) === null;
     * </pre>
     */
    public Harjoitus annaHarjoitus(int harjoitusID) {
        for (Harjoitus har : alkiot) {
            if ( harjoitusID == har.getHarjoitusID()) return har;
        }
        return null;
    }
    
    
    /**
     * Testiohjelma harjoituksille
     * @param args ei käytössä
     * @throws SailoException jos tallennus ei onnistu
     */
    public static void main(String[] args) throws SailoException {
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
    
        treenit.tallenna();
    }

}
