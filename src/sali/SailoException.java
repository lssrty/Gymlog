package sali;

/**
 * Poikkeusluokka tietorakenteesta aiheutuvia poikkeuksia varten
 * @author lasse
 * @version 25 Feb 2021
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}