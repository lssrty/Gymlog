/**
 * 
 */
package sali;

import java.io.*;
import java.util.*;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Suoritukset                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Suoritus        |
 * | - pitää yllä rekisteriä suorituksista, eli osaa    |                   |
 * |   lisätä ja poistaa suorituksia                    |                   |<-- TODO: Suoritusten poisto
 * | - lukee ja kirjoittaa suoritukset tiedostoon       |                   |
 * | - osaa etsiä liikkeistä niiden suurimman           |                   | <-- TODO: Liikekohtaisten ennätysten etsintä
 * |   sarjapainon eli ennätyksen                       |                   |
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
 * @version 25 Feb 2021
 *
 */
public class Suoritukset implements Iterable<Suoritus> {
    
    private boolean muutettu = false;
    private String tiedostonPerusNimi = "suoritukset";
    private static final int MAX_SUORITUKSIA = 8;
    private int lkm = 0;
    private Suoritus[] alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Suoritukset() {
        alkiot = new Suoritus[MAX_SUORITUKSIA];
    }
    
    
    /**
     * Lisätään uusi suoritus taulukkoon
     * @param suoritus lisättävä suoritus
     * @example
     * <pre name="test">
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus();
     * suoritukset.getLkm() === 0;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 1;
     * suoritukset.lisaa(kyykkysarja2); suoritukset.getLkm() === 2;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 3;
     * suoritukset.anna(0) === kyykkysarja1;
     * suoritukset.anna(1) === kyykkysarja2;
     * suoritukset.anna(2) === kyykkysarja1;
     * suoritukset.anna(1) == kyykkysarja1 === false;
     * suoritukset.anna(1) == kyykkysarja2 === true;
     * suoritukset.anna(3) === kyykkysarja1; #THROWS IndexOutOfBoundsException
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 4;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 5;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 6;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 7;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 8;
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 9; // Tässä luodaan kasvatettu taulukko, jotta kaikki mahtuu
     * </pre>
     */
    public void lisaa(Suoritus suoritus) {
        if ( lkm >= alkiot.length) {
            Suoritus[] kasvatettuAlkiot = new Suoritus[alkiot.length + MAX_SUORITUKSIA];
            for (int i = 0; i < alkiot.length; i++) {
                kasvatettuAlkiot[i] = alkiot[i];
            }
            alkiot = kasvatettuAlkiot;
        }
        alkiot[lkm] = suoritus;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * Poistaa liikkeen jolla on valittu tunnusNro
     * @param id poistettavan liikkeen tunnusnumero
     * @return 1 jos poistettiin, 0 jos ei löydy
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus(), kyykkysarja3 = new Suoritus();
     * kyykkysarja1.rekisteroi(); kyykkysarja2.rekisteroi(); kyykkysarja3.rekisteroi();
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 1;
     * suoritukset.lisaa(kyykkysarja2); suoritukset.getLkm() === 2;
     * suoritukset.lisaa(kyykkysarja3); suoritukset.getLkm() === 3;
     * suoritukset.anna(0) === kyykkysarja1;
     * suoritukset.anna(1) == kyykkysarja2 === true;
     * int id1 = kyykkysarja1.getTunnusNro();
     * suoritukset.poista(id1+1) === 1;
     * suoritukset.getLkm() === 2;
     * suoritukset.poista(id1) === 1; suoritukset.getLkm() === 1;
     * suoritukset.anna(0) === kyykkysarja3;
     * suoritukset.poista(id1+3) === 0; suoritukset.getLkm() === 1;
     * </pre>
     * 
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    } 
    
    
    /**
     * Etsii suorituksen tunnusnumeron perusteella
     * @param id tunnusnumero, jonka mukaan etsitään
     * @return löytyneen liikkeen indeksi tai -1 jos ei löydy
     * <pre name="test">
     * #THROWS SailoException 
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus(), kyykkysarja3 = new Suoritus();
     * kyykkysarja1.rekisteroi(); kyykkysarja2.rekisteroi(); kyykkysarja3.rekisteroi();
     * suoritukset.lisaa(kyykkysarja1); suoritukset.getLkm() === 1;
     * suoritukset.lisaa(kyykkysarja2); suoritukset.getLkm() === 2;
     * suoritukset.lisaa(kyykkysarja3); suoritukset.getLkm() === 3;

     * int id1 = kyykkysarja1.getTunnusNro();
     * suoritukset.etsiId(id1+1) === 1;
     * suoritukset.etsiId(id1+2) === 2;
     * </pre>
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
    } 
    
    
    /**
     * Korvaa suorituksen tietorakenteessa.  Ottaa suorituksen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva suoritus.  Jos ei löydy,
     * niin lisätään uutena suorituksena.
     * @param suoritus lisättävän suorituksen viite.  Huom tietorakenne muuttuu omistajaksi
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus();
     * kyykkysarja1.rekisteroi(); kyykkysarja2.rekisteroi();
     * suoritukset.getLkm() === 0;
     * suoritukset.korvaaTaiLisaa(kyykkysarja1); suoritukset.getLkm() === 1;
     * suoritukset.korvaaTaiLisaa(kyykkysarja2); suoritukset.getLkm() === 2;
     * Suoritus kyykkysarja3 = kyykkysarja1.clone();
     * kyykkysarja3.taytaKyykkyTiedoilla();
     * Iterator<Suoritus> it = suoritukset.iterator();
     * it.next() == kyykkysarja1 === true;
     * suoritukset.korvaaTaiLisaa(kyykkysarja3); suoritukset.getLkm() === 2;
     * it = suoritukset.iterator();
     * Suoritus s0 = it.next();
     * s0 === kyykkysarja3;
     * s0 == kyykkysarja3 === true;
     * s0 == kyykkysarja1 === false;
     * </pre>
     */
    public void korvaaTaiLisaa(Suoritus suoritus) {
        int id = suoritus.getTunnusNro();
        for (int i = 0; i < lkm; i++) {
            if ( alkiot[i].getTunnusNro() == id ) {
                alkiot[i] = suoritus;
                muutettu = true;
                return;
            }
        }
        lisaa(suoritus);
    }
    
    
    /**
     * Lukee suoritukset tiedostosta.
     * @param tied tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Suoritukset suoritteet = new Suoritukset();
     *  Suoritus kyykky21 = new Suoritus(2); kyykky21.taytaKyykkyTiedoilla(2);
     *  Suoritus kyykky11 = new Suoritus(1); kyykky11.taytaKyykkyTiedoilla(1);
     *  Suoritus kyykky22 = new Suoritus(2); kyykky22.taytaKyykkyTiedoilla(2);
     *  Suoritus kyykky12 = new Suoritus(1); kyykky12.taytaKyykkyTiedoilla(1);
     *  Suoritus kyykky23 = new Suoritus(2); kyykky23.taytaKyykkyTiedoilla(2);
     *  String tiedNimi = "testisuoritukset";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  suoritteet.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  suoritteet.lisaa(kyykky21);
     *  suoritteet.lisaa(kyykky11);
     *  suoritteet.lisaa(kyykky22);
     *  suoritteet.lisaa(kyykky12);
     *  suoritteet.lisaa(kyykky23);
     *  suoritteet.tallenna();
     *  suoritteet = new Suoritukset();
     *  suoritteet.lueTiedostosta(tiedNimi);
     *  Iterator<Suoritus> i = suoritteet.iterator();
     *  i.next().toString() === kyykky21.toString();
     *  i.next().toString() === kyykky11.toString();
     *  i.next().toString() === kyykky22.toString();
     *  i.next().toString() === kyykky12.toString();
     *  i.next().toString() === kyykky23.toString();
     *  i.hasNext() === false;
     *  suoritteet.lisaa(kyykky23);
     *  suoritteet.tallenna();
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
                Suoritus suo = new Suoritus();
                suo.parse(rivi); // voisi olla virhekäsittely
                lisaa(suo);
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
     * Tallentaa suoritukset tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Suoritus suo : this) {
                fo.println(suo.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palauttaa viitteen i:teen suoritukseen.
     * @param i monennenko suorituksen viite halutaan
     * @return viite suoritukseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella 
     */
    public Suoritus anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    

