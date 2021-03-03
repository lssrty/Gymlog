/**
 * 
 */
package sali;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Suoritukset                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Suoritus        |
 * | - pitää yllä rekisteriä suorituksista, eli osaa    |                   |
 * |   lisätä ja poistaa suorituksia                    |                   |<-- TODO: Suoritusten poisto
 * | - lukee ja kirjoittaa suoritukset tiedostoon       |                   |<-- TODO: Suoritusten kirjoitus tiedostoon
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
 * TODO: Uuden alkiot-taulukon luonti, kun vanha on täynnä
 * @author lasse
 * @version 25 Feb 2021
 *
 */
public class Suoritukset {
    
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
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
    }
}
