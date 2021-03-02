/**
 * 
 */
package sali;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Liikkeet                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Liike           |
 * | - Pitää yllä rekisteriä tallennetuista liikkeistä, |                   |
 * |   eli osaa lisätä ja poistaa liikkeen              |                   |
 * |   (vain liikkeitä joita ei ole tehty voi poistaa)  |                   | <- TODO: Poista vain liikkeitä, joita ei ole tehty
 * | - lukee ja kirjoittaa liikkeet tiedostoon          |                   | <- TODO: Lue ja kirjoita liikkeet tiedostoon
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
    private Liike[] alkiot;
    
    private String tiedostonNimi = "";

    
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
     * dippi = new Liike(), pystypunnerrus = new Liike(), alatalja = new Liike();
     * liikkeet.getLkm() === 0;
     * liikkeet.lisaa(kyykky); liikkeet.getLkm() === 1;
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
        
        if ( Arrays.asList(alkiot).contains(liike) == true )
            throw new SailoException("Sama liike on jo olemassa");
        alkiot[lkm] = liike;
        lkm++;
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
     * Lukee liikkeet tiedostosta.  
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".lii";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa jäsenistön tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
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

        
        System.out.println("======================= Suoritukset testi ====================");
        
        for (int i = 0; i < liikkeet.getLkm(); i++) {
            System.out.println("");
            Liike liike = liikkeet.anna(i);
            System.out.println("Liikeindeksi: " + i);
            liike.tulosta(System.out);
        }
    
    }

}
