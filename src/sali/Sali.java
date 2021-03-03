/**
 * 
 */
package sali;

/**
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
 * | - osaa muuttaa liikkeen id:n liikkeen nimeksi      |                   |<- TODO: liikkeen id:n muuttaminen liikkeen nimeksi
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
    private Suoritukset suoritukset = new Suoritukset();
    
    
    /**
     * Lisätään uusi suoritus
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
