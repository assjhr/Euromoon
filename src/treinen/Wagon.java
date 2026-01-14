package treinen;
/**
 * Deze klasse stelt een wagon van een trein voor.
 * Elke wagon behoort tot een bepaalde reisklasse
 * (eerste of tweede klasse) en heeft een vaste
 * maximale capaciteit.
 */
public class Wagon {

    //Maximale capaciteit van deze wagon (volgens de opdracht 80 personen
    private final int maximumCapaciteit = 80;

    //Klasse van deze wagon: eerste of tweede
    private Klasse klasse;

    //Constructor: stel klasse in bij aanmaak van wagon
    public Wagon(Klasse klasse) {
        this.klasse = klasse;
    }

    //Getter voor de capaciteit
    public int getMaximumCapaciteit(){
        return maximumCapaciteit;
    }

    //Getter voor de klasse
    public Klasse getKlasse(){
        return klasse;
    }
}
