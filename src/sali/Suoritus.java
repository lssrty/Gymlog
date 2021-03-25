/**
 * 
 */
package sali;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

// import static kanta.RasitusTarkistus.*; TODO: Onko turha?

/**
 * TODO: Poista turhat getterit, anna(int i) toteuttaa saman switchillä varmaankin kaikissa tarvittavissa tilanteissa?
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Suoritus                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää suorituksen attribuutit(liike,toistot jne)|                   |
 * | - osaa tarkistaa, että rasitus on 1-10 välillä     |                   | // <--- TODO: tee rasituksen arvon tarkistus
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
 */
public class Suoritus implements Cloneable {
    
    private int     tunnusNro;
    private int     harjoitusID = 0;
    private int     liikeID     = 0;
    private int     sarjat      = 0;
    private int     toistot     = 0;
    private double  painot      = 0;
    private double  rasitus     = 0;
    private String  kommentit   = "";
    
    private static int seuraavaNro    = 1;
    
    
    /**
     * Alustetaan harrastus.  Toistaiseksi ei tarvitse tehdä mitään
     */
    public Suoritus() {
        // Vielä ei tarvita mitään
    }
    
    
    /**
     * Alustetaan tietyn harjoituksen suoritus.  
     * @param harjoitusID suorituksen viitenumero 
     */
    public Suoritus(int harjoitusID) {
        this.harjoitusID = harjoitusID;
    }
    
    
    /**
     * @return harjoituksen ID
     * TODO: Lisää testejä
     * @example
     * <pre name="test">
     * Suoritus kyykkysarja = new Suoritus();
     * kyykkysarja.getHarjoitusID() === 0;
     * </pre>
     */
    public int getHarjoitusID() {
        return harjoitusID;
    }
    
    
    /**
     * @return liikkeen ID
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getLiikeID() === 1;
     * </pre>
     */
    public int getLiikeID() {
        return liikeID;
    }
    
    
    /**
     * @return suorituksen sarjamäärä
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getSarjat() === 3;
     * </pre>
     */
    public int getSarjat() {
        return sarjat;
    }
    
    
    /**
     * @return suorituksen toistomäärät
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getToistot() === 5;
     * </pre>
     */
    public int getToistot() {
        return toistot;
    }
    
    
    /**
     * @return Sarjapainot
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getPainot() ~~~ 160;
     * </pre>
     */
    public double getPainot() {
        return painot;
    }
    
    
    /**
     * @return rasitus
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.getRasitus() ~~~ 8;
     * </pre>
     */
    public double getRasitus() {
        return rasitus;
    }
    
    
    /**
     * @return suorituksen kommentit
     */
    public String getKommentit() {
        return kommentit;
    }
    
    
    /**
     * Palauttaa liikkeen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return liikeID ja liikenimi tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Liike penkki = new Liike();
     *   penkki.parse("   2  |  penkki");
     *   penkki.toString() === "2|penkki";
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                getHarjoitusID() + "|" +
                getLiikeID() + "|" +        
                getSarjat() + "|" +
                getToistot() + "|" +
                getPainot() + "|" +
                getRasitus() + "|" +
                getKommentit();
    }

    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }

    /**
     * Eka kenttä stringGridistä joka on mielekäs kysyttäväksi. Tällä hetkellä 2, eli sarjat.
     * TODO: Epäintuitiivinen muuttuja, siirrä suosiolla SaliGUIControllerin alusta-metodiin?
     * @return ekan kentän indeksi
     */
    public int ekaKentta() {
        return 2;
    }

    
    /**
     * Selvittää liikkeen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta liikkeen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.parse("   1  |  1  | 1  | 3 | 5 | 160.0 | 8.0 | Selkä kipeä");
     *   kyykkysarja.getTunnusNro() === 1;
     *   kyykkysarja.toString() === "1|1|1|3|5|160.0|8.0|Selkä kipeä";
     *
     *   kyykkysarja.rekisteroi();
     *   int n = kyykkysarja.getTunnusNro();
     *   kyykkysarja.parse(""+(n+20));       // Otetaan merkkijonosta vain liikeID
     *   kyykkysarja.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kyykkysarja.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        harjoitusID = Mjonot.erota(sb, '|', harjoitusID);
        liikeID = Mjonot.erota(sb, '|', liikeID);
        sarjat = Mjonot.erota(sb, '|', sarjat);
        toistot = Mjonot.erota(sb, '|', toistot);
        painot = Mjonot.erota(sb, '|', painot);
        rasitus = Mjonot.erota(sb, '|', rasitus);
        kommentit = Mjonot.erota(sb, '|', kommentit);
    }
    
    
    /**
     * Tehdään identtinen klooni suorituksesta
     * @return Object kloonattu suoritus
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Suoritus suoritus = new Suoritus();
     *   suoritus.parse("   3  |  1  | 1");
     *   Suoritus kopio = suoritus.clone();
     *   kopio.toString() === suoritus.toString();
     *   suoritus.parse("   4  |  1   | 1");
     *   kopio.toString().equals(suoritus.toString()) === false;
     * </pre>
     */
    @Override
    public Suoritus clone() throws CloneNotSupportedException {
        Suoritus uusi;
        uusi = (Suoritus) super.clone();
        return uusi;
    }

        
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot suoritukselle.
     * Harjoituksen ID arvotaan 1-9999 väliltä.
     */
    public void taytaKyykkyTiedoilla() {
        liikeID = 1;
        sarjat = 3;
        toistot = 5;
        painot = 160;
        rasitus = 8;
        kommentit = "Selkä kipeä " + ((int) (Math.random() * (999 - 1)) + 1);
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot suoritukselle. Tässä versiossa päätetään itse harjoitusID.
     * Harjoituksen ID arvotaan 1-9999 väliltä.
     * @param nro kyykkyyn tuleva harjoitusID
     */
    public void taytaKyykkyTiedoilla(int nro) {
        harjoitusID = nro;
        liikeID = 1;
        sarjat = 3;
        toistot = 5;
        painot = 160;
        rasitus = 8;
        kommentit = "Selkä kipeä " + ((int) (Math.random() * (999 - 1)) + 1);
    }
    
    
    /**
     * Tulostetaan suorituksen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro));
        out.print("  harjoituksen ID: " + harjoitusID + " liikkeen ID: " + liikeID);
        out.print(" sarjat: " + sarjat + " toistot: " + toistot + " painot: " + painot + " rasitus: " + rasitus);
        out.println("  kommentit: " + kommentit);
    }
    
    
    /**
     * Tulostetaan suorituksen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa suoritukselle seuraavan rekisterinumeron.
     * @return suorituksen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja1 = new Suoritus();
     *   kyykkysarja1.getTunnusNro() === 0;
     *   kyykkysarja1.rekisteroi();
     *   Suoritus kyykkysarja2 = new Suoritus();
     *   kyykkysarja2.rekisteroi();
     *   int n1 = kyykkysarja1.getTunnusNro();
     *   int n2 = kyykkysarja2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Asettaa lajitteluavaimeksi suorituksen tunnusnumeron
     */
    @Override
    public int hashCode() {
        return tunnusNro;
    }

    
    
    /**
     * Palauttaa suorituksen tunnusnumeron.
     * @return suorituksen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Kertoo, minkä sarakkeen kohdalle palautetaan mitäkin tietoa.
     * Tehty SaliGUIController käyttöä varten, minkä takia caset alkaa vasta liikeID:stä.
     * @param i sarakkeen indeksi
     * @return sarakkeeseen tuleva tieto
     * @example
     * <pre name="test">
     *   Suoritus kyykkysarja = new Suoritus();
     *   kyykkysarja.taytaKyykkyTiedoilla();
     *   kyykkysarja.anna(4) === "8.0";
     * </pre>
     */
    public String anna(int i) {
        switch (i) {
            case 0: return ""+liikeID;
            case 1: return ""+sarjat;
            case 2: return ""+toistot;
            case 3: return ""+painot;
            case 4: return ""+rasitus;
            case 5: return kommentit;
            default: break;
        }
        return "";
    }

    
    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     * @example
     * <pre name="test">
     *   Suoritus suoritus = new Suoritus();
     *   suoritus.aseta(1,"5") === null;
     *   suoritus.aseta(2,"3") === null;
     *   suoritus.aseta(2,"asd") === "LiikeID:hen syötetty arvo ei ole numero"; 
     *   suoritus.aseta(5,"kissa") === "Painoihin syötetty arvo ei ole numero";
     * </pre>
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setTunnusNro(Mjonot.erota(sb, '§', getTunnusNro()));
            return null;
        case 1:
            try {
                harjoitusID = Integer.parseInt(tjono);
             }
             catch (NumberFormatException e)
             {
                return "HarjoitusID:hen syötetty arvo ei ole numero";
             }
            return null;
        case 2:
            try {
                liikeID = Integer.parseInt(tjono);
             }
             catch (NumberFormatException e)
             {
                 return "LiikeID:hen syötetty arvo ei ole numero";
             }
            return null;
        case 3:
            try {
                sarjat = Integer.parseInt(tjono);
             }
             catch (NumberFormatException e)
             {
                 return "Sarjoihin syötetty arvo ei ole numero";
             }
            return null;
        case 4:
            try {
                toistot = Integer.parseInt(tjono);
             }
             catch (NumberFormatException e)
             {
                 return "Toistoihin syötetty arvo ei ole numero";
             }
            return null;
        case 5:
            try {
                painot = Double.parseDouble(tjono);
             }
             catch (NumberFormatException e)
             {
                 return "Painoihin syötetty arvo ei ole numero";
             }
            return null;
        case 6:
            try {
                rasitus = Double.parseDouble(tjono);
             }
             catch (NumberFormatException e)
             {
                 return "Rasitukseen syötetty arvo ei ole numero";
             }
            return null;
        case 7:
            kommentit = tjono;
            return null;
        default:
            return "Ääliö :D Nyt meni joku pieleen Suoritus.aseta kanssa";
        }
    }

    
    /**
     * Asettaa suoritukselle annetun liikkeen liikeID:n
     * @param liike liike, jonka ID asetetaan
     * @example
     * <pre name="test">
     * Suoritus kyykkysarja = new Suoritus();
     * Liike kyykky = new Liike();
     * kyykky.rekisteroi();
     * kyykky.setLiikeNimi("kyykky");
     * kyykkysarja.getLiikeID() === 0;
     * kyykkysarja.asetaLiike(kyykky);
     * kyykkysarja.getLiikeID() === kyykky.getLiikeID();
     * </pre>
     */
    public void asetaLiike(Liike liike) {
        liikeID = liike.getLiikeID();
    }
    
   
    /**
     *
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Suoritus kyykkysarja = new Suoritus(), kyykkysarja2 = new Suoritus();
        kyykkysarja.rekisteroi();
        kyykkysarja2.rekisteroi();
        
        kyykkysarja.tulosta(System.out);
        kyykkysarja.taytaKyykkyTiedoilla();
        kyykkysarja.tulosta(System.out);
        
        kyykkysarja2.tulosta(System.out);
        kyykkysarja2.taytaKyykkyTiedoilla();
        kyykkysarja2.tulosta(System.out);
        
        System.out.println("\n" + kyykkysarja);
        System.out.println(kyykkysarja2);
    }
}
