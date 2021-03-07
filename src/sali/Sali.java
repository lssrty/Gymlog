/**
 * 
 */
package sali;

import java.io.File;
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
 * | - lukee ja kirjoittaa käyttäjän tiedostoon         | - Liike           | <- TODO: tiedoston lukeminen ja kirjoittaminen
 * |   pyytämällä apua avustajiltaan                    | - Harjoitus       |
 * | - osaa muuttaa liikkeen id:n liikkeen nimeksi      |                   | <- TODO: liikkeen id:n muuttaminen liikkeen nimeksi
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
     */
    public void lisaa(Harjoitus har) throws SailoException {
        harjoitukset.lisaa(har);
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon (comboboxin päivämäärään) vastaavien suoritusten viitteet 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä suorituksista 
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Suoritus> annaSuoritukset(int k) throws SailoException { 
        return suoritukset.annaSuoritukset(k); 
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
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        suoritukset.setTiedostonPerusNimi(hakemistonNimi + "suoritukset");
        liikkeet.setTiedostonPerusNimi(hakemistonNimi + "liikkeet");
        harjoitukset.setTiedostonPerusNimi(hakemistonNimi + "harjoitukset");
    }
    
    
    /**
     * Lukee käyttäjän tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
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
     *  sali.lueTiedostosta(hakemisto); #THROWS SailoException
     *  sali.lisaa(reeni1);
     *  sali.lisaa(reeni2);
     *  sali.lisaa(kyykky1);
     *  sali.lisaa(kyykky2);
     *  sali.lisaa(kyykky3);
     *  sali.lisaa(kyykky4);
     *  sali.lisaa(kyykky);
     *  sali.lisaa(penkki);
     *  sali.lisaa(maastaveto); 
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
     *  sali.tallenna();
     *  fhtied.delete() === true;
     *  fltied.delete() === true;
     *  fstied.delete() === true;
     *  File fhbak = new File(hakemisto+"/harjoitukset.bak");
     *  File flbak = new File(hakemisto+"/liikkeet.bak");
     *  File fsbak = new File(hakemisto+"/suoritukset.bak");
     *  fhbak.delete() === true;
     *  flbak.delete(); // === true; TODO: Selvitä, miksi testi ei luonut liikkeet.bak
     *  fsbak.delete() === true; 
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        suoritukset = new Suoritukset(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        harjoitukset = new Harjoitukset();
        liikkeet = new Liikkeet();

        setTiedosto(nimi);
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
     * Lisätään uusi liike käyttäjälle
     * @param lii lisättävä liike 
     * @throws SailoException jos tulee ongelmia
     */
    public void lisaa(Liike lii) throws SailoException {
        liikkeet.lisaa(lii);
    }
    
    
    /**
     * @return suoritusten lukumäärä
     */
    public int getSuorituksia() {
        return suoritukset.getLkm();
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
