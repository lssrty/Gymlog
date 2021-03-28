/**
 * 
 */
package sali;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

/**
 * TODO: Suoritusten etsiminen listaan, missä tietty liike (liikelistaa varten). Mallia "Collection<Jasen> etsi" -metodista?
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Sali                                  | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Suoritukset     |
 * | - huolehtii suoritukset, liikkeet ja harjoitukset  | - Liikkeet        |
 * |   välisestä yhteistyöstä ja välittää niiden tietoja| - Harjoitukset    |
 * |   pyydettäessä                                     | - Suoritus        |
 * | - lukee ja kirjoittaa käyttäjän tiedostoon         | - Liike           | 
 * |   pyytämällä apua avustajiltaan                    | - Harjoitus       |
 * | - osaa muuttaa liikkeen id:n liikkeen nimeksi      |                   | 
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
 * Testien alustus
 * @example
 * <pre name="testJAVA">
 * #import sali.SailoException;
 *  private Sali sali;
 *  private Suoritus kyykkysarja1;
 *  private Suoritus kyykkysarja2;
 *  private int sid1;
 *  private int sid2;
 *  private Harjoitus treeni21;
 *  private Harjoitus treeni11;
 *  private Harjoitus treeni22; 
 *  private Harjoitus treeni12; 
 *  private Harjoitus treeni23;
 *  private Liike pystypunnerrus;
 *  private Liike dippi;
 *  
 *  public void alustaSali() {
 *    sali = new Sali();
 *    treeni21 = new Harjoitus(); treeni21.rekisteroi();
 *    treeni11 = new Harjoitus(); treeni11.rekisteroi();
 *    treeni22 = new Harjoitus(); treeni22.rekisteroi(); 
 *    treeni12 = new Harjoitus(); treeni12.rekisteroi(); 
 *    treeni23 = new Harjoitus(); treeni23.rekisteroi();
 *    kyykkysarja1 = new Suoritus(); kyykkysarja1.taytaKyykkyTiedoilla(treeni21.getHarjoitusID()); kyykkysarja1.rekisteroi();
 *    kyykkysarja2 = new Suoritus(); kyykkysarja2.taytaKyykkyTiedoilla(treeni11.getHarjoitusID()); kyykkysarja2.rekisteroi();
 *    pystypunnerrus = new Liike(); pystypunnerrus.rekisteroi(); pystypunnerrus.setLiikeNimi("pystypunnerrus");
 *    dippi = new Liike(); dippi.rekisteroi(); dippi.setLiikeNimi("dippi");
 *    sid1 = kyykkysarja1.getTunnusNro();
 *    sid2 = kyykkysarja2.getTunnusNro();
 *    try {
 *    sali.lisaa(kyykkysarja1);
 *    sali.lisaa(kyykkysarja2);
 *    sali.lisaa(treeni21);
 *    sali.lisaa(treeni11);
 *    sali.lisaa(treeni22);
 *    sali.lisaa(treeni12);
 *    sali.lisaa(treeni23);
 *    sali.lisaa(pystypunnerrus);
 *    sali.lisaa(dippi);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
 */
