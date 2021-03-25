/**
 * 
 */
package sali;

import java.io.*;
import java.util.*;


/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Liikkeet                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Liike           |
 * | - Pitää yllä rekisteriä tallennetuista liikkeistä, |                   |
 * |   eli osaa lisätä ja poistaa liikkeen              |                   |
 * |   (vain liikkeitä joita ei ole tehty voi poistaa)  |                   | <- TODO: Poista vain liikkeitä, joita ei ole tehty
 * | - lukee ja kirjoittaa liikkeet tiedostoon          |                   |
 * | - huolehtii, ettei samannimistä liikettä voi lisätä|                   |
 * |   kahdesti                                         |                   |
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
public class Liikkeet implements Iterable<Liike> {
    
    private static final int MAX_LIIKKEITA = 5;
    private boolean muutettu = false;
    private int lkm = 0;
    private String kokoNimi = "Testi Treenaaja";  // Muuta tyhjäksi, kun ohjelma valmis
    private String tiedostonPerusNimi = "liikkeet";
    private Liike[] alkiot;

    
    /**
     * Luodaan alustava taulukko
     */
    public Liikkeet() {
        alkiot = new Liike[MAX_LIIKKEITA];
    }
    
    
    /**
     * Lisätään uusi liike taulukkoon
     * @param liike lisättävä suoritus
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike(), 
     * dippi = new Liike(), pystypunnerrus = new Liike(), alatalja = new Liike(), testi = new Liike();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     * dippi.setLiikeNimi("dippi"); pystypunnerrus.setLiikeNimi("pystypunnerrus"); alatalja.setLiikeNimi("alatalja");
     * testi.setLiikeNimi("kyykky");  
     * liikkeet.getLkm() === 0;
     * liikkeet.lisaa(kyykky); liikkeet.getLkm() === 1;
     * liikkeet.lisaa(testi); #THROWS SailoException
     * liikkeet.lisaa(penkki); liikkeet.getLkm() === 2;
     * liikkeet.lisaa(maastaveto); liikkeet.getLkm() === 3;
     * liikkeet.lisaa(maastaveto); #THROWS SailoException
     * liikkeet.anna(0) === kyykky;
     * liikkeet.anna(1) === penkki;
     * liikkeet.anna(2) === maastaveto;
     * liikkeet.anna(1) == kyykky === false;
     * liikkeet.anna(1) == penkki === true;
     * liikkeet.anna(3) === kyykky; #THROWS IndexOutOfBoundsException
     * liikkeet.lisaa(dippi); liikkeet.getLkm() === 4;
     * liikkeet.lisaa(pystypunnerrus); liikkeet.getLkm() === 5;
     * liikkeet.lisaa(alatalja);  liikkeet.getLkm() === 6;
     * </pre>
     */
    public void lisaa(Liike liike) throws SailoException {
        if ( lkm >= alkiot.length) {
            Liike[] kasvatettuAlkiot = new Liike[alkiot.length + MAX_LIIKKEITA];
            for (int i = 0; i < alkiot.length; i++) {
                kasvatettuAlkiot[i] = alkiot[i];
            }
            alkiot = kasvatettuAlkiot;
        }
        
        if ( Arrays.asList(alkiot).contains(liike) == true ) // Tarkistaa, onko sama olio lisätty
            throw new SailoException("Sama liike on jo olemassa");
        for (Liike olemassaoleva : alkiot) {                 // Tarkistaa, onko eri olio samalla nimellä lisätty
            if (olemassaoleva != null && olemassaoleva.getLiikeNimi().equalsIgnoreCase(liike.getLiikeNimi()))
                throw new SailoException("Sama liike on jo olemassa");
        }
        
        alkiot[lkm] = liike;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * Poistaa liikkeen jolla on valittu liikeID eli tunnusnumero
     * @param id poistettavan liikkeen tunnusnumero
     * @return 1 jos poistettiin, 0 jos ei löydy
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto"); 
     * int id1 = kyykky.getLiikeID();
     * liikkeet.lisaa(kyykky); liikkeet.lisaa(penkki); liikkeet.lisaa(maastaveto);
     * liikkeet.poista(id1+1) === 1;
     * liikkeet.annaId(id1+1) === null; liikkeet.getLkm() === 2;
     * liikkeet.poista(id1) === 1; liikkeet.getLkm() === 1;
     * liikkeet.poista(id1+3) === 0; liikkeet.getLkm() === 1;
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
     * @param tied tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Liikkeet liikkeet = new Liikkeet();
     *  Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     *  kyykky.rekisteroi();
     *  kyykky.setLiikeNimi("kyykky");
     *  penkki.rekisteroi();
     *  penkki.setLiikeNimi("penkki");
     *  maastaveto.rekisteroi();
     *  maastaveto.setLiikeNimi("maastaveto");
     *  String hakemisto = "testiliikkeet";
     *  String tiedNimi = hakemisto+"/liikkeet";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  liikkeet.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  liikkeet.lisaa(kyykky);
     *  liikkeet.lisaa(penkki);
     *  liikkeet.tallenna();                    
     *  liikkeet = new Liikkeet();           
     *  liikkeet.lueTiedostosta(tiedNimi);
     *  Iterator<Liike> i = liikkeet.iterator();
     *  i.next().toString() === kyykky.toString();
     *  i.next().toString() === penkki.toString();
     *  i.hasNext() === false;
     *  liikkeet.lisaa(maastaveto);
     *  liikkeet.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Liike lii = new Liike();
                lii.parse(rivi); // voisi olla virhekäsittely
                lisaa(lii);
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
     * Tallentaa liikelistan tiedostoon.  
     * Tiedoston muoto:
     * <pre>
     * Harri harjoittelija
     * 3
     * 1|Kyykky
     * 2|Penkki
     * 3|Maastaveto
     * </pre>
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Liike liike : this) {
                fo.println(liike.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palauttaa viitteen i:teen liikkeeseen.
     * @param i monennenko liikkeen viite halutaan
     * @return viite liikkeeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella 
     */
    public Liike anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Etsii liikkeen id:n perusteella
     * @param id tunnusnumero, jonka mukaan etsitään
     * @return liike, jolla etsittävä id tai null
     * <pre name="test">
     * #THROWS SailoException 
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     * int id1 = kyykky.getLiikeID();
     * liikkeet.lisaa(kyykky); liikkeet.lisaa(penkki); liikkeet.lisaa(maastaveto);
     * liikkeet.annaId(id1  ) == kyykky === true;
     * liikkeet.annaId(id1+1) == penkki === true;
     * liikkeet.annaId(id1+2) == maastaveto === true;
     * </pre>
     */ 
    public Liike annaId(int id) { 
        for (Liike liike : this) { 
            if (id == liike.getLiikeID()) return liike; 
        } 
        return null; 
    } 
    
    
    /**
     * Tarkistaa, onko samannimistä liikettä jo olemassa
     * @param liikenimi liikkeen nimi, jota etsitään jo olemassaolevista
     * @return false, jos samannimistä liikettä ei ole ja true, jos on.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     * liikkeet.lisaa(kyykky); liikkeet.lisaa(penkki); liikkeet.lisaa(maastaveto);
     * liikkeet.onkoLiike("leuanveto") === false;
     * liikkeet.onkoLiike("kyykky") === true;
     * liikkeet.onkoLiike("maastaveto") === true;
     * </pre>
     */
    public boolean onkoLiike(String liikenimi) {
        boolean onko = false;
        for (Liike liike : alkiot)
            if ( liike != null && liike.getLiikeNimi().equalsIgnoreCase(liikenimi)) onko = true;
        return onko; 
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
     * Luokka liikkeiden iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #PACKAGEIMPORT
     * #import java.util.*;
     *
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     *
     * liikkeet.lisaa(kyykky); liikkeet.lisaa(penkki); liikkeet.lisaa(maastaveto);
     *
     * StringBuffer ids = new StringBuffer(30);
     * for (Liike liike:liikkeet)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+liike.getLiikeID());           
     *
     * String tulos = " " + kyykky.getLiikeID() + " " + penkki.getLiikeID() + " " + maastaveto.getLiikeID();
     *
     * ids.toString() === tulos;
     *
     * ids = new StringBuffer(30);
     * for (Iterator<Liike>  i=liikkeet.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Liike liike = i.next();
     *   ids.append(" "+liike.getLiikeID());           
     * }
     *
     * ids.toString() === tulos;
     *
     * Iterator<Liike>  i=liikkeet.iterator();
     * i.next() == kyykky  === true;
     * i.next() == penkki  === true;
     * i.next() == maastaveto  === true;
     *
     * i.next();  #THROWS NoSuchElementException
     * 
     * </pre>
     */
    public class LiikkeetIterator implements Iterator<Liike> {
        private int kohdalla = 0;
        /**
         * Onko olemassa vielä seuraavaa liikettä
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä liikkeitä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }
        /**
         * Annetaan seuraava liike
         * @return seuraava liike
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Liike next() throws NoSuchElementException {
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
     * Palautetaan iteraattori liikkeistään.
     * @return liike iteraattori
     */
    @Override
    public Iterator<Liike> iterator() {
        return new LiikkeetIterator();
    }
    
    
    /**
     * Palauttaa harjoittelijan koko nimen
     * @return harjoittelijan koko nimi merkkijononna
     */
    public String getKokoNimi() {
        return kokoNimi;
    }

    
    /**
     * Palauttaa luotujen liikkeiden lukumäärän
     * @return liikkeiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Etsii liikkeen id:n perusteella
     * @param id tunnusnumero, jonka mukaan etsitään
     * @return löytyneen liikkeen indeksi tai -1 jos ei löydy
     * <pre name="test">
     * #THROWS SailoException 
     * Liikkeet liikkeet = new Liikkeet();
     * Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     * kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     * int id1 = kyykky.getLiikeID();
     * liikkeet.lisaa(kyykky); liikkeet.lisaa(penkki); liikkeet.lisaa(maastaveto);
     * liikkeet.etsiId(id1+1) === 1;
     * liikkeet.etsiId(id1+2) === 2;
     * </pre>
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getLiikeID()) return i; 
        return -1; 
    } 
    
    
    /**
     * Haetaan kaikki harjoittelijan harjoitukset
     * @return tietorakenne jossa viiteet löydettyihin harjoituksiin
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.util.*;

     *  Liikkeet liikelista = new Liikkeet();
     *  Liike liike1 = new Liike(); liike1.setLiikeNimi("liike1"); liikelista.lisaa(liike1);
     *  Liike liike2 = new Liike(); liike2.setLiikeNimi("liike2"); liikelista.lisaa(liike2);
     *  Liike liike3 = new Liike(); liike3.setLiikeNimi("liike3"); liikelista.lisaa(liike3);
     *  Liike liike4 = new Liike(); liike4.setLiikeNimi("liike4"); liikelista.lisaa(liike4);
     *  Liike liike5 = new Liike(); liike5.setLiikeNimi("liike5"); liikelista.lisaa(liike5);
     *  
     *  List<Liike> loytyneet;
     *  loytyneet = liikelista.annaLiikkeet();
     *  loytyneet.size() === 5; 
     *  loytyneet.get(0) == liike1 === true;
     *  loytyneet.get(3) == liike4 === true;
     * </pre> 
     */
    public List<Liike> annaLiikkeet() {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike lii : alkiot)
            if (lii != null) loydetyt.add(lii);
        return loydetyt;
    }
    
    
    /**
     * @param args ei käytössä
     * @throws SailoException poikkeus, jos tiedoston tallennus ei toimi
     */
    public static void main(String[] args) throws SailoException {
        Liikkeet liikkeet = new Liikkeet();
        
        Liike kyykky = new Liike();
        kyykky.rekisteroi();
        kyykky.setLiikeNimi("kyykky");
        
        Liike penkki = new Liike();
        penkki.rekisteroi();
        penkki.setLiikeNimi("penkki");
        
        Liike maastaveto = new Liike();
        maastaveto.rekisteroi();
        maastaveto.setLiikeNimi("maastaveto");
        
        Liike dippi = new Liike();
        dippi.rekisteroi();
        dippi.setLiikeNimi("dippi");
        
        Liike pystypunnerrus = new Liike();
        pystypunnerrus.rekisteroi();
        pystypunnerrus.setLiikeNimi("pystypunnerrus");
        
        Liike alatalja = new Liike();
        alatalja.rekisteroi();
        alatalja.setLiikeNimi("alatalja");
        
        try {
            liikkeet.lisaa(kyykky);
            liikkeet.lisaa(penkki);
            liikkeet.lisaa(maastaveto);
            liikkeet.lisaa(dippi);
            liikkeet.lisaa(pystypunnerrus);
            liikkeet.poista(pystypunnerrus.getLiikeID());
            liikkeet.lisaa(alatalja);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }

        
        System.out.println("======================= Liikkeet testi ====================");
        
        for (int i = 0; i < liikkeet.getLkm(); i++) {
            System.out.println("");
            Liike liike = liikkeet.anna(i);
            System.out.println("Liikeindeksi: " + i);
            liike.tulosta(System.out);
        }
        liikkeet.tallenna();
        liikkeet.lueTiedostosta("liikkeet");
        Iterator<Liike> i = liikkeet.iterator();
        System.out.println("\n" + i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
    }

}