    /**
     * Palauttaa harjoittelijan suoritusten lukumäärän
     * @return suoritusten lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }
    
    
    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    
    /**
     * Luokka suoritusten iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #PACKAGEIMPORT
     * #import java.util.*;
     *
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus(), kyykkysarja3 = new Suoritus();
     * kyykkysarja1.rekisteroi(); kyykkysarja2.rekisteroi(); kyykkysarja3.rekisteroi();
     *
     * suoritukset.lisaa(kyykkysarja1); suoritukset.lisaa(kyykkysarja2); suoritukset.lisaa(kyykkysarja3);
     *
     * StringBuffer ids = new StringBuffer(30);
     * for (Suoritus suoritus:suoritukset)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+suoritus.getTunnusNro());           
     *
     * String tulos = " " + kyykkysarja1.getTunnusNro() + " " + kyykkysarja2.getTunnusNro() + " " + kyykkysarja3.getTunnusNro();
     *
     * ids.toString() === tulos;
     *
     * ids = new StringBuffer(30);
     * for (Iterator<Suoritus>  i=suoritukset.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Suoritus suoritus = i.next();
     *   ids.append(" "+suoritus.getTunnusNro());           
     * }
     *
     * ids.toString() === tulos;
     *
     * Iterator<Suoritus>  i=suoritukset.iterator();
     * i.next() == kyykkysarja1  === true;
     * i.next() == kyykkysarja2  === true;
     * i.next() == kyykkysarja3  === true;
     *
     * i.next();  #THROWS NoSuchElementException
     * 
     * </pre>
     */
    public class SuorituksetIterator implements Iterator<Suoritus> {
        private int kohdalla = 0;
        /**
         * Onko olemassa vielä seuraavaa suoritusta
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä suorituksia
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }
        /**
         * Annetaan seuraava suoritus
         * @return seuraava suoritus
         * @throws NoSuchElementException jos seuraavaa alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Suoritus next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
        }
        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Me ei poisteta");
        }
    }
    
    
    /**
     * Iteraattori kaikkien suoritusten läpikäymiseen
     * @return suoritusiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Suoritukset suoritteet = new Suoritukset();
     *  Suoritus kyykky21 = new Suoritus(2); suoritteet.lisaa(kyykky21);
     *  Suoritus kyykky11 = new Suoritus(1); suoritteet.lisaa(kyykky11);
     *  Suoritus kyykky22 = new Suoritus(2); suoritteet.lisaa(kyykky22);
     *  Suoritus kyykky12 = new Suoritus(1); suoritteet.lisaa(kyykky12);
     *  Suoritus kyykky23 = new Suoritus(2); suoritteet.lisaa(kyykky23);
     * 
     *  Iterator<Suoritus> i2=suoritteet.iterator();
     *  i2.next() === kyykky21;
     *  i2.next() === kyykky11;
     *  i2.next() === kyykky22;
     *  i2.next() === kyykky12;
     *  i2.next() === kyykky23;
     *  i2.next() === kyykky12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int hnrot[] = {2,1,2,1,2};
     *  
     *  for ( Suoritus suo:suoritteet ) { 
     *    suo.getHarjoitusID() === hnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<Suoritus> iterator() {
        return new SuorituksetIterator();
    }
    
    
    /**
     * Haetaan kaikki harjoittelijan tietyn harjoituksen suoritukset
     * @param tunnusnro harjoituksen tunnusnumero jolle suorituksia haetaan
     * @return tietorakenne jossa viiteet löydettyihin suorituksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Suoritukset suoritteet = new Suoritukset();
     *  Suoritus kyykky21 = new Suoritus(2); suoritteet.lisaa(kyykky21);
     *  Suoritus kyykky11 = new Suoritus(1); suoritteet.lisaa(kyykky11);
     *  Suoritus kyykky22 = new Suoritus(2); suoritteet.lisaa(kyykky22);
     *  Suoritus kyykky12 = new Suoritus(1); suoritteet.lisaa(kyykky12);
     *  Suoritus kyykky23 = new Suoritus(2); suoritteet.lisaa(kyykky23);
     *  Suoritus kyykky51 = new Suoritus(5); suoritteet.lisaa(kyykky51);
     *  
     *  List<Suoritus> loytyneet;
     *  loytyneet = suoritteet.annaSuoritukset(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = suoritteet.annaSuoritukset(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == kyykky11 === true;
     *  loytyneet.get(1) == kyykky12 === true;
     *  loytyneet = suoritteet.annaSuoritukset(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == kyykky51 === true;
     * </pre> 
     */
    public List<Suoritus> annaSuoritukset(int tunnusnro) {  
        List<Suoritus> loydetyt = new ArrayList<Suoritus>();
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getHarjoitusID() == tunnusnro) loydetyt.add(alkiot[i]);
        }
        
        return loydetyt;
    }
    
    
    /**
     * @param args ei käytössä
     * @throws SailoException jos tiedoston tallennus ei onnistu
     */
    public static void main(String[] args) throws SailoException {
        Suoritukset suoritukset = new Suoritukset();
        Suoritus kyykkysarja = new Suoritus();
        Suoritus kyykkysarja2 = new Suoritus();        
        kyykkysarja.rekisteroi();
        kyykkysarja.taytaKyykkyTiedoilla();
        kyykkysarja2.rekisteroi();
        kyykkysarja2.taytaKyykkyTiedoilla();
        
        suoritukset.lisaa(kyykkysarja);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja2);
        suoritukset.lisaa(kyykkysarja);

        
        System.out.println("======================= Suoritukset testi ====================");
        
        for (int i = 0; i < suoritukset.getLkm(); i++) {
            System.out.println("");
            Suoritus suoritus = suoritukset.anna(i);
            System.out.println("Suoritusindeksi: " + i);
            suoritus.tulosta(System.out);
        }
        suoritukset.tallenna();
    }
}