public class Sali {
    private Harjoitukset harjoitukset = new Harjoitukset();
    private Suoritukset suoritukset = new Suoritukset();
    private Liikkeet liikkeet = new Liikkeet();
    
    
    /**
     * Lisätään uusi suoritus käyttäjälle
     * @param suoritus lisättävä suoritus
     * @throws SailoException jos lisääminen ei onnistu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Sali sali = new Sali();
     * Suoritus kyykkysarja1 = new Suoritus(), kyykkysarja2 = new Suoritus();
     * kyykkysarja1.rekisteroi(); kyykkysarja2.rekisteroi();
     * sali.getSuorituksia() === 0;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 1;
     * sali.lisaa(kyykkysarja2); sali.getSuorituksia() === 2;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 3;
     * sali.getSuorituksia() === 3;
     * sali.annaSuoritus(0) === kyykkysarja1;
     * sali.annaSuoritus(1) === kyykkysarja2;
     * sali.annaSuoritus(2) === kyykkysarja1;
     * sali.annaSuoritus(3) === kyykkysarja1; #THROWS IndexOutOfBoundsException
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 4;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 5;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 6;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 7;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 8;
     * sali.lisaa(kyykkysarja1); sali.getSuorituksia() === 9;
     * </pre>
     */
    public void lisaa(Suoritus suoritus) throws SailoException {
        suoritukset.lisaa(suoritus);     
    }
    
    
    /**
     * Lisätään uusi harjoitus käyttäjälle
     * @param har lisättävä harjoitus 
     * @throws SailoException jos tulee ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Sali sali = new Sali();
     * Harjoitus harjoitus1 = new Harjoitus();
     * Harjoitus harjoitus2 = new Harjoitus();
     * sali.getHarjoituksia() === 0;
     * sali.lisaa(harjoitus1); sali.lisaa(harjoitus2);
     * sali.getHarjoituksia() === 2;
     * </pre>
     */
    public void lisaa(Harjoitus har) throws SailoException {
        harjoitukset.lisaa(har);
    }
    
    
    /**
     * Lisätään uusi liike käyttäjälle
     * @param lii lisättävä liike 
     * @throws SailoException jos tulee ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Sali sali = new Sali();
     * Liike kyykky = new Liike(), penkki = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi();
     * kyykky.setLiikeNimi("kyykky");
     * penkki.setLiikeNimi("penkki");
     * sali.getLiikkeita() === 0;
     * sali.lisaa(kyykky); sali.lisaa(penkki);
     * sali.getLiikkeita() === 2;
     * </pre>
     */
    public void lisaa(Liike lii) throws SailoException {
        liikkeet.lisaa(lii);
    }
    
    
    /** 
     * Korvaa suorituksen tietorakenteessa.  Ottaa suorituksen omistukseensa. 
     * Etsitään samalla tunnusnumerolla oleva suoritus.  Jos ei löydy, 
     * niin lisätään uutena suorituksena. 
     * @param suoritus lisättävän suorituksen viite.  Huom tietorakenne muuttuu omistajaksi 
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaSali();
     *  Suoritus kyykkysarja5 = new Suoritus();
     *  kyykkysarja5.rekisteroi();
     *  sali.getSuorituksia() === 2;
     *  sali.korvaaTaiLisaa(kyykkysarja1);
     *  sali.getSuorituksia() === 2;
     *  sali.korvaaTaiLisaa(kyykkysarja5);
     *  sali.getSuorituksia() === 3;
     * </pre>
     */ 
    public void korvaaTaiLisaa(Suoritus suoritus) { 
        suoritukset.korvaaTaiLisaa(suoritus); 
    } 
    
    
    /**
     * Poistaa suorituksista valitun suorituksen tiedot
     * @param suoritus suoritus jokapoistetaan
     * @return montako suoritusta poistettiin
     * @example
     * <pre name="test">
     * #THROWS Exception
     *   alustaSali();
     *   sali.getSuorituksia() === 2;
     *   sali.poista(kyykkysarja1) === 1;
     *   sali.getSuorituksia() === 1;
     * </pre>
     */
    public int poista(Suoritus suoritus) {
        if ( suoritus == null ) return 0;
        int ret = suoritukset.poista(suoritus.getTunnusNro()); 
        return ret; 
    }
    
    
    /** 
     * Poistaa tämän harjoituksen ja siihen kuuluneet suoritukset
     * TODO: Miksi ei poista kaikkia tähän kuuluneita suorituksia?
     * @param harjoitus poistettava harjoitus 
     * @example
     * <pre name="test">
     * #THROWS Exception
     *   alustaSali();
     *   sali.annaHarjoitukset().size() === 5;
     *   kyykkysarja2.getHarjoitusID() === treeni11.getHarjoitusID();
     *   sali.getSuorituksia() === 2;
     *   sali.poistaHarjoitus(treeni11);
     *   sali.getSuorituksia() === 1;
     *   sali.annaHarjoitukset().size() === 4;
     */ 
    public void poistaHarjoitus(Harjoitus harjoitus) { 
        for (Suoritus suo : suoritukset) {
            if ( suo.getHarjoitusID() == harjoitus.getHarjoitusID() ) poista(suo);
        }
        harjoitukset.poista(harjoitus); 
    } 
    
    
    /**
     * Poistaa liikkeet, mitä ei ole merkitty mihinkään suoritukseen
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.getLiikkeita() === 2;
     * Suoritus dippisarja = new Suoritus(); 
     * dippisarja.rekisteroi(); dippisarja.asetaLiike(dippi);
     * sali.poistaTurhatLiikkeet();
     * sali.getLiikkeita() === 1;
     * </pre>
     */
    public void poistaTurhatLiikkeet() {
        for (Liike lii : liikkeet) { // Poistetaan muut käyttämättömät liikkeet kuin kyykky, penkki ja maastaveto
            boolean kaytetty = false;
            if (lii.getLiikeID() <= 3) kaytetty = true; // Säästetään kyykky, penkki ja maastaveto
            for (Suoritus suo : suoritukset) { // Katsotaan, onko liikettä tehty missään suorituksessa
                if ( lii.getLiikeID() == suo.getLiikeID()) kaytetty = true;
            }
            if ( !kaytetty ) {
                liikkeet.poista(lii.getLiikeID());
            }
        }
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon (eli comboboxin päivämäärään) vastaavien suoritusten viitteet 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä suorituksista 
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Suoritus> annaSuoritukset(int k) throws SailoException { 
        return suoritukset.annaSuoritukset(k); 
    }
    
    
    /**
     * Palauttaa haetun liikkeen korkeimman tehdyn sarjapainon ja sen sarjan toistot
     * @param liikeID tutkittavan liikkeen
     * @return ennätyksen toistot x sarjapaino Stringinä
     * @example
     * <pre name="test">
     * alustaSali();
     * kyykkysarja1.aseta(2, "1"); kyykkysarja1.aseta(4, "2"); kyykkysarja1.aseta(5, "170");
     * sali.haeEnnatys(1) === kyykkysarja1; 
     * kyykkysarja2.aseta(2, "1"); kyykkysarja2.aseta(4, "1"); kyykkysarja2.aseta(5, "180");
     * sali.haeEnnatys(1) === kyykkysarja2;  
     * </pre>
     */
    public Suoritus haeEnnatys (int liikeID) {
        return suoritukset.haeEnnatys(liikeID);
    }
    
    
    /**
     * Haetaan kaikki harjoituksessa olevat suoritukset
     * @param harjoitus harjoitus jonka suorituksia haetaan
     * @return tietorakenne jossa viiteet löydettyihin suorituksiin
     * @throws SailoException jos tulee ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.util.*;
     * 
     *  Sali sali = new Sali();
     *  Harjoitus treeni1 = new Harjoitus(), treeni2 = new Harjoitus(), treeni3 = new Harjoitus();
     *  treeni1.rekisteroi(); treeni2.rekisteroi(); treeni3.rekisteroi();
     *  int id1 = treeni1.getHarjoitusID();
     *  int id2 = treeni2.getHarjoitusID();
     *  Suoritus kyykky11 = new Suoritus(id1); sali.lisaa(kyykky11);
     *  Suoritus kyykky12 = new Suoritus(id1); sali.lisaa(kyykky12);
     *  Suoritus kyykky21 = new Suoritus(id2); sali.lisaa(kyykky21);
     *  Suoritus kyykky22 = new Suoritus(id2); sali.lisaa(kyykky22);
     *  Suoritus kyykky23 = new Suoritus(id2); sali.lisaa(kyykky23);
     *  
     *  List<Suoritus> loytyneet;
     *  loytyneet = sali.annaSuoritukset(treeni3);
     *  loytyneet.size() === 0; 
     *  loytyneet = sali.annaSuoritukset(treeni1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == kyykky11 === true;
     *  loytyneet.get(1) == kyykky12 === true;
     *  loytyneet = sali.annaSuoritukset(treeni2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == kyykky21 === true;
     * </pre> 
     */
    public List<Suoritus> annaSuoritukset(Harjoitus harjoitus) throws SailoException {
        return suoritukset.annaSuoritukset(harjoitus.getHarjoitusID());
    }
    
    
    /**
     * Asettaa tiedostojen perusnimet ja luo tyhjät tiedostot suoritukset.dat, liikkeet.dat ja harjoitukset.dat
     * jos niitä ei ole vielä ole olemassa
     * @param nimi uusi nimi
     * @throws IOException Jos tyhjien pohjatiedostojen luonnin kanssa on ongelmia
     * @throws SailoException Jos kyykyn, penkin ja maastavedon kirjoittaminen uuteen pohjatiedostoon ei onnistu
     */
    public void setTiedosto(String nimi) throws IOException, SailoException {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        suoritukset.setTiedostonPerusNimi(hakemistonNimi + "suoritukset");        
        liikkeet.setTiedostonPerusNimi(hakemistonNimi + "liikkeet");
        harjoitukset.setTiedostonPerusNimi(hakemistonNimi + "harjoitukset");

        File suorituksetPohja  = new File(suoritukset.getTiedostonNimi());
        File liikkeetPohja     = new File(liikkeet.getTiedostonNimi());
        File harjoituksetPohja = new File(harjoitukset.getTiedostonNimi());
        
        if (!suorituksetPohja.isFile()) suorituksetPohja.createNewFile();
        if (!liikkeetPohja.isFile()) {
            liikkeetPohja.createNewFile();
            try ( PrintWriter fo = new PrintWriter(new FileWriter(liikkeetPohja.getCanonicalPath())) ) {
                fo.println("1|Kyykky\n2|Penkki\n3|Maastaveto");
            } catch ( FileNotFoundException ex ) {
                throw new SailoException("Tiedosto " + liikkeetPohja.getName() + " ei aukea");
            } catch ( IOException ex ) {
                throw new SailoException("Tiedoston " + liikkeetPohja.getName() + " kirjoittamisessa ongelmia");
            }
        }
        if (!harjoituksetPohja.isFile()) harjoituksetPohja.createNewFile();
    }
    
    
    /**
     * Lukee käyttäjän tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #THROWS IOException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *  Sali sali = new Sali();
     *  
     *  Harjoitus reeni1 = new Harjoitus(); reeni1.rekisteroi();
     *  Harjoitus reeni2 = new Harjoitus(); reeni2.rekisteroi();
     *  Suoritus kyykky1 = new Suoritus(); kyykky1.taytaKyykkyTiedoilla(reeni1.getHarjoitusID()); kyykky1.rekisteroi();
     *  Suoritus kyykky2 = new Suoritus(); kyykky2.taytaKyykkyTiedoilla(reeni1.getHarjoitusID()); kyykky2.rekisteroi();
     *  Suoritus kyykky3 = new Suoritus(); kyykky3.taytaKyykkyTiedoilla(reeni2.getHarjoitusID()); kyykky3.rekisteroi();
     *  Suoritus kyykky4 = new Suoritus(); kyykky4.taytaKyykkyTiedoilla(reeni2.getHarjoitusID()); kyykky4.rekisteroi();
     *  Liike kyykky = new Liike(), penkki = new Liike(), maastaveto = new Liike();
     *  kyykky.rekisteroi(); penkki.rekisteroi(); maastaveto.rekisteroi();
     *  kyykky.setLiikeNimi("kyykky"); penkki.setLiikeNimi("penkki"); maastaveto.setLiikeNimi("maastaveto");
     *   
     *  String hakemisto = "testiharjoittelija";
     *  File dir = new File(hakemisto);
     *  File fhtied  = new File(hakemisto+"/harjoitukset.dat");
     *  File fltied = new File(hakemisto+"/liikkeet.dat");
     *  File fstied = new File(hakemisto+"/suoritukset.dat");
     *  dir.mkdir();  
     *  fhtied.delete();
     *  fltied.delete();
     *  fstied.delete();
     *  sali.lueTiedostosta(hakemisto);
     *  sali.lisaa(reeni1);
     *  sali.lisaa(reeni2);
     *  sali.lisaa(kyykky1);
     *  sali.lisaa(kyykky2);
     *  sali.lisaa(kyykky3);
     *  sali.lisaa(kyykky4);
     *  // Tästä poistettu kyykyn, penkin ja maastavedon lisääminen koska ne on nyt oletuksena uudessa liikkeet.datissa
     *  sali.tallenna();
     *  sali = new Sali();
     *  sali.lueTiedostosta(hakemisto);
     *  Collection<Suoritus> kaikki = sali.annaSuoritukset(1); 
     *  Iterator<Suoritus> it = kaikki.iterator();
     *  it.next().toString() === kyykky1.toString();
     *  it.next().toString() === kyykky2.toString();
     *  it.hasNext() === false;
     *  List<Suoritus> loytyneet = sali.annaSuoritukset(reeni1);
     *  Iterator<Suoritus> ih = loytyneet.iterator();
     *  ih.next().toString() === kyykky1.toString();
     *  ih.next().toString() === kyykky2.toString();
     *  ih.hasNext() === false;
     *  loytyneet = sali.annaSuoritukset(reeni2);
     *  ih = loytyneet.iterator();
     *  ih.next().toString() === kyykky3.toString();
     *  ih.next().toString() === kyykky4.toString();
     *  ih.hasNext() === false;
     *  sali.lisaa(reeni2);
     *  sali.lisaa(kyykky4);
     *  Liike kulmasoutu = new Liike(); kulmasoutu.rekisteroi(); kulmasoutu.setLiikeNimi("kulmasoutu");
     *  sali.lisaa(kulmasoutu);
     *  sali.tallenna();
     *  fhtied.delete() === true;
     *  fltied.delete() === true;
     *  fstied.delete() === true;
     *  File fhbak = new File(hakemisto+"/harjoitukset.bak");
     *  File flbak = new File(hakemisto+"/liikkeet.bak");
     *  File fsbak = new File(hakemisto+"/suoritukset.bak");
     *  fhbak.delete() === true;
     *  flbak.delete(); // === true;
     *  fsbak.delete() === true; 
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        suoritukset = new Suoritukset(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        harjoitukset = new Harjoitukset();
        liikkeet = new Liikkeet();

        try {
            setTiedosto(nimi);
        } catch (IOException e) {
            throw new SailoException("Ongelmia tyhjän pohjatiedoston luonnin kanssa: " + e.getMessage());
        }
        
        suoritukset.lueTiedostosta();
        harjoitukset.lueTiedostosta();
        liikkeet.lueTiedostosta();
    }

    
    /**
     * Tallentaa salin tiedot tiedostoon.  
     * Vaikka yhden tiedoston tallettamien epäonistuisi, niin yritetään silti tallettaa
     * muita ennen poikkeuksen heittämistä.
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            harjoitukset.tallenna();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            poistaTurhatLiikkeet();
            liikkeet.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        try {
            suoritukset.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new SailoException(virhe);
    }
    
    
    /**
     * @return suoritusten lukumäärä
     */
    public int getSuorituksia() {
        return suoritukset.getLkm();
    }
    
    
    /**
     * @return harjoitusten lukumäärä
     */
    public int getHarjoituksia() {
        return harjoitukset.getLkm();
    }
    
    
    /**
     * @return suoritusten lukumäärä
     */
    public int getLiikkeita() {
        return liikkeet.getLkm();
    }
    
    
    /**
     * Tarkastaa, onko käyttäjän liikkeissä jo samannimistä liikettä olemassa
     * @param liikenimi liikkeen nimi, josta etsitään onko samannimisiä
     * @return false jos samannimisiä liikkeitä ei ole, true jos on
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Sali sali = new Sali();
     * Liike kyykky = new Liike(), penkki = new Liike();
     * kyykky.rekisteroi(); penkki.rekisteroi();
     * kyykky.setLiikeNimi("kyykky");
     * penkki.setLiikeNimi("penkki");
     * sali.lisaa(kyykky); sali.lisaa(penkki);
     * sali.onkoLiike("petteri") === false;
     * sali.onkoLiike("kyykky") === true;
     * </pre>
     */
    public boolean onkoLiike(String liikenimi) {
        return liikkeet.onkoLiike(liikenimi);
    }
    
    
    /**
     * Antaa harjoittelijan i:nnen suorituksen
     * @param i monesko suoritus
     * @return suoritus paikasta i
     */
    public Suoritus annaSuoritus(int i) {
        return suoritukset.anna(i);
    }
    
    
    /**
     * TODO: Antaa tyhjän liikkeen, jos kyseistä liikettä ei ole. Mieti, mikä olisi fiksumpi tapa toteuttaa.
     * Antaa liikkeistä i:nnen liikkeen
     * @param i monesko liike
     * @return liike paikasta i
     */
    public Liike annaLiike(int i) {
        Liike annettava = new Liike();
        for (Liike liike : liikkeet)
            if (liike.getLiikeID() == i) annettava = liike;
        return annettava;
    }
    
    
    /**
     * Antaa harjoittelijan harjoitukset listana
     * @return Lista harjoituksista
     */
    public List<Harjoitus> annaHarjoitukset() {
        return harjoitukset.annaHarjoitukset();
    }
    
    
    /**
     * Antaa harjoittelijan liikkeet listana
     * @return Lista liikkeistä
     */
    public List<Liike> annaLiikkeet() {
        return liikkeet.annaLiikkeet();
    }
    
    
    /**
     * TODO: Isot testit kaikkien luokkien yhteistoiminnasta tähän
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Sali sali = new Sali();
            
            Suoritus kyykkysarja = new Suoritus(), kyykkysarja2 = new Suoritus();
            kyykkysarja.rekisteroi();
            kyykkysarja.taytaKyykkyTiedoilla();
            kyykkysarja2.rekisteroi();
            kyykkysarja2.taytaKyykkyTiedoilla();
            
            try {  
            sali.lisaa(kyykkysarja);
            sali.lisaa(kyykkysarja2);
            } catch (SailoException e) {
                System.err.println(e.getMessage());
            }

            System.out.println("============= Salin testi =================");
            
            for (int i = 0; i < sali.getSuorituksia(); i++) {
                Suoritus suoritus = sali.annaSuoritus(i);
                suoritus.tulosta(System.out);
            }
    }

}
